/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanliquancaphe.models;

import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author admin
 */
@Entity
@Table (name = "ban")
public class Ban implements Serializable {
    @Id
    @Column(name = "maBan")
    private String maBan;
    @Column(name = "sucChua")
    private int sucChua;
    @Column(name = "tinhTrangTrong")
    private String tinhTrangTrong; // true là còn trống, flase là kh trống


    public Ban(String m, int sc, String tinhTrangTrong) {
        this.maBan = m;
        this.sucChua = sc;
        this.tinhTrangTrong = tinhTrangTrong;
    }
    public  Ban(){
        
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
     * @return the sucChua
     */
    public int getSucChua() {
        return sucChua;
    }

    /**
     * @param sucChua the sucChua to set
     */
    public void setSucChua(int sucChua) {
        this.sucChua = sucChua;
    }

    /**
     * @return the tinhTrangTrong
     */
    public String isTinhTrangTrong() {
        return tinhTrangTrong;
    }

    /**
     * @param tinhTrangTrong the tinhTrangTrong to set
     */
    public void setTinhTrangTrong(String tinhTrangTrong) {
        this.tinhTrangTrong = tinhTrangTrong;
    }

    

    
    
}
