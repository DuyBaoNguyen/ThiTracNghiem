package model;

public class ExamStructureEntry {
	TypeQuestionEntry typeQuestion;
	int numberQuestions;
	
	public ExamStructureEntry(TypeQuestionEntry typeQuestion) {
		this.typeQuestion = typeQuestion;
	}
	
	public ExamStructureEntry(TypeQuestionEntry typeQuestion, int numberQuestions) {
		this.typeQuestion = typeQuestion;
		this.numberQuestions = numberQuestions;
	}
	
	public TypeQuestionEntry getTypeQuestion() {
		return typeQuestion;
	}

	public void setTypeQuestion(TypeQuestionEntry typeQuestion) {
		this.typeQuestion = typeQuestion;
	}

	public int getNumberQuestions() {
		return numberQuestions;
	}

	public void setNumberQuestions(int numberQuestions) {
		this.numberQuestions = numberQuestions;
	}	
}
