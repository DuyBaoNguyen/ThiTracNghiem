package model;

public class ClassEntry {
	String id;
	String name;
	int amountCandidate;
	FieldEntry field;
	
	public ClassEntry(String id) {
		this.id = id;
	}
	
	public ClassEntry(String name, FieldEntry field) {
		this.name = name;
		this.field = field;
	}
	
	public ClassEntry(String id, String name, FieldEntry field) {
		this.id = id;
		this.name = name;
		this.field = field;
	}
	
	public ClassEntry(String id, String name, int amountCandidate) {
		this.id = id;
		this.name = name;
		this.amountCandidate = amountCandidate;
	}
	
	public ClassEntry(String id, String name) {
		this.id = id;
		this.name = name;
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

	public int getAmountCandidate() {
		return amountCandidate;
	}

	public void setAmountCandidate(int amountCandidate) {
		this.amountCandidate = amountCandidate;
	}
	
	public FieldEntry getField() {
		return field;
	}

	public void setField(FieldEntry field) {
		this.field = field;
	}
}
