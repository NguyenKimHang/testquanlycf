/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author admin
 */
public class HoaDon {

    private int maHD;
    private String maBan;
    private int maNV;
    private Date ngayDat;
    private double tongTien;


    public HoaDon() {

    }

    public HoaDon(int ma, String maBan, int maNV,String ngayDat, double tt) {
        try {
            this.maHD = ma;
            this.maBan = maBan;
            this.maNV = maNV;
            SimpleDateFormat f = new SimpleDateFormat("yyyy/mm/dd");
            this.ngayDat = f.parse(ngayDat);
            this.tongTien = tt;
        } catch (ParseException ex) {
            System.err.println("Lỗi định dạng ngày tháng!!");
        }
    }
    @Override
    public String toString() {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        String m = String.format("\n\t Mã hóa đơn: %d\n\t Mã NV: %d\n\t "
                + "Mã bàn: %s\n\t Ngày đặt: %s ", this.maHD,
                this.maNV, this.maBan, f.format(ngayDat));
        ;
        return m;
    }

    /**
     * @return the maHD
     */
    public int getMaHD() {
        return maHD;
    }

    /**
     * @param maHD the maHD to set
     */
    public void setMaHD(int maHD) {
        this.maHD = maHD;
    }

    /**
     * @return the maBan
     */
    public String getMaBan() {
        return maBan;
    }

    /**
     * @param maBan the maBan to set
     */
    public void setMaBan(String maBan) {
        this.maBan = maBan;
    }

    /**
     * @return the maNV
     */
    public int getMaNV() {
        return maNV;
    }

    /**
     * @param maNV the maNV to set
     */
    public void setMaNV(int maNV) {
        this.maNV = maNV;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    /**
     * @return the tongTien
     */
    public double getTongTien() {
        return tongTien;
    }

    /**
     * @param tongTien the tongTien to set
     */
    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

   
}
