package org.kp4tr.exams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.QuestionBean;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;

/**
 * 
 * @author Ramon Gonzalez
 */
public class ExamQuestionsDAO {

	/** Creates a new instance of examStatistics */
	public ExamQuestionsDAO() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	private Connection connection = null;

	private Statement SQL_Statement = null;

	public ArrayList execute(ArrayList questionNumbers) {

		connection = DbConnectionFactory.getConnection();

		ArrayList questions = new ArrayList();
		String subElementNumber = "";

		try {
			SQL_Statement = connection.createStatement();
			ResultSet rsquery1 = null;
			for (int i = 0; i < questionNumbers.size(); i++) {
				rsquery1 = SQL_Statement.executeQuery((String) questionNumbers
						.get(i));

				while (rsquery1.next()) {
					QuestionBean qb = new QuestionBean();
					qb.setQuestion_subelement(rsquery1
							.getString("question_subelement"));
					qb.setQuestion_subelementgroup(rsquery1
							.getString("question_subelementgroup"));
					qb.setQuestion_number(Integer.toString(rsquery1
							.getInt("question_number")));
					qb
							.setQuestion_answer(rsquery1
									.getString("question_answer"));
					qb.setQuestion_reference(rsquery1
							.getString("question_reference"));
					qb.setQuestion(rsquery1.getString("question"));
					qb.setMultiplechoice_A(rsquery1
							.getString("multiplechoice_A"));
					qb.setMultiplechoice_B(rsquery1
							.getString("multiplechoice_B"));
					qb.setMultiplechoice_C(rsquery1
							.getString("multiplechoice_C"));
					qb.setMultiplechoice_D(rsquery1
							.getString("multiplechoice_D"));
					qb.setFigureImage(rsquery1.getString("figureImage"));
					qb.setGeneratedQuestionNumber(Integer.toString(i + 1));
					subElementNumber = qb.getQuestion_subelement()
							+ qb.getQuestion_subelementgroup()
							+ qb.getQuestion_number();

					qb.setQuestion_subelementNumber(subElementNumber.trim());
					//System.out.println(subElementNumber + "-------------image=" + qb.getFigureImage());
					qb.setUserAnswer("");
					questions.add(qb);
				}
			}

			SQL_Statement.close();
		} catch (Exception e) {
			log.error("Exception caught in :" + this.getClass().getName() + e);
		} finally {
			DbConnectionFactory.closeDBConnection(connection);
		}

		return questions;
	}

}
