package model;

import java.sql.Date;
import java.util.List;

public class ExamEntry {
	int id;
	String name;
	Date startTime;
	Date endTime;
	int totalTime;
	int totalQuestion;
	FieldEntry field;
	List<ExamStructureEntry> examStructures;
	List<ClassEntry> classes;
	
	public ExamEntry(int id) {
		this.id = id;
	}
	
	public ExamEntry(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public ExamEntry(int id, String name, int totalTime, int totalQuestion, FieldEntry field) {
		this.id = id;
		this.name = name;
		this.totalTime = totalTime;
		this.totalQuestion = totalQuestion;
		this.field = field;
	}
	
	public ExamEntry(int id, String name, Date startTime, Date endTime, int totalTime, int totalQuestion,
			FieldEntry field, List<ExamStructureEntry> examStructures, List<ClassEntry> classes) {
		super();
		this.id = id;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalTime = totalTime;
		this.totalQuestion = totalQuestion;
		this.field = field;
		this.examStructures = examStructures;
		this.classes = classes;
	}
	
	public ExamEntry(String name, int totalTime, int totalQuestion, FieldEntry field,
			List<ExamStructureEntry> examStructures) {
		super();
		this.name = name;
		this.totalTime = totalTime;
		this.totalQuestion = totalQuestion;
		this.field = field;
		this.examStructures = examStructures;
	}
	
	public ExamEntry(int id, String name, int totalTime, int totalQuestion, FieldEntry field,
			List<ExamStructureEntry> examStructures) {
		super();
		this.id = id;
		this.name = name;
		this.totalTime = totalTime;
		this.totalQuestion = totalQuestion;
		this.field = field;
		this.examStructures = examStructures;
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getTotalQuestion() {
		return totalQuestion;
	}

	public void setTotalQuestion(int totalQuestion) {
		this.totalQuestion = totalQuestion;
	}

	public FieldEntry getField() {
		return field;
	}

	public void setField(FieldEntry field) {
		this.field = field;
	}

	public List<ExamStructureEntry> getExamStructures() {
		return examStructures;
	}

	public void setExamStructures(List<ExamStructureEntry> examStructures) {
		this.examStructures = examStructures;
	}

	public List<ClassEntry> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntry> classes) {
		this.classes = classes;
	}
}
