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
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import my.crud.controller.DataController;
import my.crud.controller.UltilityController;
import my.crud.model.DiemThi;
import my.crud.model.MonHoc;
import my.crud.model.SinhVien;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class pnlQlDiem extends JPanel implements ActionListener {

    private JLabel lb[] = new JLabel[3];
    private String nameLabel[] = {"Học kỳ(*)", "Điểm giữa kỳ(*)", "Điểm cuối kỳ(*)"};

    private JButton btnAdd, btnEdit, btnDel;
    private JTextField tfDiemGk, tfDiemCk, tfHocKy;

    private JComboBox comboBoxId, comboBoxMaMon;

    private JScrollPane jScrollPaneTable;
    private JTable table;
    private DefaultTableModel tableModel;
    private DefaultComboBoxModel boxModelId;
    private DefaultComboBoxModel boxModelMaMon;

    public pnlQlDiem() {
        init();

        //Thêm ActionListener   
        btnAdd.addActionListener(this);
        btnDel.addActionListener(this);
        btnEdit.addActionListener(this);
        tableClick();
        addActionKey();
        //Đọc dữ liệu từ file
        openListMonHoc();
        openListSinhVien();
        openListQLSV();
    }

    private void init() {
        this.setLayout(null);
        addComponent();
        addTable();
        addComboBox();
    }

    private void addComponent() {
        //add Label
        for (int i = 0; i < 3; i++) {
            lb[i] = new JLabel(nameLabel[i]);
            lb[i].setBounds(10, 100 + i * 40, 100, 30);
            this.add(lb[i]);
        }
        //add TextField          
        tfHocKy = new JTextField();
        tfHocKy.setBounds(110, 100, 150, 30);
        this.add(tfHocKy);

        tfDiemGk = new JTextField();
        tfDiemGk.setBounds(110, 140, 150, 30);
        this.add(tfDiemGk);

        tfDiemCk = new JTextField();
        tfDiemCk.setBounds(110, 180, 150, 30);
        this.add(tfDiemCk);

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
        tableModel.addColumn("STT");
        tableModel.addColumn("Mã SV");
        tableModel.addColumn("Học kỳ");
        tableModel.addColumn("Mã HP");
        tableModel.addColumn("Tên HP");
        tableModel.addColumn("Tín chỉ");
        tableModel.addColumn("Điểm Gk");
        tableModel.addColumn("Điểm Ck");
        //cho table vào JscrollPanle
        jScrollPaneTable = new JScrollPane(table);
        jScrollPaneTable.setBounds(310, 40, 500, 350);
        this.add(jScrollPaneTable);
    }

    private void addComboBox() {
        boxModelId = new DefaultComboBoxModel();
        comboBoxId = new JComboBox(boxModelId);
        comboBoxId.setBounds(10, 40, 90, 30);
        comboBoxId.setBackground(Color.white);
        //Đưa dũ liệu vào combobox là đưa vào boxModel
        this.add(comboBoxId);

        boxModelMaMon = new DefaultComboBoxModel();
        comboBoxMaMon = new JComboBox(boxModelMaMon);
        comboBoxMaMon.setBounds(110, 40, 90, 30);
        comboBoxMaMon.setBackground(Color.white);
        //Đưa dũ liệu vào combobox là đưa vào boxModel
        this.add(comboBoxMaMon);
    }

    private void addActionKey() {
        tfDiemGk.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    DiemThi diemThi = new DiemThi();
                    diemThi.setDiemMid(Double.parseDouble(tfDiemGk.getText()));
                    tfDiemGk.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } catch (Exception ex) {
                    tfDiemGk.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });
        tfDiemCk.addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    DiemThi diemThi = new DiemThi();
                    diemThi.setDiemFinal(Double.parseDouble(tfDiemCk.getText()));
                    tfDiemCk.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                } catch (Exception ex) {
                    tfDiemCk.setBorder(BorderFactory.createLineBorder(Color.red));
                }
            }
        });

    }

    //===================================Action============================================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            addDiem();
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
                String s[] = new String[3];
                String hocKy = (String) tableModel.getValueAt(index, 2);
                double diemGk = (double) tableModel.getValueAt(index, 6);
                double diemCk = (double) tableModel.getValueAt(index, 7);
                //Đưa lên textFiled
                tfHocKy.setText(hocKy);
                tfDiemGk.setText(Double.toString(diemGk));
                tfDiemCk.setText(Double.toString(diemCk));
                //Lưu vào diemThiOld để sửa nếu cần
                stt = (int) tableModel.getValueAt(index, 0);
            }
        });
    }

    private void btnEditClick() {
        /*
         kiểm tra dữ liệu đã bị thay đỏi chưa
         Lấy dữ liệu ra
         Cập nhật lại
         */
        //Bước 1
        if (stt == -1) {
            JOptionPane.showMessageDialog(null, "Dữ liệu đã bị thay đổi");
            return;
        }
        //bước 2
        SinhVienManager sinhVienManager = listSvManage.get(stt);
        try {
            //Bước 1: Kiểm tra định dạng
            DiemThi diemThi = new DiemThi();
            diemThi.setHocKi(tfHocKy.getText());
            diemThi.setDiemMid(Double.parseDouble(tfDiemGk.getText()));
            diemThi.setDiemFinal(Double.parseDouble(tfDiemCk.getText()));

            sinhVienManager.setDiemThi(diemThi);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Sửa thất bại");
            return;
        }
        //Bước 3:
        saveListSvManage();
        //xóa dữ liệu cũ và cập nhật lại lên bảng
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
        openListQLSV();
        openListSinhVien();
        JOptionPane.showMessageDialog(null, "Sửa thành công");
    }

    private void btnDel() {
        /*
         Bước 1: Cập nhật lại dữ liệu
         Bước 2: Xóa dữ liệu trong ListSv và trong listSvManage
         Bước 3: lưu xuống file
         */
        //Bước 1:
        if (stt == -1) {
            JOptionPane.showMessageDialog(null, "Dữ liệu không tồn tại");
            return;
        }
        listSvManage.remove(stt);
        //Bước 3 : Lưu lại  
        saveListSvManage();
        //xóa dữ liệu cũ và cập nhật lại lên bảng
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
        openListQLSV();
        openListSinhVien();
        JOptionPane.showMessageDialog(null, "Xóa thành công");
    }

    private void addDiem() {
        //Bước 1: Kiểm tra định dạng
        int id = comboBoxId.getSelectedIndex();
        int maMon = comboBoxMaMon.getSelectedIndex();
        SinhVienManager sinhVienManager = new SinhVienManager();
        DiemThi diemThi = new DiemThi();
        try {
            MonHoc monHoc = listMonHoc.get(maMon);

            diemThi.setHocKi(tfHocKy.getText());
            diemThi.setDiemMid(Double.parseDouble(tfDiemGk.getText()));
            diemThi.setDiemFinal(Double.parseDouble(tfDiemCk.getText()));
            //Tạo mới sinhvienManage
            sinhVienManager.setIdSv(listSv.get(id).getId());
            sinhVienManager.setDiemThi(diemThi);
            sinhVienManager.setMonHoc(monHoc);

            listSvManage.add(sinhVienManager);
            JOptionPane.showMessageDialog(null, "Thêm thành công");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Thêm thất bại");
            return;
        }
        //Bước 2: lưu môn học  vào ArrayList và lưu xuống file
        saveListSvManage();
        //xóa dữ liệu cũ và cập nhật lại 
        controller.clearOldDataInTableModel(tableModel);
        openListMonHoc();
        openListQLSV();
        openListSinhVien();
    }

    private void openListMonHoc() {
        boxModelMaMon.removeAllElements();
        //Đọc dữ liệu từ file
        listMonHoc = dataController.readDS("MH.DAT");
        //Thêm vào comboboxMaMon
        for (int i = 0; i < listMonHoc.size(); i++) {
            boxModelMaMon.addElement(listMonHoc.get(i).getIdMon());
        }
    }

    private void openListSinhVien() {
        boxModelId.removeAllElements();
        //Đọc dữ liệu từ file
        listSv = dataController.readDS("SV.DAT");
        //Thêm vào comboboxID 
        for (int i = 0; i < listSv.size(); i++) {
            boxModelId.addElement(listSv.get(i).getId());
        }
    }

    private void openListQLSV() {
        //Đọc dữ liệu từ file
        listSvManage = dataController.readDS("QLSV.DAT");
        //Thêm vào table 
        controller.addListSvManageToTable(listSvManage, tableModel);
    }

    private void saveListSvManage() {
        try {
            dataController.writeDS(listSvManage, "QLSV.DAT");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi ghi file");
        }
    }

    private ArrayList<SinhVienManager> listSvManage = new ArrayList<>();
    private ArrayList<SinhVien> listSv = new ArrayList<>();
    private ArrayList<MonHoc> listMonHoc = new ArrayList<>();
    private DataController dataController = new DataController();
    private UltilityController controller = new UltilityController();
    private int stt = -1;
}
