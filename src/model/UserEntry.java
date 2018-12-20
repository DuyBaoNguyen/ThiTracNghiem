package model;

import java.sql.Date;

public class UserEntry {
	String id;
	String name;
	boolean sex;
	String address;
	Date birthday;
	String phone;
	RoleEntry role;
	
	public UserEntry() {
		
	}

	public UserEntry(String id) {
		this.id = id;
	}

	public UserEntry(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public UserEntry(String id, String name, boolean sex, String address, Date birthday, String phone, RoleEntry role) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.phone = phone;
		this.role = role;
		
		if (this.address != null && this.address.length() == 0) {
			this.address = null;
		}
		if (this.phone != null && this.phone.length() == 0) {
			this.phone = null;
		}
	}

	public UserEntry(String name, boolean sex, String address, Date birthday, String phone, RoleEntry role) {
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.birthday = birthday;
		this.phone = phone;
		this.role = role;
		
		if (this.address != null && this.address.length() == 0) {
			this.address = null;
		}
		if (this.phone != null && this.phone.length() == 0) {
			this.phone = null;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		if (this.address != null && this.address.length() == 0) {
			this.address = null;
		}
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
		if (this.phone != null && this.phone.length() == 0) {
			this.phone = null;
		}
	}

	public RoleEntry getRole() {
		return role;
	}

	public void setRole(RoleEntry role) {
		this.role = role;
	}
}
