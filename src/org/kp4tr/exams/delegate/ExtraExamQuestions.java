package org.kp4tr.exams.delegate;

import java.util.ArrayList;

/**
 * @author Ramon Gonzalez
 * 
 */
public class ExtraExamQuestions extends AbstractExamQuestions {

	public ExtraExamQuestions() {
		// these are parameters speific to general questions
		numberOfQuestions = 50;

	}

	// fccquestions_en or fccquestions_es
	public ArrayList buildQuestions(String fccQuestions) {
		ArrayList ql = new ArrayList();
		ql.add(this.getQuestion(fccQuestions, "E1", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E1", "B", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E1", "C", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E1", "D", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E1", "E", 20, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E1", "F", 17, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E2", "A", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E2", "B", 21, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E2", "C", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E2", "D", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E2", "E", 12, new String[] {"10"}));
		ql.add(this.getQuestion(fccQuestions, "E3", "A", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E3", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E3", "C", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E4", "A", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E4", "B", 17, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E4", "C", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E4", "D", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E4", "E", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E5", "A", 17, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E5", "B", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E5", "C", 23, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E5", "D", 19, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "A", 18, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "B", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "C", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "D", 18, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "E", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E6", "F", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "B", 20, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "C", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "D", 17, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "E", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "F", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "G", 17, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E7", "H", 20, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E8", "A", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E8", "B", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E8", "C", 15, new String[] {"4"}));
		ql.add(this.getQuestion(fccQuestions, "E8", "D", 19, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "A", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "B", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "C", 17, new String[] {"16"}));
		ql.add(this.getQuestion(fccQuestions, "E9", "D", 15, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "E", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "F", 16, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "G", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E9", "H", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "E0", "A", 11, new String[] {}));

		return ql;
	}
}