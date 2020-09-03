/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import com.sun.javafx.util.Utils;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.Ban;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class QuanLiBanController implements Initializable {

    @FXML
    private TextField txtMaBan;
    @FXML
    private TextField txtSucChua;
    @FXML
    private ComboBox<String> cbTinhTrang;
    @FXML
    private TextField txtNhapTimKiem;
    @FXML
    private TableView<Ban> tbBan;
    @FXML
    private TableColumn colMaBan;
    @FXML
    private TableColumn colSucChua;
    @FXML
    private TableColumn colTinhTrang;
    @FXML
    private Button btTrangChu;
    @FXML
    private Button btQLMon;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btDel;

    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.cbTinhTrang.getItems().add("Còn trống");
        this.cbTinhTrang.getItems().add("Hết chỗ");

        //Gọi observable, thêm vào các trường
        this.colMaBan.setCellValueFactory(new PropertyValueFactory("maBan"));
        this.colSucChua.setCellValueFactory(new PropertyValueFactory("sucChua"));
        this.colTinhTrang.setCellValueFactory(new PropertyValueFactory("tinhTrangTrong"));
        this.tbBan.setItems(this.getListBan(""));

        //Tìm kiếm
        this.txtNhapTimKiem.textProperty().addListener(et -> {
            this.reLoad(this.txtNhapTimKiem.getText());
        });

        //Sự kiện click trên dòng
        this.tbBan.setRowFactory(tbb -> {
            TableRow<Ban> row = new TableRow<>();
            row.setOnMouseClicked(r -> {
                // Đúp click
                if (r.getClickCount() == 2) {
                    Ban b = row.getItem();
                    this.txtMaBan.setText(b.getMaBan());
                    this.txtSucChua.setText(String.valueOf(b.getSucChua()));
                    this.cbTinhTrang.setValue(b.isTinhTrangTrong());
                }
            });
            return row;

        });

    }

    //FXCollections dùng ép kiểu observable
    private ObservableList getListBan(String kw) {
        Session sessions = HibernateUtils.getSessionFactory().openSession();
        Criteria cr = sessions.createCriteria(Ban.class);
        cr.addOrder(Order.asc("sucChua"));
        if (kw != null && !kw.equals("")) {
            kw = String.format("%%%s%%", kw);

            cr.add(Restrictions.ilike("tinhTrangTrong", kw));
        }
        List<Ban> bans = cr.list();
        sessions.close();

        return FXCollections.observableArrayList(bans);

    }
//    private ObservableList getListBan(int kw){
//        Session sessions = HibernateUtils.getSessionFactory().openSession();
//        Criteria cr = sessions.createCriteria(Ban.class);
//        cr.addOrder(Order.asc("sucChua"));
//        if(kw > 0 && !kw.equals("")){
//            kw = String.format("%%%s%%", kw);
//            cr.add(Restrictions.or(Restrictions.ilike("sucChua", kw),
//                                    Restrictions.ilike("tinhTrangTrong", kw)));
//           // cr.add(Restrictions.ilike("tinhTrangTrong", kw));
//        }
//        List<Ban> bans = cr.list();
//        sessions.close();
//        
//        return FXCollections.observableArrayList(bans);
//        
//    }

    private void clear() {
        this.txtMaBan.clear();
        this.txtSucChua.clear();

    }

    private void reLoad(String kw) {
        this.tbBan.getItems().clear();
        this.tbBan.setItems(this.getListBan(kw));
    }

    @FXML
    private void addBanAction(ActionEvent event) {

        String m = UUID.randomUUID().toString();
//        if (this.txtSucChua.getText() != "")
//         {
//             Alert a = new Alert(Alert.AlertType.WARNING, "Xin nhập số", ButtonType.OK);
//            a.show();
//            
//        }
        if (txtMaBan.getText().isEmpty() || txtSucChua.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Thông tin đang trống", ButtonType.CANCEL);
            a.show();
        } else {
            if (Integer.parseInt(this.txtSucChua.getText()) < 0
                    || Integer.parseInt(this.txtSucChua.getText()) > 11) {
                Alert a = new Alert(Alert.AlertType.WARNING, "Sức chứa tối đa 11 chỗ", ButtonType.OK);
                a.show();
            } else {

                if (this.addBan(m, Integer.parseInt(this.txtSucChua.getText()),
                        this.cbTinhTrang.getSelectionModel().getSelectedItem().toString())) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION, "Thêm thành công", ButtonType.OK);
                    a.show();
                    this.clear();
                    this.reLoad("");

                } else {

                    Alert a = new Alert(Alert.AlertType.WARNING, "Thêm thất bại", ButtonType.OK);
                    a.show();
                }

            }

        }

    }

    @FXML
    private void updateBanAction(ActionEvent event) {

        this.tbBan.getSelectionModel().getSelectedItem();
        if (txtMaBan.getText().isEmpty() || txtSucChua.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.INFORMATION, "Thông tin đang trống", ButtonType.CANCEL);
            a.show();
        } else {
            if (this.upDateBan(txtMaBan.getText(), Integer.parseInt(txtSucChua.getText()),
                    cbTinhTrang.getSelectionModel().getSelectedItem().toString())) {
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Cập nhật thành công", ButtonType.OK);
                a.show();
                this.clear();
                this.reLoad("");
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Cập nhật thất bại", ButtonType.CANCEL);
                a.show();
            }

        }

    }

    @FXML
    private void deleteBanAction(ActionEvent event) {
        Ban b = this.tbBan.getSelectionModel().getSelectedItem();
        if (txtMaBan.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.WARNING, "Thông tin rỗng", ButtonType.OK);
            a.show();
        } else {
            btDel.setOnAction(et -> {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Bạn chắc chưa?");
                alert.showAndWait().ifPresent(bt -> {
                    if (bt == ButtonType.OK) {
                        this.deleteBan(txtMaBan.getText(), Integer.parseInt(txtSucChua.getText()),
                                cbTinhTrang.getSelectionModel().getSelectedItem().toString());
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Thành công", ButtonType.OK);
//                a.show();
                        this.reLoad("");
                    }
//
                });

            });

//            } else {
//                Alert a = new Alert(Alert.AlertType.ERROR, "Thất bại", ButtonType.OK);
//                a.show();
//            }
        }
    }

    // Giai quyết click vào khi chưa có dữ liệu ?
    public boolean deleteBan(String m, int sc, String tt) {
        boolean rs = false;
        Ban b = new Ban(m, sc, tt);
        //Ban b = this.tbBan.getSelectionModel().getSelectedItem();
        if (m.isEmpty()) {

            rs = false;
        } else {
//            btDel.setOnAction(et -> {
//
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setContentText("Bạn chắc chưa?");
//                alert.showAndWait().ifPresent(bt -> {
//                    if (bt == ButtonType.OK) {
            Ultils.deleteBans(b);
//                    }
//
//                });
//
//            });
            rs = true;

        }
        return rs;
    }

    private boolean checkTrungBan(String kw, List<Ban> dsb) {
        dsb.stream().filter((b1) -> (b1.getMaBan().equals(kw))).map((_item)
                -> new Alert(Alert.AlertType.INFORMATION, "Đã Có", ButtonType.CANCEL))
                .forEachOrdered((a) -> {
                    a.show();
                });
        return false;
    }

    public boolean addBan(String ma, int sc, String tt) {
        boolean rs = false;
        Ban ban = new Ban(ma, sc, tt);
        try {
            if ((sc > 0 || sc < 11) && !tt.isEmpty()) {

                Ultils.addOrUpdateBan(ban);
                rs = true;

//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.setTitle("Thông báo");
//            a.setContentText("Thêm thành công.");
//            a.show();
//            } else {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Nhập không đúng, mời nhập lại!");
//                JOptionPane.showMessageDialog(null, "Nhập không đúng, mời nhập lại!");
            }
//
        } catch (ExceptionInInitializerError ex) {
            System.err.println(ex.getMessage());
//
        }
        return rs;

    }

//        Session sessions = HibernateUtils.getSessionFactory().openSession();
//        String m = UUID.randomUUID().toString();
//        Ban ban = new Ban(m, Integer.parseInt(this.txtSucChua.getText()),
//                this.cbTinhTrang.getSelectionModel().getSelectedItem().toString());
//        
//        try {
//            
//            Ultils.addOrUpdateBan(ban);
//            
//
//            Alert a = new Alert(Alert.AlertType.INFORMATION);
//            a.setContentText("Thêm thành công !!!");
//            a.show();
//
//            this.reLoad("");
//            rs = true;
//        } catch (Exception ex) {
//            if (trans != null) {
//                trans.rollback();
//                rs = false;
//            }
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setContentText("Thêm thất bại !!!");
//            a.show();
//        } finally {
//            sessions.close();
//        }
// Note: chưa kiểm tra đầu vào
    public boolean upDateBan(String m, int sc, String tt) {
        boolean rs = false;
        //Ban b = this.tbBan.getSelectionModel().getSelectedItem();
//        if (sc != 0 && tt.isEmpty()) {
//            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Chú ý");
//            a.setContentText(" Bạn chưa chọn khách hàng để cập nhật.");
//            JOptionPane.showMessageDialog(null," Bạn chưa chọn khách hàng để cập nhật." );
//            rs = false;
//        }
        //Lấy bàn, trả về Ban
        if (!m.isEmpty()) {

            try {
//                Ban b = (Ban) sessions.get(Ban.class, this.txtMaBan.getText());
//                b.setSucChua(Integer.parseInt(this.txtSucChua.getText()));
//                b.setTinhTrangTrong(this.cbTinhTrang.getSelectionModel()
//                        .getSelectedItem().toString());
                Ban b = new Ban();
                b.setMaBan(m);
                b.setSucChua(sc);
                b.setTinhTrangTrong(tt);

                Ultils.addOrUpdateBan(b);
                rs = true;

//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//                a.setTitle("Thông báo");
//                a.setContentText("Cập nhật thành công !!!");
//                a.show();
            } catch (NullPointerException ex) {
                System.out.println(ex.getMessage());
                rs = false;
            }

        }
        return rs;
    }

    @FXML
    private void troVeTrangChinh(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("GiaoDienChinh.fxml"));
            Scene loginScene = new Scene(login);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void denQLMon(ActionEvent event) {
        try {
            Parent login = FXMLLoader.load(getClass().getResource("QuanLiMon.fxml"));
            Scene loginScene = new Scene(login);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void btThoatHandler(ActionEvent event) throws IOException {
        btThoat.setOnAction(et -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    try {
                        Parent login = FXMLLoader.load(getClass().getResource("DangNhap.fxml"));
                        Scene loginScene = new Scene(login);

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(loginScene);
                        window.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }

            });
        });

    }

}
