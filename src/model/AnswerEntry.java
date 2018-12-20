package model;

public class AnswerEntry {
	int id;
	String content;
	
	public AnswerEntry() {
		
	}
	
	public AnswerEntry(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}
	
	public AnswerEntry(int id) {
		this.id = id;
	}

	public AnswerEntry(String content) {
		this.content = content;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
