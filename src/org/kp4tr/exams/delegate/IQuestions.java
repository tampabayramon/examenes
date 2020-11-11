package org.kp4tr.exams.delegate;

import java.util.ArrayList;

public interface IQuestions {

	public String getQuestion(String fccQuestionsTable, String subElement,
			String subElementGroup, int rangeTo, String skipQuestions[]);

	public ArrayList buildQuestions(String fccQuestions);

}