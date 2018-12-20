package model;

public class AccountEntry {
	String username;
	String password;
	UserEntry user;
	
	public AccountEntry(String username) {
		this.username = username;
	}
	
	public AccountEntry(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public AccountEntry(String userName, String pass, UserEntry user) {
		this.username = userName;
		this.password = pass;
		this.user = user;
	}

	
	public AccountEntry(String username, UserEntry user) {
		this.username = username;
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserEntry getUser() {
		return user;
	}

	public void setUser(UserEntry user) {
		this.user = user;
	}
}
