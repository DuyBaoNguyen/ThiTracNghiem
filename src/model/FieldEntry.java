package model;

public class FieldEntry {
	int id;
	String name;
	
	public FieldEntry(int id) {
		this.id = id;
	}
	
	public FieldEntry(String name) {
		this.name = name;
	}
	
	public FieldEntry(int id, String name) {
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
