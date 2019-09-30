/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package my.crud.model;

import java.io.Serializable;

/**
 *
 * @author NguyenTuanVu
 */
public class MonHoc implements Serializable{

    private String idMon;
    private String tenMon;
    private int soTinChi;

    public MonHoc() {
    }

    public MonHoc(String idMon, String tenMon, int soTinChi) {
        this.idMon = idMon;
        this.tenMon = tenMon;
        this.soTinChi = soTinChi;
    }

    public String getIdMon() {
        return idMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public int getSoTinChi() {
        return soTinChi;
    }

    public void setIdMon(String idMon) {
        this.idMon = idMon;
    }

    public void setSoTinChi(int soTinChi) throws Exception {
        if (20 < soTinChi || soTinChi < 0) {
            throw new Exception();
        }
        this.soTinChi = soTinChi;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public static void main(String[] args) {

    }
}

