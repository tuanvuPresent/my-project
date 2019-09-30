/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import my.crud.controller.DataController;
import my.crud.controller.SortByDiem;
import my.crud.controller.SortByDiemTb;
import my.crud.controller.SortByHocKy;
import my.crud.controller.UltilityController;
import my.crud.model.SinhVienManager;

/**
 *
 * @author NguyenTuanVu
 */
public class pnlThongKe extends JPanel implements ActionListener {

    private JScrollPane jScrollPane;
    private JTable table;
    private DefaultTableModel tableModel;

    private JTextField tfTimKiemId;
    private JLabel lbKqDiemTb;
    private JButton btnUpdate;

    private JComboBox comboBoxSx;
    private DefaultComboBoxModel boxModelSx;

    public pnlThongKe() {
        init();
        //Thêm Actionlistener
        tfTimKiemId.addActionListener(this);
        comboBoxSx.addActionListener(this);
        //Mở dữ liệu từ file lên
        openListQLSV();
    }

    private void init() {
        this.setLayout(null);
        addComponent();
        addTable();
    }

    private void addComponent() {
        tfTimKiemId = new JTextField("Tìm Kiếm");
        tfTimKiemId.setBounds(10, 10, 100, 30);
        this.add(tfTimKiemId);

        //add Label
        lbKqDiemTb = new JLabel("Chú ý: nếu tìm đúng MSSV thì mới thấy điểm trung bình");
        lbKqDiemTb.setBounds(250, 10, 350, 30);
        this.add(lbKqDiemTb);
        //add Combobox Sắp xếp
        boxModelSx = new DefaultComboBoxModel();
        comboBoxSx = new JComboBox(boxModelSx);
        comboBoxSx.setBounds(650, 10, 150, 30);
        //cho dữ liệu vào combobox
        boxModelSx.addElement("Sx theo học kỳ");
        boxModelSx.addElement("Sx theo điểm chữ");
        boxModelSx.addElement("Sx theo điểm tb");
        this.add(comboBoxSx);
    }

    private void addTable() {
        table = new JTable();
        tableModel = new DefaultTableModel();
        table.setModel(tableModel);
        //đưa dữ liệu vào bảng chỉ cần đưa dữ liệu vào tableModel
        tableModel.addColumn("Mã SV");
        tableModel.addColumn("Học kỳ");
        tableModel.addColumn("Mã HP");
        tableModel.addColumn("Tên HP");
        tableModel.addColumn("Tín chỉ");
        tableModel.addColumn("Điểm GK");
        tableModel.addColumn("Điểm CK");
        tableModel.addColumn("Điểm trung bình");
        tableModel.addColumn("Điểm Chữ");
        //cho table vào JscrollPanle
        jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(10, 50, 800, 340);
        this.add(jScrollPane);
    }

    //===================================================================================
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == tfTimKiemId) {
            timKiem();
        }
        if (e.getSource() == comboBoxSx) {
            sapXep();
        }
    }

    private void timKiem() {
        //Bước 1: Tìm kiếm
        ArrayList<SinhVienManager> list = new ArrayList<SinhVienManager>();
        list = controller.findSVManager(listSvManage, tfTimKiemId.getText());
        //Bước 2: Hiện lên bảng
        controller.clearOldDataInTableModel(tableModel);
        controller.addToTableTranscript(list, tableModel);
        //Bước 3 : hiện điểm trung bình các môn nếu tìm đúng
        double dtb = controller.diemTrungBinhCacMon(list, tfTimKiemId.getText());
        if (dtb != -1) {
            lbKqDiemTb.setText("Điểm trung bình của " + tfTimKiemId.getText() + " là: " + dtb);
        }
    }

    private void sapXep() {
        //Bước 1: Sắp xếp
        int index = comboBoxSx.getSelectedIndex();
        switch (index) {
            case 0:
                Collections.sort(listSvManage, new SortByHocKy());
                break;

            case 1:
                Collections.sort(listSvManage, new SortByDiem());
                break;
            case 2:
                Collections.sort(listSvManage, new SortByDiemTb());      //Sắp xếp
                break;
        }
        //Bước 2: Xóa dữ liệu cũ và hiện thông tin lên bảng
        controller.clearOldDataInTableModel(tableModel);       //xóa data
        controller.addToTableTranscript(listSvManage, tableModel);  //Thêm vào table 
    }

    private void openListQLSV() {
        //Đọc dữ liệu từ file
        listSvManage = dataController.readDS("QLSV.DAT");
        //Thêm vào table 
        controller.addToTableTranscript(listSvManage, tableModel);
    }

    private ArrayList<SinhVienManager> listSvManage = new ArrayList<>();
    private DataController dataController = new DataController();
    private UltilityController controller = new UltilityController();
}
