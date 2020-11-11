package org.kp4tr.exams.delegate.examStatistics;

import org.kp4tr.exams.beans.ExamStatisticsBean;
import org.kp4tr.exams.layers.AbstractServiceResponse;

public class ExamStatisticsResponse extends AbstractServiceResponse {

	private ExamStatisticsBean examStatisticsBean;

	public ExamStatisticsBean getExamStatisticsBean() {
		return examStatisticsBean;
	}

	public void setExamStatisticsBean(ExamStatisticsBean examStatisticsBean) {
		this.examStatisticsBean = examStatisticsBean;
	}

}
