/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controler.Ctr;

/**
 *
 * @author NguyenTuanVu
 */
public class FrameDangNhap extends JFrame implements ActionListener {

	private JTextField tfUsername;
	private JPasswordField jPassword;
	private JButton btnLogin, btnRegister;

	public FrameDangNhap() {
		Ctr.connectSql();
		init();
		btnLogin.addActionListener(this);
		btnRegister.addActionListener(this);
	}

	private void init() {
		// TODO Auto-generated method stub
		this.setTitle("Đăng nhập");
		this.setLayout(null);
		this.setSize(330, 250);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addComponent();
	}

	private void addComponent() {
		tfUsername = new HintTextField("User name");
		tfUsername.setBounds(60, 50, 200, 40);
		this.add(tfUsername);

		jPassword = new JPasswordField();
		jPassword.setBounds(60, 90, 200, 40);
		this.add(jPassword);

		btnLogin = new JButton("Login");
		btnLogin.setBounds(60, 140, 80, 30);
		this.add(btnLogin);

		btnRegister = new JButton("Register");
		btnRegister.setBounds(160, 140, 100, 30);
		this.add(btnRegister);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnRegister) {
			new FrameDangKy().setVisible(true);
		}
		if (e.getSource() == btnLogin) {
			try {
				Ctr.signin(tfUsername, jPassword);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Lỗi kết nối");
			}
		}
	}

	public static void main(String[] args) {
		new FrameDangNhap().setVisible(true);
	}

}
