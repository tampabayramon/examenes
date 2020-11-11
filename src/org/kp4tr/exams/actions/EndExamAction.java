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
import org.kp4tr.exams.delegate.endExamDelegate.EndExamDelegate;
import org.kp4tr.exams.delegate.endExamDelegate.EndExamRequest;
import org.kp4tr.exams.delegate.endExamDelegate.EndExamResponse;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsDelegate;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsRequest;
import org.kp4tr.exams.delegate.examStatistics.ExamStatisticsResponse;
import org.kp4tr.exams.formbeans.QuestionForm;
import org.kp4tr.exams.formbeans.SelectExamForm;

public class EndExamAction extends Action {

	public EndExamAction() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		//check for expired session
		//passing false means the method returns an existing session, or null
		// if there was no session associated with this client
		HttpSession session = request.getSession(false);
		if (session == null) {
			ActionMessages messages = new ActionMessages();
			messages.add("prarlMessage", new ActionMessage("sessionExpired"));
			saveMessages(request, messages);
			return mapping.findForward("selectexam");
		}

		String returnMapping = "";
		String examType = "";

		QuestionForm questionForm = (QuestionForm) form;

		if (questionForm.getStartDateAndTime() != null) {
			ActionMessages messages = new ActionMessages();
			messages.add("startOfSessionMessage", new ActionMessage(
					"sessionStarted"));
			saveMessages(request, messages);
		} else {
			ActionMessages messages = new ActionMessages();
			messages.add("prarlMessage", new ActionMessage("sessionExpired"));
			saveMessages(request, messages);
			return mapping.findForward("initselectexam");
		}

		try {

			if (questionForm.getFunc().equals("End")) {

				examType = questionForm.getStartExamName();
				returnMapping = "endexam";

				EndExamDelegate delegate = new EndExamDelegate();
				EndExamRequest endExamRequest = new EndExamRequest();
				EndExamResponse endExamResponse = new EndExamResponse();
				endExamRequest
						.setExamQuestions(questionForm.getExamQuestions());
				endExamRequest.setExamSize(questionForm.getExamSize());
				endExamRequest.setExamType(questionForm.getStartExamName());
				endExamRequest.setSubElementDesc(questionForm
						.getSubelementDesc());
				endExamResponse = (EndExamResponse) delegate
						.processRequest(endExamRequest);
				questionForm.setCorrectAnswers(endExamResponse
						.getCorrectAnswers());
				questionForm.setWrongAnswers(endExamResponse.getWrongAnswers());
				questionForm.setPassOrFail(endExamResponse.getPassOrFail());

				ExamStatisticsDelegate delegate2 = new ExamStatisticsDelegate();
				ExamStatisticsRequest examStatisticsRequest = new ExamStatisticsRequest();
				ExamStatisticsResponse examStatisticsResponse = new ExamStatisticsResponse();

				examStatisticsResponse = (ExamStatisticsResponse) delegate2
						.processRequest(examStatisticsRequest);

				SelectExamForm selectExam = new SelectExamForm();

				selectExam.setExamStatistics(examStatisticsResponse
						.getExamStatisticsBean());
			} else if (questionForm.getFunc().equals("Next")) {
				// increment to select next question
				int x = Integer.parseInt(questionForm.getSingleQuestionNo());
				x++;
				questionForm.setSingleQuestionNo(Integer.toString(x).trim());
				returnMapping = "takeexam_single";
			} else if (questionForm.getFunc().equals("Prev")) {
				int x = Integer.parseInt(questionForm.getSingleQuestionNo());
				x--;
				questionForm.setSingleQuestionNo(Integer.toString(x).trim());
				returnMapping = "takeexam_single";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(returnMapping);
	}

}
