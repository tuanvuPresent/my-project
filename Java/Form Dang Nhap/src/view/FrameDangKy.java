/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controler.Ctr;
import model.GioiTinh;
import model.User;

/**
 *
 * @author NguyenTuanVu
 */
public class FrameDangKy extends JFrame implements ActionListener {

	private JTextField tfName, tfid, tfPass, tfRePass, tfns, tfGioiTinh;
	private JButton btnSignup;
	private JRadioButton r1, r2;

	public FrameDangKy() {
		this.setTitle("Đăng ký tài khoản");
		this.setLayout(null);
		this.setSize(330, 430);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		addComponent();
		btnSignup.addActionListener(this);
	}

	private void addComponent() {
		tfName = new JTextField();
		tfName.setBounds(100, 30, 180, 40);
		this.add(tfName);

		tfid = new JTextField();
		tfid.setBounds(100, 70, 180, 40);
		this.add(tfid);

		tfPass = new JTextField();
		tfPass.setBounds(100, 110, 180, 40);
		this.add(tfPass);

		tfRePass = new JTextField();
		tfRePass.setBounds(100, 150, 180, 40);
		this.add(tfRePass);

		tfns = new JTextField();
		tfns.setBounds(100, 190, 180, 40);
		this.add(tfns);

		r1 = new JRadioButton("FeMale");
		r1.setBounds(100, 230, 100, 50);
		r2 = new JRadioButton("Male");
		r2.setBounds(200, 230, 100, 50);
		r2.setSelected(true);
		ButtonGroup group = new ButtonGroup();
		group.add(r1);
		group.add(r2);
		this.add(r1);
		this.add(r2);
		// add Label
		JLabel lb[] = new JLabel[6];
		String nameLabel[] = { "Tên", "Tài khoản", "Mật khẩu", "Nhập lại", "Năm sinh", "Giới tính" };
		for (int i = 0; i < 6; i++) {
			lb[i] = new JLabel(nameLabel[i]);
			lb[i].setBounds(10, 30 + 40 * i, 90, 40);
			this.add(lb[i]);
		}

		btnSignup = new JButton("Đăng ký");
		btnSignup.setBounds(100, 280, 110, 40);
		this.add(btnSignup);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSignup) {
			User user = new User();
			// set name
			try {
				user.setName(tfName.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			// set id
			try {
				user.setId(tfid.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			// set pass
			try {
				user.setPass(tfPass.getText());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			// set năm sinh
			try {
				user.setNs(Integer.parseInt(tfns.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Vui lòng nhập năm sinh là số");
				return;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				return;
			}
			// set giới tính
			if (r1.isSelected()) {
				user.setGioiTinh(GioiTinh.Female);
			} else {
				user.setGioiTinh(GioiTinh.Male);
			}
			// nếu nhập đúng
			if (tfPass.getText().equals(tfRePass.getText())) {
				Ctr.signup(user);
			} else {
				JOptionPane.showMessageDialog(null, "pass nhập lại không khớp nhau");
			}
		}
	}

}
