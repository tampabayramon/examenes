package org.kp4tr.exams.delegate.endExamDelegate;

import java.util.ArrayList;

import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.layers.AbstractServiceResponse;

public class EndExamResponse extends AbstractServiceResponse {

	private ArrayList questions;

	private String examType;

	private int examSize;
	
	private int correctAnswers;

	private int wrongAnswers;
	
	private String passOrFail;
	
	private ExamQuestionsSubelementDescBean subelementDesc;

	/**
	 * @return Returns the examType.
	 */
	public String getExamType() {
		return examType;
	}

	/**
	 * @param examType
	 *            The examType to set.
	 */
	public void setExamType(String examType) {
		this.examType = examType;
	}

	/**
	 * @return Returns the examSize.
	 */
	public int getExamSize() {
		return examSize;
	}

	/**
	 * @param examSize
	 *            The examSize to set.
	 */
	public void setExamSize(int examSize) {
		this.examSize = examSize;
	}
	/**
	 * @return Returns the questions.
	 */
	public ArrayList getQuestions() {
		return questions;
	}
	/**
	 * @param questions The questions to set.
	 */
	public void setQuestions(ArrayList questions) {
		this.questions = questions;
	}

	public ExamQuestionsSubelementDescBean getSubelementDesc() {
		return subelementDesc;
	}

	public void setSubelementDesc(ExamQuestionsSubelementDescBean subelementDesc) {
		this.subelementDesc = subelementDesc;
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(int wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public String getPassOrFail() {
		return passOrFail;
	}

	public void setPassOrFail(String passOrFail) {
		this.passOrFail = passOrFail;
	}
}
