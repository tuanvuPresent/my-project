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
import my.crud.model.SinhVien;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class pnlSinhVien extends JPanel implements ActionListener {

    private JLabel lb[] = new JLabel[5];
    private String[] nameLabel = {"ID(*)", "Tên(*)", "Ngày sinh(*)", "Gmail(*)", "Số ĐT(*)"};

    private JTextField tfId, tfTen, tfNs, tfGmail, tfSdt, tfTimKiem;
    private JButton btnAdd, btnEdit, btnDel;

    private JScrollPane jScrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    public pnlSinhVien() {
        init();
        //Thêm các Action
        addActionKey();
        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        btnEdit.addActionListener(this);
        tfTimKiem.addActionListener(this);
        tableClick();
        // đọc dữ từ file
        openListSinhVien();
    }

    public void init() {
        this.setLayout(null);
        addComponent();
        addTable();
    }

    private void addComponent() {
        for (int i = 0; i < 5; i++) {
            lb[i] = new JLabel(nameLabel[i]);
            lb[i].setBounds(10, 10 + 40 * i, 80, 30);
            this.add(lb[i]);
        }
        //add TextField
        tfId = new JTextField();
        tfId.setBounds(90, 10, 150, 30);
        this.add(tfId);

        tfTen = new JTextField();
        tfTen.setBounds(90, 50, 150, 30);
        this.add(tfTen);

        tfNs = new JTextField();
        tfNs.setBounds(90, 90, 150, 30);
        this.add(tfNs);

        tfGmail = new JTextField();
        tfGmail.setBounds(90, 130, 150, 30);
        this.add(tfGmail);

        tfSdt = new JTextField();
        tfSdt.setBounds(90, 170, 150, 30);
        this.add(tfSdt);

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
        tableModel.addColumn("Tên");
        tableModel.addColumn("Ngày sinh");
        tableModel.addColumn("Gmail");
        tableModel.addColumn("Số điện thoại");
        //cho table vào JscrollPanle
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(310, 40, 500, 350);
        this.add(jScrollPane);
    }

    //===========================================================================
    private void addActionKey() {
        tfId.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                MyException myException = new MyException();
                if (myException.isId(tfId.getText())) {
                    tfId.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    tfId.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

        tfTen.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                MyException myException = new MyException();
                if (myException.isTen(tfTen.getText())) {
                    tfTen.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    tfTen.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

        tfNs.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                MyException myException = new MyException();
                if (myException.isNs(tfNs.getText())) {
                    tfNs.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    tfNs.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

        tfGmail.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                MyException myException = new MyException();
                if (myException.isGmail(tfGmail.getText())) {
                    tfGmail.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    tfGmail.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

        tfSdt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                MyException myException = new MyException();
                if (myException.isSdt(tfSdt.getText())) {
                    tfSdt.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } else {
                    tfSdt.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            btnAddClick();
        }
        if (e.getSource() == btnDel) {
            btnDelClick();
        }
        if (e.getSource() == btnEdit) {
            btnEditClick();
        }
        if (e.getSource() == tfTimKiem) {
            timKiem();
        }
    }

    private void tableClick() {
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Đọc dữ liệu từ bảng
                int index = table.getSelectedRow();
                String s[] = new String[5];
                for (int i = 0; i < 5; i++) {
                    s[i] = (String) tableModel.getValueAt(index, i);
                }
                //Đưa lên textFiled
                tfId.setText(s[0]);
                tfTen.setText(s[1]);
                tfNs.setText(s[2]);
                tfGmail.setText(s[3]);
                tfSdt.setText(s[4]);
                //Lưu lại idSinhVienOld để sửa nếu cần
                idSinhVienOld = tfId.getText();
            }
        });
    }

    private void btnEditClick() {
        /*
         //Kiểm tra định dạng
         //xóa sinh viên cũ đi và thêm sinh viên mới vào
         //Sửa lại trong listSvManage     
         //Lưu lại vào file
         //Cập nhật lại lên bảng
         */

        //Kiểm tra id đã tồn tại chưa
        MyException exception = new MyException();
        if (!exception.idSvTrungNhau(listSv, tfId.getText())) {
            JOptionPane.showMessageDialog(null, "ID đã tồn tại vui lòng nhập ID khác");
            return;
        }
        //Bước 1
        if (idSinhVienOld == null) {
            return;
        }
        SinhVien sinhVien = new SinhVien();
        try {
            sinhVien.setId(tfId.getText());
            sinhVien.setTen(tfTen.getText());
            sinhVien.setNs(tfNs.getText());
            sinhVien.setGmail(tfGmail.getText());
            sinhVien.setSdt(tfSdt.getText());

            listSv.add(sinhVien);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Bạn nhập sai định dạng");
            return;
        }
        //Bước 2: xóa sinh viên cũ đi
        String idSvNew = tfId.getText();
        listSv = controller.removeSv(listSv, idSinhVienOld);
        //Sửa lại trong listSinhVienManage
        listSvManage = dataController.readDS("QLSV.DAT");
        controller.EditSinhVien(listSvManage, idSvNew, idSinhVienOld);
        //Lưu lại trong file
        saveListSinhvien();
        saveListSvManage();
        JOptionPane.showMessageDialog(null, "Sửa thành công id mới là: " + idSvNew);
        idSinhVienOld = null;
        //xóa dữ liệu cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListSinhVien();
    }

    private void btnDelClick() {
        /*
         Bước 1: Xóa dữ liệu trên bảng
         Bước 2: Xóa dữ liệu trong ListSv và trong listSvManage
         Bước 3: lưu xuống file
         Bước 4: cập nhật lại
         */
        int index = -1;
        //Bước 1:
        if (table.getSelectedRow() != -1) {
            index = table.getSelectedRow();
        }
        //Bước 2: 
        if (index == -1) {
            return;
        }
        String idSv = (String) tableModel.getValueAt(index, 0);
        tableModel.removeRow(index);
        listSvManage = dataController.readDS("QLSV.DAT");
        listSvManage = controller.DelSinhVien(listSvManage, idSv);
        listSv = controller.removeSv(listSv, idSv);
        //Bước 3
        saveListSinhvien();
        saveListSvManage();
        //xóa dữ liệu cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListSinhVien();
        JOptionPane.showMessageDialog(null, "Xóa thành công");
    }

    private void btnAddClick() {
        //Kiểm tra id đã tồn tại chưa
        MyException exception = new MyException();
        if (!exception.idSvTrungNhau(listSv, tfId.getText())) {
            JOptionPane.showMessageDialog(null, "ID đã tồn tại vui lòng nhập ID khác");
            return;
        }
        //Kiểm tra định dạng
        SinhVien sinhVien = new SinhVien();
        try {
            sinhVien.setId(tfId.getText());
            sinhVien.setTen(tfTen.getText());
            sinhVien.setNs(tfNs.getText());
            sinhVien.setGmail(tfGmail.getText());
            sinhVien.setSdt(tfSdt.getText());

            listSv.add(sinhVien);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Bạn nhập sai định dạng");
            return;
        }
        //Luu xuong file
        saveListSinhvien();
        //xóa dữ liệu cũ và cập nhật lại
        controller.clearOldDataInTableModel(tableModel);
        openListSinhVien();
        JOptionPane.showMessageDialog(null, "Thêm thành công");
    }

    private void timKiem() {
        //Tìm theo mã sinh viên
        ArrayList<SinhVien> result = new ArrayList<SinhVien>();
        result = controller.findSV(listSv, tfTimKiem.getText());
        //Xóa hết dữ liệu cũ + hiện dữ liệu tìm được lên bảng
        controller.clearOldDataInTableModel(tableModel);
        controller.addListSvToTable(result, tableModel);
    }

    private void saveListSinhvien() {
        //SinhVien(String id, String ten, String ns, String gmail, String sdt)
        try {
            dataController.writeDS(listSv, "SV.DAT");
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

    private void openListSinhVien() {
        //Đọc dữ liệu từ file
        listSv = dataController.readDS("SV.DAT");
        //Thêm vào bảng 
        controller.addListSvToTable(listSv, tableModel);
    }

    private ArrayList<SinhVien> listSv = new ArrayList<>();
    private ArrayList<SinhVienManager> listSvManage = new ArrayList<>();
    private DataController dataController = new DataController();
    private UltilityController controller = new UltilityController();
    private String idSinhVienOld = null;

}
