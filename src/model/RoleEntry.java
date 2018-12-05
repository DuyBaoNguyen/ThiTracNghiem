package model;

public class RoleEntry {
	int id;
	String name;
	
	public RoleEntry(int id) {
		this.id = id;
	}
	
	public RoleEntry(String name) {
		this.name = name;
	}
	
	public RoleEntry(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
