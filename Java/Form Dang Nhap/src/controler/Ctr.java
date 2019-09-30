package controler;

import java.sql.*;
import javax.swing.*;
import com.mysql.cj.xdevapi.Statement;
import model.User;

public class Ctr {
	public static Connection conn = null;
	public static ResultSet rs;
	public static PreparedStatement pst;
	public static Statement st;

	public static void connectSql() {
		// TODO Auto-generated method stub
		// kết nối với csdl
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "wellcome");
			// xuất kết quả ra
		} catch (Exception ex) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "thất bại");
		}
	}

	public static void signin(JTextField tfUsername, JPasswordField jPassword) throws SQLException {
		String user = tfUsername.getText();
		String pass = jPassword.getText();

		String sql = "select * from signin where id='" + user + "'and pass=MD5('" + pass + "')";
		pst = conn.prepareStatement(sql);
		rs = pst.executeQuery();
		if (rs.next()) {
			JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
		} else {
			JOptionPane.showMessageDialog(null, "Đăng nhập thất bại");
		}
	}

	public static void signup(User user) {
		// TODO Auto-generated method stub
		try {
			String sql = "INSERT INTO `login`.`signin` (`name`, `id`, `pass`, `ns`, `GioiTinh`) VALUES (?,?,MD5(?),?,?)";
			pst = conn.prepareStatement(sql);

			pst.setString(1, user.getName());
			pst.setString(2, user.getId());
			pst.setString(3, user.getPass());
			pst.setInt(4, user.getNs());
			pst.setString(5, user.getGioiTinh() + "");

			pst.execute();
			JOptionPane.showMessageDialog(null, "Đăng ký thành công");
		} catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Đăng ký thất bại");
		}
	}
}
