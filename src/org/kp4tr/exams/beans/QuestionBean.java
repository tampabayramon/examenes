package org.kp4tr.exams.beans;

public class QuestionBean {

	private String question_subelement;

	private String question_subelementgroup;

	private String question_number;

	private String question_subelementNumber;

	private String question_answer;

	private String question_reference;

	private String question;

	private String generatedQuestionNumber;

	private String multiplechoice_A;

	private String multiplechoice_B;

	private String multiplechoice_C;

	private String multiplechoice_D;

	private String figureImage;

	private String userAnswer;

	public String getFigureImage() {
		return figureImage;
	}

	public void setFigureImage(String figureImage) {
		this.figureImage = figureImage;
	}

	public String getMultiplechoice_A() {
		return multiplechoice_A;
	}

	public void setMultiplechoice_A(String multiplechoice_A) {
		this.multiplechoice_A = multiplechoice_A;
	}

	public String getMultiplechoice_B() {
		return multiplechoice_B;
	}

	public void setMultiplechoice_B(String multiplechoice_B) {
		this.multiplechoice_B = multiplechoice_B;
	}

	public String getMultiplechoice_C() {
		return multiplechoice_C;
	}

	public void setMultiplechoice_C(String multiplechoice_C) {
		this.multiplechoice_C = multiplechoice_C;
	}

	public String getMultiplechoice_D() {
		return multiplechoice_D;
	}

	public void setMultiplechoice_D(String multiplechoice_D) {
		this.multiplechoice_D = multiplechoice_D;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestion_answer() {
		return question_answer;
	}

	public void setQuestion_answer(String question_answer) {
		this.question_answer = question_answer;
	}

	public String getQuestion_number() {
		if (Integer.parseInt(this.question_number) < 10) {
			return "0" + question_number;
		} else {
			return question_number;
		}

	}

	public void setQuestion_number(String question_number) {
		this.question_number = question_number;
	}

	public String getQuestion_reference() {
		return question_reference;
	}

	public void setQuestion_reference(String question_reference) {
		this.question_reference = question_reference;
	}

	public String getQuestion_subelement() {
		return question_subelement;
	}

	public void setQuestion_subelement(String question_subelement) {
		this.question_subelement = question_subelement;
	}

	public String getQuestion_subelementgroup() {
		return question_subelementgroup;
	}

	public void setQuestion_subelementgroup(String question_subelementgroup) {
		this.question_subelementgroup = question_subelementgroup;
	}

	public String getUserAnswer() {
		return userAnswer;
	}

	public void setUserAnswer(String userAnswer) {
		this.userAnswer = userAnswer;
	}

	public String getGeneratedQuestionNumber() {
		return generatedQuestionNumber;
	}

	public void setGeneratedQuestionNumber(String generatedQuestionNumber) {
		this.generatedQuestionNumber = generatedQuestionNumber;
	}

	public String getQuestion_subelementNumber() {
		return question_subelementNumber;
	}

	public void setQuestion_subelementNumber(String question_subelementNumber) {
		this.question_subelementNumber = question_subelementNumber;
	}

}
