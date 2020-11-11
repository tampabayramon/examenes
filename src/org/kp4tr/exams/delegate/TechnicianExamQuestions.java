package org.kp4tr.exams.delegate;

import java.util.ArrayList;

/**
 * @author Ramon Gonzalez
 * 
 */
public class TechnicianExamQuestions extends AbstractExamQuestions {

	public TechnicianExamQuestions() {
		// these are parameters speific to technicial questions
		numberOfQuestions = 35;

	}
// fccquestions_en or fccquestions_es
	public ArrayList buildQuestions(String fccQuestions) {
		ArrayList ql = new ArrayList();
		ql.add(this.getQuestion(fccQuestions, "T1", "A", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T1", "B", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T1", "C", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T1", "D", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T2", "A", 11, new String[] {"2"}));
		ql.add(this.getQuestion(fccQuestions, "T2", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T2", "C", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T2", "D", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T3", "A", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T3", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T3", "C", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T3", "D", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T4", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T4", "B", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T4", "C", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T4", "D", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T4", "E", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T5", "A", 10, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T5", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T5", "C", 14, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T5", "D", 13, new String[] { "6" }));
		ql.add(this.getQuestion(fccQuestions, "T6", "A", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T6", "B", 11, new String[] { "9" }));
		ql.add(this.getQuestion(fccQuestions, "T6", "C", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T7", "A", 12, new String[] { "8" }));
		ql.add(this.getQuestion(fccQuestions, "T7", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T8", "A", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T8", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T8", "C", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T9", "A", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T9", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T9", "C", 12, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T0", "A", 13, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T0", "B", 11, new String[] {}));
		ql.add(this.getQuestion(fccQuestions, "T0", "C", 11, new String[] {}));

		return ql;
	}
}