package org.kp4tr.exams.actions;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.kp4tr.exams.delegate.startExamDelegate.StartExamDelegate;
import org.kp4tr.exams.delegate.startExamDelegate.StartExamRequest;
import org.kp4tr.exams.delegate.startExamDelegate.StartExamResponse;
import org.kp4tr.exams.formbeans.QuestionForm;

public class TakeExamAction extends Action {

	public TakeExamAction() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(1200); // 20 min.

		String returnMapping = "";
		String examType = "";

		QuestionForm questionForm = (QuestionForm) form;

		if (questionForm.getStartDateAndTime() == null) {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new java.text.SimpleDateFormat(
					"MMM dd, yyyy HH:mm:ss:aa");
			questionForm.setStartDateAndTime(sdf.format(cal.getTime()));
		}

		ActionMessages messages = new ActionMessages();

		/*
		 * messages.add("startOfSessionMessage", new ActionMessage(
		 * "sessionStarted")); saveMessages(request, messages);
		 */

		try {
			examType = questionForm.getStartExamName();

			if (examType == null || examType.equals("")) {
				messages.add("prarlMessage",
						new ActionMessage("noExamSelected"));
				saveMessages(request, messages);
				returnMapping = "initselectexam";
			} else {
				session.setAttribute("examType", examType);
			}

			StartExamDelegate delegate = new StartExamDelegate();
			StartExamRequest startExamRequest = new StartExamRequest();
			StartExamResponse startExamResponse = new StartExamResponse();

			startExamRequest.setExamType(examType);
			startExamResponse = (StartExamResponse) delegate
					.processRequest(startExamRequest);

			questionForm.setExamQuestions(startExamResponse.getQuestions());
			questionForm.setSubelementDesc(startExamResponse
					.getSubelementDesc());
			questionForm.setExamSize(startExamResponse.getExamSize());
			questionForm.initUserAnswer(Integer.parseInt(startExamResponse
					.getExamSize()));

			if (questionForm.getSingleQuestionFlag() != null) {
				if (questionForm.getSingleQuestionFlag().equals("Y")) {
					returnMapping = "takeexam_single";
					questionForm.setSingleQuestionNo("0"); // index of 1st
															// question
					// in Array
				}
			} else {
				returnMapping = "takeexam";
			}

		} catch (Exception e) {

		}
		return mapping.findForward(returnMapping);
	}
}
