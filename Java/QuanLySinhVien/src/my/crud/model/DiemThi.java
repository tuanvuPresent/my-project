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
public class DiemThi implements Serializable {

    private String hocKi;
    private double diemMid;
    private double diemFinal;

    public DiemThi() {
    }

    public DiemThi(String hocKi, double diemMid, double diemFinal) {
        this.hocKi = hocKi;
        this.diemMid = diemMid;
        this.diemFinal = diemFinal;
    }

    public void setDiemFinal(double diemFinal) throws Exception {
        if (0 > diemFinal || diemFinal > 10) {
            throw new Exception();
        }
        this.diemFinal = diemFinal;
    }

    public void setDiemMid(double diemMid) throws Exception {
        if (0 > diemMid || diemMid > 10) {
            throw new Exception();
        }
        this.diemMid = diemMid;
    }

    public void setHocKi(String hocKi) throws Exception {
        if (hocKi.equals("")) {
            throw new Exception();
        }
        this.hocKi = hocKi;
    }

    public double getDiemFinal() {
        return diemFinal;
    }

    public double getDiemMid() {
        return diemMid;
    }

    public String getHocKi() {
        return hocKi;
    }

    public double diemTb() {
        //Làm tròn 2 chữ số
        return Math.ceil((this.diemMid * 0.3 + this.diemFinal * 0.7) * 100) / 100;
    }

    public String diemChu() {
        double diem = diemTb();
        if (diem >= 9.5) {
            return "A+";
        }
        if (diem >= 8.5) {
            return "A";
        }
        if (diem >= 8) {
            return "B+";
        }
        if (diem >= 7) {
            return "B";
        }
        if (diem >= 6.5) {
            return "C+";
        }
        if (diem >= 5.5) {
            return "C";
        }
        if (diem >= 5) {
            return "D+";
        }
        if (diem >= 4) {
            return "D";
        } else {
            return "F";
        }
    }

    public double diemChuToSo() {
        if (diemChu().equals("A+")) {
            return 4;
        }
        if (diemChu().equals("A")) {
            return 4;
        }
        if (diemChu().equals("B+")) {
            return 3.5;
        }
        if (diemChu().equals("B")) {
            return 3;
        }
        if (diemChu().equals("C+")) {
            return 2.5;
        }
        if (diemChu().equals("C")) {
            return 2;
        }
        if (diemChu().equals("D+")) {
            return 1.5;
        }
        if (diemChu().equals("D")) {
            return 1;
        } else {
            return 0;
        }
    }
}
