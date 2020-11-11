package org.kp4tr.exams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamStatisticsBean;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;

/**
 * 
 * @author Ramon Gonzalez
 */
public class UpdateStatisticsDAO {

	public UpdateStatisticsDAO() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	private Connection connection = null;

	private Statement SQL_Statement = null;

	public void execute(String examType, int numCorrect, int wrong) {

		int exams_taken = 0;
		int exams_passed = 0;
		int exams_failed = 0;
		int minCorrectQuestions = 0;

		Calendar exams_lasttaken = Calendar.getInstance();
		java.text.SimpleDateFormat sqlDate = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");
		java.text.SimpleDateFormat sqlTime = new java.text.SimpleDateFormat(
				"hh:mm:ss");
		String examDate = sqlDate.format(exams_lasttaken.getTime()).toString();
		String examTime = sqlTime.format(exams_lasttaken.getTime()).toString();

		connection = DbConnectionFactory.getConnection();
		ExamStatisticsBean examStatisticsBean = new ExamStatisticsBean();

		try {
			SQL_Statement = connection.createStatement();
			ResultSet rsquery1 = null;
			rsquery1 = SQL_Statement
					.executeQuery("SELECT * FROM statistics INNER JOIN fccSubelementDesc_en ON statistics.question_element = fccSubelementDesc_en.question_element WHERE statistics.question_element = '"
							+ examType + "'");
			while (rsquery1.next()) {
				exams_taken = rsquery1.getInt("exams_taken");
				exams_passed = rsquery1.getInt("exams_passed");
				exams_failed = rsquery1.getInt("exams_failed");
				minCorrectQuestions = rsquery1.getInt("minCorrectQuestions");
			}
			exams_taken++;
			if (numCorrect >= minCorrectQuestions) {
				exams_passed++;
			} else {
				exams_failed++;
			}
			String SQLUpdate = "UPDATE statistics SET exams_lasttakendate='"
					+ examDate + "' , exams_lasttakentime='" + examTime
					+ "' , exams_passed=" + exams_passed + " , exams_failed="
					+ exams_failed + " , exams_taken=" + exams_taken
					+ " WHERE question_element='" + examType + "';";

			SQL_Statement.execute(SQLUpdate);
			SQL_Statement.close();
			
		} catch (Exception e) {
			log.error("Exception caught in :" + this.getClass().getName() + e);
		} finally {
			// dbTools.DBCloseConnection(dataSource);

			DbConnectionFactory.closeDBConnection(connection);
		}

	}

}
