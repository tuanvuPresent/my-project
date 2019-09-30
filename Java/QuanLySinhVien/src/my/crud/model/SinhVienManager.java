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
public class SinhVienManager implements Serializable {

    private String idSv;
    private MonHoc monHoc;
    private DiemThi diemThi;

    public SinhVienManager() {

    }

    
    
    public void setDiemThi(DiemThi diemThi) {
        this.diemThi = diemThi;
    }

    public void setIdSv(String idSv) {
        this.idSv = idSv;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public DiemThi getDiemThi() {
        return diemThi;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public String getIdSv() {
        return idSv;
    }

    public SinhVienManager(String idSv, MonHoc monHoc, DiemThi diemThi) {
        this.idSv = idSv;
        this.monHoc = monHoc;
        this.diemThi = diemThi;
    }

}

