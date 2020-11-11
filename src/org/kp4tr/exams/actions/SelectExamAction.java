package org.kp4tr.exams.actions;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsDelegate;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsRequest;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsResponse;
import org.kp4tr.exams.formbeans.SelectExamForm;
import org.kp4tr.exams.util.SessionKeys;

public class SelectExamAction extends Action {

	public SelectExamAction() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String returnMapping = "";

		SelectExamForm selectExam = (SelectExamForm) form;

		try {

			ExamStatisticsDelegate delegate = new ExamStatisticsDelegate();
			ExamStatisticsRequest examStatisticsRequest = new ExamStatisticsRequest();
			ExamStatisticsResponse examStatisticsResponse = new ExamStatisticsResponse();

			examStatisticsResponse = (ExamStatisticsResponse) delegate
					.processRequest(examStatisticsRequest);

			selectExam.setExamStatistics(examStatisticsResponse
					.getExamStatisticsBean());

			ArrayList sessionTotals = (ArrayList) session
					.getAttribute(SessionKeys.SESSIONLISTENER);

			selectExam.setActiveSessions((String) sessionTotals.get(0));
			selectExam.setMaxActiveSessions((String) sessionTotals.get(1));
			selectExam.setTotalSessions((String) sessionTotals.get(2));
			selectExam.setLastSessionDateTime((String) sessionTotals.get(3));

			/*
			 * ActionMessages messages = new ActionMessages();
			 * messages.add("prarlMessage", new ActionMessage(
			 * "selectexam.title")); saveMessages(request, messages);
			 */

			returnMapping = "selectexam";

		} catch (Exception e) {

		}
		return mapping.findForward(returnMapping);
	}

}
