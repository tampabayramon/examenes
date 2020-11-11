package org.kp4tr.exams.delegate;

import java.util.ArrayList;

import org.kp4tr.exams.util.RandomNumbers;

public abstract class AbstractExamQuestions implements IQuestions {

	public int numberOfQuestions;

	public AbstractExamQuestions() {
	}

	public ArrayList buildQuestions(String fccQuestions) {

		return new ArrayList();
	}

	public String getQuestion(String fccQuestionsTable, String subElement,
			String subElementGroup, int rangeTo, String skipQuestions[]) {

		String poolQuestionNumber = "";
		String examSkipNumbers = this.buildQuestionsToSkip(skipQuestions);
		RandomNumbers rn = new RandomNumbers();

		for (int i = 0; i < rangeTo; i++) {

			if (examSkipNumbers.equals("")) {
				poolQuestionNumber = Integer.toString(rn.randomNumber(rangeTo));
			} else {
				poolQuestionNumber = Integer.toString(rn.randomNumber(rangeTo,
						examSkipNumbers));
			}
			System.out.println("rn: "+poolQuestionNumber);
		}

		String rsquery = "SELECT * FROM " + fccQuestionsTable + "  WHERE "
				+ fccQuestionsTable + ".question_subelement = '" + subElement
				+ "' and " + fccQuestionsTable
				+ ".question_subelementgroup = '" + subElementGroup + "' and "
				+ fccQuestionsTable + ".question_number = '"
				+ poolQuestionNumber + "';";
		return rsquery;

	}

	private String buildQuestionsToSkip(String[] numberArray) {
		String numberList = "";
		if (numberArray.length != 0) {
			for (int i = 0; i < numberArray.length; i++) {
				if (i == 0) {
					numberList = numberArray[i];
				} else {
					numberList += "," + numberArray[i];
				}
			}
		}
		return numberList;
	}

}