package org.kp4tr.exams.delegate.endExamDelegate;

import java.util.ArrayList;

import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.layers.AbstractServiceRequest;

public class EndExamRequest extends AbstractServiceRequest {
	
	
	private ArrayList examQuestions;
	private String examSize;
	private String examType;
	private ExamQuestionsSubelementDescBean subElementDesc;

	
	/**
	 * @return Returns the examQuestions.
	 */
	public ArrayList getExamQuestions() {
		return examQuestions;
	}
	/**
	 * @param examQuestions The examQuestions to set.
	 */
	public void setExamQuestions(ArrayList examQuestions) {
		this.examQuestions = examQuestions;
	}
	public String getExamSize() {
		return examSize;
	}
	public void setExamSize(String examSize) {
		this.examSize = examSize;
	}
	public String getExamType() {
		return examType;
	}
	public void setExamType(String examType) {
		this.examType = examType;
	}
	public ExamQuestionsSubelementDescBean getSubElementDesc() {
		return subElementDesc;
	}
	public void setSubElementDesc(ExamQuestionsSubelementDescBean subElementDesc) {
		this.subElementDesc = subElementDesc;
	}
}
