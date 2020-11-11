package org.kp4tr.exams.delegate.startExamDelegate;

import org.kp4tr.exams.layers.AbstractServiceRequest;

public class StartExamRequest extends AbstractServiceRequest {
	
	private String examType;

	public String getExamType() {
		return examType;
	}

	public void setExamType(String examType) {
		this.examType = examType;
	}

}
