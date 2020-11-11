package org.kp4tr.exams.beans;

public class ExamQuestionsSubelementDescBean {

	private String question_elementClass;

	private String question_elementDate;

	private int totalQuestions;

	private int minCorrectQuestions;

	private String fccSubelementDesc;

	public String getFccSubelementDesc() {
		return fccSubelementDesc;
	}

	public void setFccSubelementDesc(String fccSubelementDesc) {
		this.fccSubelementDesc = fccSubelementDesc;
	}

	public int getMinCorrectQuestions() {
		return minCorrectQuestions;
	}

	public void setMinCorrectQuestions(int minCorrectQuestions) {
		this.minCorrectQuestions = minCorrectQuestions;
	}

	public String getQuestion_elementClass() {
		return question_elementClass;
	}

	public void setQuestion_elementClass(String question_elementClass) {
		this.question_elementClass = question_elementClass;
	}

	public String getQuestion_elementDate() {
		return question_elementDate;
	}

	public void setQuestion_elementDate(String question_elementDate) {
		this.question_elementDate = question_elementDate;
	}

	public int getTotalQuestions() {
		return totalQuestions;
	}

	public void setTotalQuestions(int totalQuestions) {
		this.totalQuestions = totalQuestions;
	}

}
