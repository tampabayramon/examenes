package org.kp4tr.exams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;

/**
 * 
 * @author Ramon Gonzalez
 */
public class ExamQuestionsSubelementDescDAO {

	/** Creates a new instance of examStatistics */
	public ExamQuestionsSubelementDescDAO() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	private Connection connection = null;

	private Statement SQL_Statement = null;

	public ExamQuestionsSubelementDescBean execute(String fccSubelementDesc,
			String question_element) {

		ExamQuestionsSubelementDescBean subelementDesc = new ExamQuestionsSubelementDescBean();

		try {
			connection = DbConnectionFactory.getConnection();
			ResultSet rsquery = null;
			SQL_Statement = connection.createStatement();
			rsquery = SQL_Statement.executeQuery("SELECT * FROM "
					+ fccSubelementDesc + " WHERE " + fccSubelementDesc.trim()
					+ ".question_element = '" + question_element + "'");
			while (rsquery.next()) {
				subelementDesc.setQuestion_elementClass(rsquery
						.getString("question_elementClass"));
				subelementDesc.setQuestion_elementDate(rsquery
						.getString("question_elementDate"));
				subelementDesc
						.setTotalQuestions(rsquery.getInt("numQuestions"));
				subelementDesc.setMinCorrectQuestions(rsquery
						.getInt("minCorrectQuestions"));
				subelementDesc.setFccSubelementDesc(rsquery
						.getString("questionPoolDesc"));
			}

		} catch (Exception e) {
			log.error("Exception caught in examQuery():" + e);
			e.printStackTrace();
		} finally {
			DbConnectionFactory.closeDBConnection(connection);
		}

		return subelementDesc;
	}

}
