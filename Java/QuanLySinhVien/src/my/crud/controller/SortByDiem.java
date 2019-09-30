/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.controller;

import java.util.Comparator;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class SortByDiem implements Comparator<SinhVienManager> {

    @Override
    public int compare(SinhVienManager o1, SinhVienManager o2) {
        //Sắp xếp điểm chữ  tăng dần
        return o1.getDiemThi().diemChu().compareTo(o2.getDiemThi().diemChu());
    }
}
