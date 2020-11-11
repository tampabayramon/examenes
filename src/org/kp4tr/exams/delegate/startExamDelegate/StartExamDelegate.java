package org.kp4tr.exams.delegate.startExamDelegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.dao.ExamQuestionsDAO;
import org.kp4tr.exams.dao.ExamQuestionsSubelementDescDAO;
import org.kp4tr.exams.delegate.ExtraExamQuestions;
import org.kp4tr.exams.delegate.GeneralExamQuestions;
import org.kp4tr.exams.delegate.IQuestions;
import org.kp4tr.exams.delegate.TechnicianExamQuestions;
import org.kp4tr.exams.layers.AbstractDelegate;
import org.kp4tr.exams.layers.IServiceRequest;
import org.kp4tr.exams.layers.IServiceResponse;

/**
 * 
 * @author Ramon Gonzalez
 */
public class StartExamDelegate extends AbstractDelegate {

	public StartExamDelegate() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public IServiceResponse processRequest(IServiceRequest serviceRequest)
			throws Exception {

		//QuestionBean questionBean = new QuestionBean();
		StartExamResponse startExamResponse = new StartExamResponse();

		ArrayList questionNumbers = null;
		IQuestions examQuestions = null;
		ArrayList questions = new ArrayList();

		try {

			StartExamRequest request = (StartExamRequest) serviceRequest;

			String examType = request.getExamType();
			startExamResponse.setExamType(examType);
			String question_element = "";
			String fccSubelementDesc = "";

			// technician, general, or extra?
			if (examType.startsWith("T")) {
				examQuestions = new TechnicianExamQuestions();
				question_element = "T";
			} else if (examType.startsWith("G")) {
				examQuestions = new GeneralExamQuestions();
				question_element = "G";

			} else if (examType.startsWith("E")) {
				examQuestions = new ExtraExamQuestions();
				question_element = "E";
			}

			// english or spanish?

			if (examType.endsWith("_EN")) {
				questionNumbers = examQuestions
						.buildQuestions("fccquestions_en");
				fccSubelementDesc = "fccSubelementDesc_en";
			} else if (examType.endsWith("_ES")) {
				questionNumbers = examQuestions
						.buildQuestions("fccquestions_es");
				fccSubelementDesc = "fccSubelementDesc_es";
			}

			startExamResponse.setExamSize(Integer.toString(questionNumbers
					.size()));
			// now query in DAO the select statements in ArrayList

			questions = new ExamQuestionsDAO().execute(questionNumbers);
			ExamQuestionsSubelementDescBean subelementDesc = new ExamQuestionsSubelementDescBean();
			subelementDesc = new ExamQuestionsSubelementDescDAO().execute(
					fccSubelementDesc, question_element);

			startExamResponse.setQuestions(questions);
			startExamResponse.setExamSize(Integer
					.toString(questions.size() - 1)); //so it matches arraylist
													  // offset
			startExamResponse.setSubelementDesc(subelementDesc);

		} catch (Exception e) {
			log.error("Exception caught in StartExamDelegate():" + e);
		}

		return startExamResponse;
	}
}
