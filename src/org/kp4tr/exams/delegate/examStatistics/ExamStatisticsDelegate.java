package org.kp4tr.exams.delegate.examStatistics;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamStatisticsBean;
import org.kp4tr.exams.dao.ExamStatisticsDAO;
import org.kp4tr.exams.layers.AbstractDelegate;
import org.kp4tr.exams.layers.IServiceRequest;
import org.kp4tr.exams.layers.IServiceResponse;

/**
 * 
 * @author Ramon Gonzalez
 */
public class ExamStatisticsDelegate extends AbstractDelegate {

	/** Creates a new instance of examStatistics */
	public ExamStatisticsDelegate() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public IServiceResponse processRequest(IServiceRequest serviceRequest)
			throws Exception {

		int totalPassed, totalFailed;

		ExamStatisticsBean examStatisticsBean = new ExamStatisticsBean();

		try {

			ExamStatisticsDAO dao = new ExamStatisticsDAO();
			examStatisticsBean = dao.execute();

			totalPassed = examStatisticsBean.getTechnicianPassed()
					+ examStatisticsBean.getGeneralPassed()
					+ examStatisticsBean.getExtraPassed();
			examStatisticsBean.setTotalPassed(totalPassed);

			totalFailed = examStatisticsBean.getTechnicianFailed()
					+ examStatisticsBean.getGeneralFailed()
					+ examStatisticsBean.getExtraFailed();
			examStatisticsBean.setTotalFailed(totalFailed);

			examStatisticsBean.setTotals(totalPassed + totalFailed);

		} catch (Exception e) {
			log.error("Exception caught in ExamStatisticsDelegate():" + e);
		}

		ExamStatisticsResponse examStatisticsResponse = new ExamStatisticsResponse();
		examStatisticsResponse.setExamStatisticsBean(examStatisticsBean);

		return examStatisticsResponse;
	}

}
