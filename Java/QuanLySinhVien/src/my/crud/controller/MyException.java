/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import my.crud.model.MonHoc;
import my.crud.model.SinhVien;

/**
 *
 * @author NguyenTuanVu
 */
public class MyException {

    //pattern phân số
    private final String regexPS = "^[0-9]+/{1}+[0-9]+$";
    //Ten
    private final String regexTen = "^[^0-9]+$";
    //pattern ID
    private final String regexId = "^20[0-9]{2}[0-9]{4}$";
    //pattern sdt
    private final String regexSdt = "^0[0-9]{9}$";
    //pattern gmail
    private final String regexGamil = "^[a-zA-Z]+[a-z0-9]*@{1}+[a-zA-Z]+mail.com$";
    //pattern ngay sinh
    private final String regexNs = "^[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}$";

    public boolean isTen(String ten) {
        Pattern pattern = Pattern.compile(regexTen);           //biên dịch mẫu
        Matcher matcher = pattern.matcher(ten);                   //so sánh
        return matcher.find();                                    //tìm kiếm
    }

    public boolean isId(String id) {
        //Kiểm tra hợp lệ
        Pattern pattern = Pattern.compile(regexId);           //biên dịch mẫu
        Matcher matcher = pattern.matcher(id);                   //so sánh
        return matcher.find();                                    //tìm kiếm
    }

    public boolean isSdt(String sdt) {
        Pattern pattern = Pattern.compile(regexSdt);           //biên dịch mẫu
        Matcher matcher = pattern.matcher(sdt);                   //so sánh
        return matcher.find();                                    //tìm kiếm
    }

    public boolean isGmail(String gmail) {
        Pattern pattern = Pattern.compile(regexGamil);           //biên dịch mẫu
        Matcher matcher = pattern.matcher(gmail);                   //so sánh
        return matcher.find();                                    //tìm kiếm
    }

    public boolean isNs(String ngaySinh) {
        /*
         Bước 1: kiểm tra định dạng
         Bước 2: Kiểm tra hợp lệ
         */

        //Bước 1
        Pattern pattern = Pattern.compile(regexNs);           //biên dịch mẫu
        Matcher matcher = pattern.matcher(ngaySinh);                   //so sánh
        boolean isNs = matcher.find();                                    //tìm kiếm
        //Bước 2
        if (isNs) {
            String s[] = ngaySinh.split("\\/");
            int ngay = Integer.parseInt(s[0]);
            int thang = Integer.parseInt(s[1]);
            int nam = Integer.parseInt(s[2]);
            if (nam < 1000 || thang > 12 || thang <= 0) {
                return false;
            }
            //kiểm tra ngày trong tháng
            switch (thang) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    if (ngay <= 0 || ngay > 31) {
                        return false;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    if (ngay <= 0 || ngay > 30) {
                        return false;
                    }
                    break;
                case 2:
                    if (ngay <= 0 || ngay > 29) {
                        return false;
                    }
                    break;
            }
            return true;
        } else {
            return false;
        }

    }

    public boolean idSvTrungNhau(ArrayList<SinhVien> list, String idSvNew) {
        for (SinhVien sinhVien : list) {
            if (idSvNew.equals(sinhVien.getId())) {
                return false;
            }
        }
        return true;
    }

    public boolean idMonHocTrungNhau(ArrayList<MonHoc> list, String idMonHocNew) {
        for (MonHoc monHoc : list) {
            if (idMonHocNew.equals(monHoc.getIdMon())) {
                return false;
            }
        }
        return true;
    }
}
