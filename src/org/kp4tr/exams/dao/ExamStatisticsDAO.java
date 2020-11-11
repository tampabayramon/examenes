package org.kp4tr.exams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.kp4tr.exams.beans.ExamStatisticsBean;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;

/**
 * 
 * @author Ramon Gonzalez
 */
public class ExamStatisticsDAO {

	/** Creates a new instance of examStatistics */
	public ExamStatisticsDAO() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	private Connection connection = null;

	private Statement SQL_Statement = null;

	public ExamStatisticsBean execute() {

		connection = DbConnectionFactory.getConnection();

		ExamStatisticsBean examStatisticsBean = new ExamStatisticsBean();

		try {
			SQL_Statement = connection.createStatement();
			ResultSet rsquery1 = null;
			rsquery1 = SQL_Statement
					.executeQuery("SELECT * FROM statistics WHERE question_element = 'T';");
			while (rsquery1.next()) {
/*				String question_element = rsquery1
						.getString("question_element");*/
				examStatisticsBean.setTechnicianTaken(rsquery1
						.getInt("exams_taken"));
				examStatisticsBean.setTechnicianPassed(rsquery1
						.getInt("exams_passed"));
				examStatisticsBean.setTechnicianFailed(rsquery1
						.getInt("exams_failed"));
				examStatisticsBean.setTechnician_lasttakendate(rsquery1.getDate(
						"exams_lasttakendate").toString());
				examStatisticsBean.setTechnician_lasttakentime(rsquery1.getTime(
						"exams_lasttakentime").toString());
			}
			rsquery1 = SQL_Statement
					.executeQuery("SELECT * FROM statistics WHERE question_element = 'G';");
			while (rsquery1.next()) {
/*				String question_element = rsquery1
						.getString("question_element");
*/				examStatisticsBean.setGeneralTaken(rsquery1.getInt("exams_taken"));
				examStatisticsBean
						.setGeneralPassed(rsquery1.getInt("exams_passed"));
				examStatisticsBean
						.setGeneralFailed(rsquery1.getInt("exams_failed"));
				examStatisticsBean.setGeneral_lasttakendate(rsquery1.getDate(
						"exams_lasttakendate").toString());
				examStatisticsBean.setGeneral_lasttakentime(rsquery1.getTime(
						"exams_lasttakentime").toString());
			}
			rsquery1 = SQL_Statement
					.executeQuery("SELECT * FROM statistics WHERE question_element = 'E';");
			while (rsquery1.next()) {
/*				String question_element = rsquery1
						.getString("question_element");*/
				examStatisticsBean.setExtraTaken(rsquery1.getInt("exams_taken"));
				examStatisticsBean.setExtraPassed(rsquery1.getInt("exams_passed"));
				examStatisticsBean.setExtraFailed(rsquery1.getInt("exams_failed"));
				examStatisticsBean.setExtra_lasttakendate(rsquery1.getDate(
						"exams_lasttakendate").toString());
				examStatisticsBean.setExtra_lasttakentime(rsquery1.getTime(
						"exams_lasttakentime").toString());
			}

			SQL_Statement.close();
		} catch (Exception e) {
			log.error("Exception caught in :" + this.getClass().getName() + e);
		} finally {
			// dbTools.DBCloseConnection(dataSource);

			DbConnectionFactory.closeDBConnection(connection);
		}

		return examStatisticsBean;
	}

}
