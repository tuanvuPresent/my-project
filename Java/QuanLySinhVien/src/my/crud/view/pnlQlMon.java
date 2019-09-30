/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import my.crud.controller.DataController;
import my.crud.controller.MyException;
import my.crud.controller.UltilityController;
import my.crud.model.MonHoc;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class pnlQlMon extends JPanel implements ActionListener {

    private JLabel lbId;
    private JLabel lbTenMon;

    private JButton btnAdd, btnEdit, btnDel;
    private JTextField tfMaMon, tfTenMon, tfSoChi, tfTimKiem;
    private JLabel lb[] = new JLabel[3];
    private String nameLabel[] = {"Mã môn(*)", "Tên môn(*)", "Số tín chỉ(*)"};

    private JScrollPane jScrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public pnlQlMon() {
        init();
        //Thêm ActionListener
        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        btnEdit.addActionListener(this);
        tfTimKiem.addActionListener(this);
        tableClick();
        addActionKey();
        //Đọc dữ liệu từ file
        openListMonHoc();
    }

    private void init() {
        this.setLayout(null);
        addComponent();
        addTable();
    }

    private void addComponent() {
        for (int i = 0; i < 3; i++) {
            lb[i] = new JLabel(nameLabel[i]);
            lb[i].setBounds(10, 10 + 40 * i, 80, 30);
            this.add(lb[i]);
        }

        tfMaMon = new JTextField();
        tfMaMon.setBounds(90, 10, 150, 30);
        this.add(tfMaMon);

        tfTenMon = new JTextField();
        tfTenMon.setBounds(90, 50, 150, 30);
        this.add(tfTenMon);

        tfSoChi = new JTextField();
        tfSoChi.setBounds(90, 90, 150, 30);
        this.add(tfSoChi);

        tfTimKiem = new JTextField("Tìm kiếm");
        tfTimKiem.setBounds(310, 10, 200, 30);
        this.add(tfTimKiem);
        //thêm button
        btnAdd = new JButton("Thêm");
        btnAdd.setBounds(10, 300, 90, 30);
        this.add(btnAdd);

        btnEdit = new JButton("Sửa");
        btnEdit.setBounds(110, 300, 90, 30);
        this.add(btnEdit);

        btnDel = new JButton("Xóa");
        btnDel.setBounds(210, 300, 90, 30);
        this.add(btnDel);
    }

    private void addTable() {
        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        //đưa dữ liệu vào bảng chỉ cần đưa dữ liệu vào tableModel
        tableModel.addColumn("ID");
        tableModel.addColumn("Tên môn");
        tableModel.addColumn("Số tín chỉ");
        //cho table vào JscrollPanle
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(310, 40, 500, 350);
        this.add(jScrollPane);
    }

    private void addActionKey() {

        tfSoChi.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    MonHoc monHoc = new MonHoc();
                    monHoc.setSoTinChi(Integer.parseInt(tfSoChi.getText()));
                    tfSoChi.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } catch (Exception ex) {
                    tfSoChi.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });
    }

    //====================================Action================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            btnAdd();
        }
        if (e.getSource() == tfTimKiem) {
            timKiem();
        }
        if (e.getSource() == btnDel) {
            btnDel();
        }
        if (e.getSource() == btnEdit) {
            btnEditClick();
        }
    }

    private void tableClick() {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Đọc dữ liệu từ bảng
                int index = table.getSelectedRow();
                String s[] = new String[2];
                for (int i = 0; i < 2; i++) {
                    s[i] = (String) tableModel.getValueAt(index, i);
                }
                int soTinChi = (int) tableModel.getValueAt(index, 2);
                //Đưa lên textFiled
                tfMaMon.setText(s[0]);
                tfTenMon.setText(s[1]);
                tfSoChi.setText(Integer.toString(soTinChi));
                //Lưu vào monHocOld để sửa nếu cần
                idMonHocOld = s[0];
            }
        });
    }

    private void btnEditClick() {
        /*
         //xóa môn học cũ đi và thêm môn học mới vào
         //Sửa lại trong listSvManage     
         //Lưu lại vào file
         //cập nhật lại lên bảng
         */
        //Kiểm tra id đã tồn tại chưa
        MyException exception = new MyException();
        if (!exception.idMonHocTrungNhau(listMonHoc, tfMaMon.getText())) {
            JOptionPane.showMessageDialog(null, "Mã môn đã tồn tại vui lòng nhập mã môn khác");
            return;
        }
        if (idMonHocOld == null) {
            return;
        }
        //Bước 1
        MonHoc monHoc = new MonHoc();
        try {
            monHoc.setIdMon(tfMaMon.getText());
            monHoc.setTenMon(tfTenMon.getText());
            monHoc.setSoTinChi(Integer.parseInt(tfSoChi.getText()));

            listMonHoc.add(monHoc);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Bạn nhập sai định dạng");
            return;
        }
        //xóa môn cũ đi
        String idMonHoc = tfMaMon.getText();
        listMonHoc = controller.removeMh(listMonHoc, idMonHocOld);
        //Bước 2: Sửa lại trong listSinhVienManage
        listSvManage = dataController.readDS("QLSV.DAT");
        controller.EditMonHoc(listSvManage, monHoc, idMonHocOld);
        //Lưu lại trong file
        saveListMonHoc();
        saveListSvManage();
        JOptionPane.showMessageDialog(null, "Sửa thành công id mới là: " + idMonHoc);
        idMonHocOld = null;
        //xóa data cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
    }

    private void btnDel() {
        /*
         Bước 1: Xóa dữ liệu trong ListMonHoc và trong listSvManage
         Bước 2: Xóa dữ liệu trên bảng
         Bước 3: lưu xuống file
         Bước 4: Cập nhật lại lên bảng
         */
        int index = -1;
        //Bước 1:
        if (table.getSelectedRow() != -1) {
            index = table.getSelectedRow();
        }
        if (index == -1) {
            return;
        }
        String idMonHoc = (String) tableModel.getValueAt(index, 0);
        listSvManage = dataController.readDS("QLSV.DAT");
        listSvManage = controller.DelMonHoc(listSvManage, idMonHoc);
        listMonHoc = controller.removeMh(listMonHoc, idMonHoc);
        //Bước 2:
        tableModel.removeRow(index);
        //Bước 3
        saveListMonHoc();
        saveListSvManage();
        //xóa data cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
        JOptionPane.showMessageDialog(null, "Xóa thành công");
    }

    private void timKiem() {
        ArrayList<MonHoc> result = new ArrayList<>();

        result = controller.findMH(listMonHoc, tfTimKiem.getText());
        //Hiện lên bảng
        controller.clearOldDataInTableModel(tableModel);
        controller.addListMhToTable(result, tableModel);
    }

    private void btnAdd() {
        //Kiểm tra id đã tồn tại chưa
        MyException exception = new MyException();
        if (!exception.idMonHocTrungNhau(listMonHoc, tfMaMon.getText())) {
            JOptionPane.showMessageDialog(null, "Mã môn đã tồn tại vui lòng nhập mã môn khác");
            return;
        }
        //Bước 1: Kiểm tra định dạng
        MonHoc monHoc = new MonHoc();
        try {
            monHoc.setIdMon(tfMaMon.getText());
            monHoc.setTenMon(tfTenMon.getText());
            monHoc.setSoTinChi(Integer.parseInt(tfSoChi.getText()));

            listMonHoc.add(monHoc);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Bạn nhập số tín chỉ sai định dạng");
            return;
        }
        //Bước 2: Lưu xuống file
        saveListMonHoc();
        //xóa data cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
        JOptionPane.showMessageDialog(null, "Thêm thành công");
    }

    private void openListMonHoc() {
        //Đọc dữ liệu từ file
        listMonHoc = dataController.readDS("MH.DAT");
        //Thêm vào bảng 
        controller.addListMhToTable(listMonHoc, tableModel);
    }

    private void saveListMonHoc() {
        try {
            dataController.writeDS(listMonHoc, "MH.DAT");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi file");
        }
    }

    private void saveListSvManage() {
        try {
            dataController.writeDS(listSvManage, "QLSV.DAT");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi file");
        }
    }
    private DataController dataController = new DataController();
    private ArrayList<MonHoc> listMonHoc = new ArrayList<>();
    private ArrayList<SinhVienManager> listSvManage = new ArrayList<>();
    private UltilityController controller = new UltilityController();
    private String idMonHocOld = null;
}
