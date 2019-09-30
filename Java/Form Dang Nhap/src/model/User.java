package model;

public class User {
	private String name;
	private String id;
	private String pass;
	private int ns;
	private GioiTinh gioiTinh;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) throws Exception {
		if (name.equals("")) {
			throw new Exception("Tên không được trống");
		} else
			this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) throws Exception {
		if (id.equals("")) {
			throw new Exception("Id không được trống");
		} else
			this.id = id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) throws Exception {
		if (pass.equals("")) {
			throw new Exception("Password không được trống");
		} else
			this.pass = pass;
	}

	public int getNs() {
		return ns;
	}

	public void setNs(int ns) throws Exception {
		if (ns > 1900) {
			this.ns = ns;
		} else
			throw new Exception("Nhập năm sinh lớn hơn 1900");
	}

	public GioiTinh getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(GioiTinh gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

}
