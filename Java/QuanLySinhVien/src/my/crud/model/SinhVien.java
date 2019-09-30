/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.model;

import java.io.Serializable;
import my.crud.controller.MyException;

/**
 *
 * @author NguyenTuanVu
 */
public class SinhVien implements Serializable {

    private String id;
    private String ten;
    private String ns;
    private String gmail;
    private String sdt;

    public SinhVien() {
    }

    public SinhVien(String id, String ten, String ns, String gmail, String sdt) {
        this.id = id;
        this.ten = ten;
        this.ns = ns;
        this.gmail = gmail;
        this.sdt = sdt;
    }

    public void setTen(String ten) throws Exception {
        MyException myException = new MyException();
        if (myException.isTen(ten)) {
            this.ten = ten;
        } else {
            throw new Exception();
        }
    }

    public void setNs(String ns) throws Exception {
        MyException myException = new MyException();
        if (myException.isNs(ns)) {
            this.ns = ns;
        } else {
            throw new Exception();
        }
    }

    public void setGmail(String gmail) throws Exception {
        MyException myException = new MyException();
        if (myException.isGmail(gmail)) {
            this.gmail = gmail;
        } else {
            throw new Exception();
        }
    }

    public void setSdt(String sdt) throws Exception {
        MyException myException = new MyException();
        if (myException.isSdt(sdt)) {
            this.sdt = sdt;
        } else {
            throw new Exception();
        }
    }

    public void setId(String id) throws Exception {
        MyException myException = new MyException();
        if (myException.isId(id)) {
            this.id = id;
        } else {
            throw new Exception();
        }
    }

    public String getId() {
        return id;
    }

    public String getTen() {
        return ten;
    }

    public String getNs() {
        return ns;
    }

    public String getGmail() {
        return gmail;
    }

    public String getSdt() {
        return sdt;
    }

}
