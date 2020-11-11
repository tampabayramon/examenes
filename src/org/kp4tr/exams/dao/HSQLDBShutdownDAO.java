package org.kp4tr.exams.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.apache.log4j.Logger;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;

/**
 * 
 * @author Ramon Gonzalez
 */
public class HSQLDBShutdownDAO {

	public HSQLDBShutdownDAO() {
	}

	/** Log4J category for logging */
	Logger log = Logger.getLogger(this.getClass());

	private Connection connection = null;

	private Statement SQL_Statement = null;

	public void execute() {

		connection = DbConnectionFactory.getConnection();

		try {
			SQL_Statement = connection.createStatement();
			ResultSet rsquery1 = null;
			rsquery1 = SQL_Statement.executeQuery("SHUTDOWN COMPACT;");

			SQL_Statement.close();
		} catch (Exception e) {
			log.error("Exception caught in :" + this.getClass().getName() + e);
		} finally {
			DbConnectionFactory.closeDBConnection(connection);
		}

	}

}
