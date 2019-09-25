/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author NguyenTuanVu
 */
public class MyFrame implements ActionListener {

    private JMenuBar menuBar;
    private JMenu jmFile, jmEdit;
    private JMenuItem itemAbout, itemExit, itemNewGame;

    private JFrame frame;

    public MyFrame() {
        frame = new JFrame("My Game Caro");
        frame.setSize(1060, 650);
//        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add component      
        frame.add(new PanelMain());
        AddJmenuBar();

        frame.setVisible(true);
    }

    private void AddJmenuBar() {
        // add menu      
        menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        menuBar.add(jmFile);

        jmEdit = new JMenu("Edit");
        jmEdit.setMnemonic(KeyEvent.VK_E);
        menuBar.add(jmEdit);
        //add Item menu
        itemNewGame = new JMenuItem("NewGame");
        itemNewGame.setMnemonic(KeyEvent.VK_E);
        jmFile.add(itemNewGame);

        itemAbout = new JMenuItem("About");
        itemAbout.setMnemonic(KeyEvent.VK_A);
        jmEdit.add(itemAbout);

        itemExit = new JMenuItem("Exit");
        itemExit.setMnemonic(KeyEvent.VK_E);
        jmEdit.add(itemExit);

        itemAbout.addActionListener(this);
        itemExit.addActionListener(this);
        itemNewGame.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == itemAbout) {
            JOptionPane.showMessageDialog(null,
                    "Copyright: Nguyễn Tuấn Vũ\n"
                    + "GameCaro: Version 1.0\n"
                    + "Gmail: Tuanvubk96@gmail.com",
                    "About", 1);
        }
        if (e.getSource() == itemExit) {
            exit();
        }

        if (e.getSource() == itemNewGame) {
            newGame();
        }
    }

    private void exit() {
        int result = JOptionPane.showConfirmDialog(null, "Bạn có muốn thoát không", "Thông báo", 2);
        if (result == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    private void newGame() {

    }

}
