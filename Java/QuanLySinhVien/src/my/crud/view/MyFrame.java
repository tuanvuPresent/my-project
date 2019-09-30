/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.crud.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author NguyenTuanVu
 */
public class MyFrame extends JFrame implements ActionListener {

    private JTabbedPane tabbedPane;
    private JMenu jMenu;
    private JMenuBar menuBar;
    private JMenu jmFile, jmEdit;
    private JMenuItem itemAbout, itemExit, itemSvMh, itemBangDiem;

    public MyFrame() {
        init();
        addMenu();

        itemAbout.addActionListener(this);
        itemExit.addActionListener(this);
        itemSvMh.addActionListener(this);
        itemBangDiem.addActionListener(this);
    }

    private void init() {
        this.setTitle("My Crud");
        this.setSize(830, 470);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //add component
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản lí sinh viên", new pnlSinhVien());
        tabbedPane.addTab("Quản lí môn học", new pnlQlMon());
        this.add(tabbedPane);
    }

    private void addMenu() {
        // add menu      
        menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);

        jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        menuBar.add(jmFile);

        jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic(KeyEvent.VK_E);
        menuBar.add(jmEdit);

        //add Item menu      
        itemSvMh = new JMenuItem("Quản lý sinh viên và môn học");
        itemSvMh.setMnemonic(KeyEvent.VK_Q);
        jmFile.add(itemSvMh);

        itemBangDiem = new JMenuItem("Quản lý điểm");
        itemBangDiem.setMnemonic(KeyEvent.VK_Q);
        jmFile.add(itemBangDiem);

        itemAbout = new JMenuItem("About");
        itemAbout.setMnemonic(KeyEvent.VK_A);
        jmEdit.add(itemAbout);

        itemExit = new JMenuItem("Exit");
        itemExit.setMnemonic(KeyEvent.VK_E);
        jmEdit.add(itemExit);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemAbout) {
            JOptionPane.showMessageDialog(null,
                    "Chương trình: Quản lý điểm sinh viên\n"
                    + "Version 1.0\n"
                    + "Copyright: Nguyễn Tuấn Vũ\n"
                    + "Gmail: Tuanvubk96@gmail.com",
                    "About", 1);
        }

        if (e.getSource() == itemExit) {
            System.exit(0);
        }
        if (e.getSource() == itemSvMh) {
            qlSinhMonHoc();
        }
        if (e.getSource() == itemBangDiem) {
            bangDiem();
        }
    }

    private void qlSinhMonHoc() {
        // Xóa Tab hiện tại đi và thêm 2 pnlSinh và pnlMonHoc
        this.remove(tabbedPane);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản lý sinh viên", new pnlSinhVien());
        tabbedPane.addTab("Quản lí môn học", new pnlQlMon());
        tabbedPane.repaint();
        tabbedPane.revalidate();
        this.add(tabbedPane);
        this.validate();
        this.repaint();
    }

    private void bangDiem() {
        /*
         Xóa Tab hiện tại đi và thêm 2 pnlQlDiem và pnlThongKe
         */
        this.remove(tabbedPane);
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Quản lí điểm", new pnlQlDiem());
        tabbedPane.addTab("Thống kê", new pnlThongKe());
        tabbedPane.repaint();
        tabbedPane.revalidate();
        this.add(tabbedPane);
        this.validate();
        this.repaint();
    }

}
