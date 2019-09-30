/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.controller;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.table.DefaultTableModel;
import my.crud.model.MonHoc;
import my.crud.model.SinhVien;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class UltilityController {

    public ArrayList<SinhVienManager> findSVManager(ArrayList<SinhVienManager> list, String key) {
        /*
         Bước 1: Khởi tạo mẫu và dịch lại
         Bước 2: Tìm trong ArrayList nếu thấy thì thêm vào result 
         */
        ArrayList<SinhVienManager> result = new ArrayList<>();
        //Bước 1
        String regex = ".*" + key + ".*";
        Pattern pattern = Pattern.compile(regex);           //biên dịch mẫu
        //Bước 2
        for (int i = 0; i < list.size(); i++) {
            SinhVienManager sinhVienManager = list.get(i);
            Matcher matcher = pattern.matcher(sinhVienManager.getIdSv());
            if (matcher.find()) {
                result.add(sinhVienManager);
            }
        }
        return result;
    }

    public ArrayList<SinhVien> findSV(ArrayList<SinhVien> list, String key) {
        /*
         Bước 1: Khởi tạo mẫu và dịch lại
         Bước 2: Tìm trong ArrayList nếu thấy thì thêm vào result 
         */
        ArrayList<SinhVien> result = new ArrayList<>();
        //Khởi tạo mẫu
        String regex = ".*" + key + ".*";
        Pattern pattern = Pattern.compile(regex);           //biên dịch mẫu
        //Tìm và add vào result 
        for (int i = 0; i < list.size(); i++) {
            SinhVien sinhVien = list.get(i);
            Matcher matcher = pattern.matcher(sinhVien.getId());
            if (matcher.find()) {
                result.add(sinhVien);
            }
        }
        return result;
    }

    public ArrayList<MonHoc> findMH(ArrayList<MonHoc> list, String key) {
        /*
         Bước 1: Khởi tạo mẫu và dịch lại
         Bước 2: Tìm trong ArrayList nếu thấy thì thêm vào result 
         */
        ArrayList<MonHoc> result = new ArrayList<>();
        //Khởi tạo mẫu
        String regex = ".*" + key + ".*";
        Pattern pattern = Pattern.compile(regex);           //biên dịch mẫu
        //Tìm và add vào result 
        for (int i = 0; i < list.size(); i++) {
            MonHoc monHoc = list.get(i);
            Matcher matcher = pattern.matcher(monHoc.getIdMon());
            if (matcher.find()) {
                result.add(monHoc);
            }
        }
        return result;
    }

    public void clearOldDataInTableModel(DefaultTableModel tableModel) {
        //Xóa theo cách thứ nhât, hiệu suất làm việc thấp hơn
//        while (dtmSinhVien.getRowCount()>0) {
//            dtmSinhVien.removeRow(0);
//        }

        //Xóa theo cách 2, hiệu suất làm việc cao hơn
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount; i > 0; i--) {
            tableModel.removeRow(i - 1);
        }
    }

    public void addListSvToTable(ArrayList<SinhVien> list, DefaultTableModel tableModel) {
        for (SinhVien sinhVien : list) {
            Object data[] = {
                sinhVien.getId(),
                sinhVien.getTen(),
                sinhVien.getNs(),
                sinhVien.getGmail(),
                sinhVien.getSdt()
            };
            tableModel.addRow(data);
        }
    }

    public void addListMhToTable(ArrayList<MonHoc> list, DefaultTableModel tableModel) {
        for (MonHoc monHoc : list) {
            Object data[] = {
                monHoc.getIdMon(),
                monHoc.getTenMon(),
                monHoc.getSoTinChi()
            };
            tableModel.addRow(data);
        }
    }

    public void addListSvManageToTable(ArrayList<SinhVienManager> list,
            DefaultTableModel tableModel) {
        int i = 0;
        for (SinhVienManager sinhVienManager : list) {
            Object data[] = {
                i++,
                sinhVienManager.getIdSv(),
                sinhVienManager.getDiemThi().getHocKi(),
                sinhVienManager.getMonHoc().getIdMon(),
                sinhVienManager.getMonHoc().getTenMon(),
                sinhVienManager.getMonHoc().getSoTinChi(),
                sinhVienManager.getDiemThi().getDiemMid(),
                sinhVienManager.getDiemThi().getDiemFinal()
            };
            tableModel.addRow(data);
        }
    }

    public void addToTableTranscript(ArrayList<SinhVienManager> list,
            DefaultTableModel tableModel) {
        for (SinhVienManager sinhVienManager : list) {
            Object data[] = {
                sinhVienManager.getIdSv(),
                sinhVienManager.getDiemThi().getHocKi(),
                sinhVienManager.getMonHoc().getIdMon(),
                sinhVienManager.getMonHoc().getTenMon(),
                sinhVienManager.getMonHoc().getSoTinChi(),
                sinhVienManager.getDiemThi().getDiemMid(),
                sinhVienManager.getDiemThi().getDiemFinal(),
                sinhVienManager.getDiemThi().diemTb(),
                sinhVienManager.getDiemThi().diemChu()
            };
            tableModel.addRow(data);
        }
    }

    public double diemTrungBinhCacMon(ArrayList<SinhVienManager> list, String idSv) {
        /*
         Tính tổng =số tín chỉ * với điểm quy ra thang điểm 4
         ===> Chia cho tổng tín chỉ
         */
        double tong = 0;
        double soTinChi = 0;
        for (SinhVienManager sinhVienManager : list) {
            if (idSv.equals(sinhVienManager.getIdSv())) {
                tong = tong + sinhVienManager.getDiemThi().diemChuToSo()
                        * sinhVienManager.getMonHoc().getSoTinChi();
                soTinChi = soTinChi + sinhVienManager.getMonHoc().getSoTinChi();
            }
        }
        if (soTinChi == 0) {
            return -1;
        }
        return Math.ceil(100 * tong / soTinChi) / 100;
    }

    public ArrayList<SinhVienManager> DelSinhVien(ArrayList<SinhVienManager> list, String idSv) {
        /*
         Tìm trong ArrayListManager nếu trùng mã sinh viên 
         thì xóa tất cả idSv trùng 
         */
        for (int i = 0; i < list.size(); i++) {
            SinhVienManager sinhVienManager = list.get(i);
            if (idSv.equals(sinhVienManager.getIdSv())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    public ArrayList<SinhVienManager> DelMonHoc(ArrayList<SinhVienManager> list, String idMonHoc) {
        /*
         Tìm trong ArrayList nếu trùng mã môn học 
         thì xóa tất cả idMonHoc trùng
         */
        for (int i = 0; i < list.size(); i++) {
            SinhVienManager sinhVienManager = list.get(i);
            if (idMonHoc.equals(sinhVienManager.getMonHoc().getIdMon())) {
                list.remove(i);
                i--;
            }
        }
        return list;
    }

    public void EditSinhVien(ArrayList<SinhVienManager> list,
            String idSinhVienNew, String idSvOld) {
        /*
         Tìm trong ArrayList nếu trùng mã sinh viên thì sửa
         */
        for (int i = 0; i < list.size(); i++) {
            SinhVienManager sinhVienManager = list.get(i);
            if (idSvOld.equals(sinhVienManager.getIdSv())) {
                list.get(i).setIdSv(idSinhVienNew);
            }
        }
    }

    public void EditMonHoc(ArrayList<SinhVienManager> list,
            MonHoc monHocNew, String idMonHocOld) {
        /*
         Tìm trong ArrayList nếu trùng mã môn học thì sửa
         */
        for (int i = 0; i < list.size(); i++) {
            SinhVienManager sinhVienManager = list.get(i);
            if (idMonHocOld.equals(sinhVienManager.getMonHoc().getIdMon())) {
                list.get(i).setMonHoc(monHocNew);
            }
        }
    }

    public ArrayList<SinhVien> removeSv(ArrayList<SinhVien> list, String idSv) {
        /*
         Tìm trong ArrayListSinhVien nếu trùng mã sinh viên thì xóa 1 phần tử
         */
        for (int i = 0; i < list.size(); i++) {
            SinhVien sinhVien = list.get(i);
            if (idSv.equals(sinhVien.getId())) {
                list.remove(i);
                break;
            }
        }
        return list;
    }

    public ArrayList<MonHoc> removeMh(ArrayList<MonHoc> list, String idMonHoc) {
        /*
         Tìm trong ArrayList nếu trùng mã sinh viên thì xóa 1 phần tử
         */
        for (int i = 0; i < list.size(); i++) {
            MonHoc monHoc = list.get(i);
            if (idMonHoc.equals(monHoc.getIdMon())) {
                list.remove(i);
                break;
            }
        }
        return list;
    }

}
