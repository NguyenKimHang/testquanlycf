/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import quanliquancaphe.hibernate.HibernateUtils;
import quanliquancaphe.models.NhanVien;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class DangNhapController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtTenTaiKhoan;
    @FXML
    private PasswordField txtMatKhau;
    @FXML
    private Button btDangNhap;

    @FXML
    private Button btThoat;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void fXDangNhap(ActionEvent event) {
        if (DangNhap(txtTenTaiKhoan.getText(), txtMatKhau.getText(), Ultils.getLogin())) {
            try {
                Parent login = FXMLLoader.load(getClass().getResource("GiaoDienChinh.fxml"));
                Scene loginScene = new Scene(login);

                Stage window = (Stage) ((Node) event.getSource()).getScene()
                        .getWindow();
                window.setScene(loginScene);
                window.show();
            } catch (IOException ex) {
                Logger.getLogger(DangNhapController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            if (txtTenTaiKhoan.getText().isEmpty() && !txtMatKhau.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Tài Khoản trống !!", ButtonType.CANCEL);
                a.showAndWait();
            } else if (txtTenTaiKhoan.getText().isEmpty() && txtMatKhau.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Tài Khoản và Mật Khẩu trống !!", ButtonType.CANCEL);
                a.showAndWait();
            } else if (!txtTenTaiKhoan.getText().isEmpty() && txtMatKhau.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Mật Khẩu trống !!", ButtonType.CANCEL);
                a.showAndWait();
            } else if (txtTenTaiKhoan.getText().length() > 25 || txtMatKhau.getText().length() > 25) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Tài khoản và mật khẩu tối đa 25 ký tự", ButtonType.CANCEL);
                a.showAndWait();
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Tài khoản không tồn tại", ButtonType.CANCEL);
                a.showAndWait();
            }
        }
    }

    public boolean DangNhap(String ttk, String mk, List<NhanVien> dsn) {
        boolean rs = false;

        if (!ttk.trim().isEmpty() && !mk.trim().isEmpty()) {
            NhanVien nv1;
            nv1 = new NhanVien(ttk, mk);
            for (NhanVien n : dsn) {
                if (nv1.getTenTaiKhoan().equals(n.getTenTaiKhoan())
                        && (nv1.getMatKhau().equals(n.getMatKhau()))) {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setContentText("Thành công");
                    JOptionPane.showMessageDialog(null, "Thành công");

                    rs = true;
                }

            }

        }
        return rs;
    }

//        if(rs == false){
//            Alert a = new Alert(Alert.AlertType.INFORMATION, "Tài khoản không tồn tại",
//                                ButtonType.CLOSE);
//                        a.show();
//        }
    public void btThoatHandler(ActionEvent event) throws IOException {
        btThoat.setOnAction(et -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Bạn chắc chưa?");
            alert.showAndWait().ifPresent(bt -> {
                if (bt == ButtonType.OK) {
                    Stage stage = (Stage) btThoat.getScene().getWindow();
                    stage.close();
                }

            });
        });

    }
}

//    public boolean dangNhap(String ttk, String mk, List<NhanVien> dsnv) {
//        boolean kq = false;
//
//        if (ttk.trim().length() == 0 || mk.trim().length() == 0) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setContentText("Tên tài khoản và mật khẩu không được để trống");
//            JOptionPane.showMessageDialog(null, "Tên tài khoản và mật khẩu không được để trống");
//
//        } else {
//            if (ttk.trim().length() > 25 && mk.trim().length() > 25) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Tên tài khoản và mật khẩu tối đa 25 ký tự");
//                JOptionPane.showMessageDialog(null, "Tên tài khoản và mật khẩu tối đa 25 ký tự", "Thông báo", 1);
//
//            } else if (ttk.trim().length() == 0 && mk.trim().length() != 0) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Tài khoản không được để trống");
//                JOptionPane.showMessageDialog(null, "Tài khoản không được để trống", "Thông báo", 1);
//
//            } else if (!ttk.isEmpty() && mk.isEmpty()) {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setContentText("Mật khẩu không được rỗng");
//                JOptionPane.showMessageDialog(null, "Mật khẩu không được rỗng", "Thông báo", 1);
//            } else {
//                NhanVien nv1;
//                nv1 = new NhanVien(ttk.trim(), mk.trim());
//                for (NhanVien n : dsnv) {
//                    if (nv1.getTenTaiKhoan().equals(n.getTenTaiKhoan())
//                            && (nv1.getTenTaiKhoan().equals(n.getMatKhau()))) {
//                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                        alert.setContentText("Thành công");
//                        JOptionPane.showMessageDialog(null, "Thành công", "Thông báo", 1);
//                        return true;
//                    }
//
//                    kq = true;
//                }
//            }
//            if (kq == false) {
//                JOptionPane.showMessageDialog(null, "Không tồn  tại tài khoản", "Thông báo", 1);
//            }
//        }
//        return true;
//
//    }
//
//}
//    public int dangNhap(String ttk, String mk, List<NhanVien> nv) {
//        Session sessions = HibernateUtils.getSessionFactory().openSession();
//        Query q = sessions.createQuery("from NhanVien");
//        List<NhanVien> rs;
//        rs = Ultils.getLogin();
//        rs.forEach(e -> {
//            if (this.txtTenTaiKhoan.getText().length() > 25 && this.txtMatKhau.getText().length() > 25) {
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//                a.setContentText("Tối đa 25 ký tự");
//                a.show();
//            } else if (this.txtTenTaiKhoan.getText().isEmpty() && !(this.txtMatKhau.getText().isEmpty())) {
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//
//                a.setContentText("Vui lòng nhập Tên tài khoản");
//                a.show();
//
//            } else if (!(this.txtTenTaiKhoan.getText().isEmpty()) && this.txtMatKhau.getText().isEmpty()) {
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//
//                a.setContentText("Vui lòng nhập Mật khẩu");
//                a.show();
//            } else if (this.txtTenTaiKhoan.getText().isEmpty() && this.txtMatKhau.getText().isEmpty()) {
//                Alert a = new Alert(Alert.AlertType.INFORMATION);
//
//                a.setContentText("Vui lòng nhập Nhập Tài Khoản và Mật khẩu");
//                a.show();
//            } else if (e.getTenTaiKhoan().equals(this.txtTenTaiKhoan.getText())
//                    && e.getMatKhau().equals(this.txtMatKhau.getText())) {
//
//                try {
//                    Parent login = FXMLLoader.load(getClass().getResource("GiaoDienChinh.fxml"));
//                    Scene loginScene = new Scene(login);
//
//                    Stage window = (Stage) ((Node) event.getSource()).getScene()
//                            .getWindow();
//                    window.setScene(loginScene);
//                    window.show();
//                } catch (IOException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });
//
//        sessions.close();
//
//    }

