package model;

import java.sql.Timestamp;
import java.util.List;

public class ResultEntry {
	int id;
	CandidateEntry candidate;
	float score;
	int totalRightAnswers;
	int totalQuestion;
	ExamEntry exam;
	Timestamp time;
	List<ResultDetailEntry> resultDetails;
	
	public ResultEntry(int id, ClassEntry classEntry, CandidateEntry candidate, float score, int totalRightAnswers,
			int totalQuestion, ExamEntry exam, List<ResultDetailEntry> resultDetails) {
		super();
		this.id = id;
		this.candidate = candidate;
		this.score = score;
		this.totalRightAnswers = totalRightAnswers;
		this.totalQuestion = totalQuestion;
		this.exam = exam;
		this.resultDetails = resultDetails;
	}

	public ResultEntry() {

	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CandidateEntry getCandidate() {
		return candidate;
	}

	public void setCandidate(CandidateEntry candidate) {
		this.candidate = candidate;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getTotalRightAnswers() {
		return totalRightAnswers;
	}

	public void setTotalRightAnswers(int totalRightAnswers) {
		this.totalRightAnswers = totalRightAnswers;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public ExamEntry getExam() {
		return exam;
	}

	public void setExam(ExamEntry exam) {
		this.exam = exam;
	}

	public List<ResultDetailEntry> getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(List<ResultDetailEntry> resultDetails) {
		this.resultDetails = resultDetails;
	}

}
