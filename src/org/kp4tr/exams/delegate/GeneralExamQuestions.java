package org.kp4tr.exams.delegate;

import java.util.ArrayList;

/**
 * @author Ramon Gonzalez
 * 
 * July 2007 question pool
 * 
 */
public class GeneralExamQuestions extends AbstractExamQuestions {

	public GeneralExamQuestions() {
		// these are parameters speific to general questions
		numberOfQuestions = 35;

	}
// fccquestions_en or fccquestions_es
	public ArrayList buildQuestions(String fccQuestions) {
		ArrayList ql = new ArrayList();
		ql.add(this.getQuestion(fccQuestions, "G1", "A", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G1", "B", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G1", "C", 13, new String[] {"2"}));
		ql.add(this.getQuestion(fccQuestions, "G1", "D", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G1", "E", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G2", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G2", "B", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G2", "C", 12, new String[] {"6"}));
		ql.add(this.getQuestion(fccQuestions, "G2", "D", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G2", "E", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G2", "F", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G3", "A", 19, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G3", "B", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G3", "C", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G4", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G4", "B", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G4", "C", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G4", "D", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G4", "E", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G5", "A", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G5", "B", 15, new String[] {"10"}));
		ql.add(this.getQuestion(fccQuestions, "G5", "C", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G6", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G6", "B", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G6", "C", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G7", "A", 24, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G7", "B", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G8", "A", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G8", "B", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G9", "A", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G9", "B", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G9", "C", 21, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G9", "D", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G0", "A", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "G0", "B", 14, new String[] {}));

		return ql;
	}
}