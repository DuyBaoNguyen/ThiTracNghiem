package model;

public class ResultDetailEntry {
	QuestionEntry question;
	AnswerEntry userAnswer;
	Boolean status;
	int order;

	public ResultDetailEntry(QuestionEntry question, AnswerEntry userAnswer, Boolean status) {
		super();
		this.question = question;
		this.userAnswer = userAnswer;
		this.status = status;
	}

	public ResultDetailEntry() {

	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public QuestionEntry getQuestion() {
		return question;
	}

	public void setQuestion(QuestionEntry question) {
		this.question = question;
	}

	public AnswerEntry getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(AnswerEntry userAnswer) {
		this.userAnswer = userAnswer;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
