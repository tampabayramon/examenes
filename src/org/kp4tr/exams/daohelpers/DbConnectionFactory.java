/*
 * DbTools.java
 *
 * Created on August 8, 2003, 11:45 PM
 */

package org.kp4tr.exams.daohelpers;

import java.sql.Connection;

import org.apache.log4j.Logger;

/**
 * 
 * @author Ramon Gonzalez
 */

/**
 * This class provides methods for a centralized connection to the database
 * using a connection pool. Currently using the www.javaexchange.com
 * DBConnectionBroker connection pooling.
 */

public final class DbConnectionFactory {

	/** Creates a new instance of DbTools */

	/** Log4J category for logging */
	private static Logger log = Logger.getLogger(DbConnectionFactory.class);

	private static DbConnectionBroker dataSource = null;

	public DbConnectionFactory(Object dataSource) {
		super();
		DbConnectionFactory.dataSource = (DbConnectionBroker) dataSource;
	}

	/*
	 * public Connection DBOpenConnection(DbConnectionBroker dataSource) {
	 * 
	 * try { connection = dataSource.getConnection(); log.debug("Opening
	 * database connection " + dataSource.idOfConnection(connection)); } catch
	 * (Exception e) { log.error("Exception caught in DBOpenConnection():" + e);
	 * e.printStackTrace(); } return connection; }
	 */

	public static void closeDBConnection(Connection connection) {
		try {
			log
					.debug("Closing database connection "
							+ DbConnectionFactory.dataSource
									.idOfConnection(connection));
			DbConnectionFactory.dataSource.freeConnection(connection);

		} catch (Exception e) {
			log.error("Exception caught in DBCloseConnection():" + e);
			e.printStackTrace();
		}
	}

	public static DbConnectionBroker initDBConnection(
			DBConnectionBean jdbcConfigurator) {
		// instantiate & configure a db connection pool
		try {
			String JDBCDriverClass = jdbcConfigurator.getJdbcDriver();
			String JDBCConnectionUrl = jdbcConfigurator.getJdbcConnection();
			String JDBCUserId = jdbcConfigurator.getJdbcUserId();
			String JDBCUserIdPassword = jdbcConfigurator.getJdbcPassword();
			int dbPoolMinConn = jdbcConfigurator.getJdbcDBPoolMinConn();
			int dbPoolMaxConn = jdbcConfigurator.getJdbcDBPoolMaxConn();
			double dbPoolMaxConnTime = jdbcConfigurator
					.getJdbcDBPoolMaxConnTime();
			int dbPoolMaxCheckoutSeconds = jdbcConfigurator
					.getJdbcDBPoolMaxCheckoutSeconds();
			int dbPoolDebugLevel = jdbcConfigurator.getJdbcDBPoolDebugLevel();
			String dbLogFile = jdbcConfigurator.getJdbcDBLogFile();
			boolean dbPoolLogAppend = false;
			if (jdbcConfigurator.getJdbcDBPoolLogAppend().equals("true")) {
				dbPoolLogAppend = true;
			}

			try {
				DbConnectionFactory.dataSource = new DbConnectionBroker(
						JDBCDriverClass, JDBCConnectionUrl, JDBCUserId,
						JDBCUserIdPassword, dbPoolMinConn, dbPoolMaxConn,
						dbLogFile, dbPoolMaxConnTime, dbPoolLogAppend,
						dbPoolMaxCheckoutSeconds, dbPoolDebugLevel);
				log.info("Using JDBC Driver: " + JDBCDriverClass);
				log.info("Connection URL: " + JDBCConnectionUrl);
				log.info("Minimum connections:" + dbPoolMinConn);
				log.info("Maximum connections:" + dbPoolMaxConn);
				log.info("Available connections:"
						+ DbConnectionFactory.dataSource.getSize());
			} catch (Exception e) {
				log
						.error("Exception while starting the DB pool in DBInitConnection():"
								+ e);
			}
		} catch (Exception e) {
			log.error("Exception caught in DBInitConnection():" + e);
		}
		return DbConnectionFactory.dataSource;
	}

	/*
	 * public static DbConnectionBroker getDataSource() { return dataSource; }
	 */

	public static Connection getConnection() {
		return DbConnectionFactory.dataSource.getConnection();

	}
}
