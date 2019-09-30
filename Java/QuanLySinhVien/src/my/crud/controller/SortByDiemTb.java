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
public class SortByDiemTb implements Comparator<SinhVienManager> {

    @Override
    public int compare(SinhVienManager o1, SinhVienManager o2) {
        //Sắp xếp điểm trung bình
        String diemTb1 = Double.toString(o1.getDiemThi().diemTb());
        String diemTb2 = Double.toString(o2.getDiemThi().diemTb());
        return diemTb1.compareTo(diemTb2);
    }

}
