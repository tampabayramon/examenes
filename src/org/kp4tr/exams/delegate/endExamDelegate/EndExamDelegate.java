package org.kp4tr.exams.delegate.endExamDelegate;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.beans.QuestionBean;
import org.kp4tr.exams.dao.UpdateStatisticsDAO;
import org.kp4tr.exams.layers.AbstractDelegate;
import org.kp4tr.exams.layers.IServiceRequest;
import org.kp4tr.exams.layers.IServiceResponse;

/**
 * 
 * @author Ramon Gonzalez
 */
public class EndExamDelegate extends AbstractDelegate {

	public EndExamDelegate() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	public IServiceResponse processRequest(IServiceRequest serviceRequest)
			throws Exception {

		EndExamResponse endExamResponse = new EndExamResponse();

		// IQuestions examQuestions = null;
		ArrayList examQuestions;

		try {

			EndExamRequest request = (EndExamRequest) serviceRequest;

			examQuestions = (ArrayList) request.getExamQuestions();
			int examSize = examQuestions.size();
			String examType = request.getExamType().substring(0, 1);
			int correct = 0;
			int wrong = 0;

			// find out how many correct and wrong
			for (int i = 0; i < examSize; i++) {
				QuestionBean qb = (QuestionBean) examQuestions.get(i);
				if (qb.getUserAnswer().equals(qb.getQuestion_answer())) {
					correct++;
				} else {
					wrong++;
				}
			}

			int minCorrectQuestions = request.getSubElementDesc()
					.getMinCorrectQuestions();
			if (correct >= minCorrectQuestions) {
				endExamResponse.setPassOrFail("P");
			} else {
				endExamResponse.setPassOrFail("F");
			}

			endExamResponse.setWrongAnswers(wrong);
			endExamResponse.setCorrectAnswers(correct);

			// execute DAO to update exam statistics
			new UpdateStatisticsDAO().execute(examType, correct, wrong);

		} catch (Exception e) {
			log.error("Exception caught in EndExamDelegate():" + e);
		}

		return endExamResponse;
	}
}
