package model;

import java.util.List;

public class QuestionEntry {
	int id;
	String content;
	FieldEntry field;
	TypeQuestionEntry type;
	AnswerEntry rightAnswer;
	List<AnswerEntry> answers;
	
	public QuestionEntry() {
		
	}

	public QuestionEntry(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public QuestionEntry(int id) {
		super();
		this.id = id;
	}
	
	public QuestionEntry(Integer macauhoi) {
		this.id = macauhoi;
	}

	public QuestionEntry(String content) {
		super();
		this.content = content;
	}

	public QuestionEntry(int id, String content, AnswerEntry rightAnswer) {
		super();
		this.id = id;
		this.content = content;
		this.rightAnswer = rightAnswer;
	}
	
	public QuestionEntry(int id, String content, FieldEntry field, TypeQuestionEntry type, AnswerEntry rightAnswer,
			List<AnswerEntry> answers) {
		super();
		this.id = id;
		this.content = content;
		this.field = field;
		this.type = type;
		this.rightAnswer = rightAnswer;
		this.answers = answers;
	}

	public TypeQuestionEntry getType() {
		return type;
	}

	public void setType(TypeQuestionEntry type) {
		this.type = type;
	}

	public QuestionEntry(int id, String content, FieldEntry field, TypeQuestionEntry type) {
		super();
		this.id = id;
		this.content = content;
		this.field = field;
		this.type = type;
	}
	
	public QuestionEntry(String content, AnswerEntry rightAnswer, List<AnswerEntry> answers, FieldEntry field, TypeQuestionEntry type) {
		this.content = content;
		this.rightAnswer = rightAnswer;
		this.answers = answers;
		this.field = field;
		this.type = type;
	}

	public int getId() {
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

	public FieldEntry getField() {
		return field;
	}

	public void setField(FieldEntry field) {
		this.field = field;
	}

	public AnswerEntry getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(AnswerEntry rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public List<AnswerEntry> getAnswers() {
		return answers;
	}

	public void setAnswers(List<AnswerEntry> answers) {
		this.answers = answers;
	}

}
