package org.kp4tr.exams.delegate.startExamDelegate;

import java.util.ArrayList;

import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.layers.AbstractServiceResponse;

public class StartExamResponse extends AbstractServiceResponse {

	private ArrayList questions;

	private String examType;

	private String examSize;
	
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
	public String getExamSize() {
		return examSize;
	}

	/**
	 * @param examSize
	 *            The examSize to set.
	 */
	public void setExamSize(String examSize) {
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
}
