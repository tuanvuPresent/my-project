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
public class SortByHocKy implements Comparator<SinhVienManager> {

    @Override
    public int compare(SinhVienManager o1, SinhVienManager o2) {
        //Sắp xếp tăng dần theo học kỳ
        return o1.getDiemThi().getHocKi().compareTo(o2.getDiemThi().getHocKi());
    }

}
