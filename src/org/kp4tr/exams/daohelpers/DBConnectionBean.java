/*
 * JDBCConfigurator.java
 *
 * Created on January 24, 2004, 2:44 AM
 */

package org.kp4tr.exams.daohelpers;

/**
 * 
 * @author Owner
 */
public class DBConnectionBean {

	/** Holds value of property jdbcDriver. */
	private String jdbcDriver;

	/** Holds value of property jdbcConnection. */
	private String jdbcConnection;

	/** Holds value of property jdbcUserId. */
	private String jdbcUserId;

	/** Holds value of property jdbcPassword. */
	private String jdbcPassword;

	/** Holds value of property jdbcDBPoolMinConn. */
	private int jdbcDBPoolMinConn;

	/** Holds value of property jdbcDBPoolMaxConn. */
	private int jdbcDBPoolMaxConn;

	/** Holds value of property jdbcDBPoolMaxConnTime. */
	private double jdbcDBPoolMaxConnTime;

	/** Holds value of property jdbcDBPoolMaxCheckoutSeconds. */
	private int jdbcDBPoolMaxCheckoutSeconds;

	/** Holds value of property jdbcDBPoolLogAppend. */
	private String jdbcDBPoolLogAppend;

	/** Holds value of property jdbcDBPoolDebugLevel. */
	private int jdbcDBPoolDebugLevel;

	/** Holds value of property jdbcDBLogFile. */
	private String jdbcDBLogFile;

	/** Creates a new instance of Configurator2 */
	public DBConnectionBean() {
	}

	/**
	 * Getter for property jdbcDriver.
	 * 
	 * @return Value of property jdbcDriver.
	 * 
	 */
	public String getJdbcDriver() {
		return this.jdbcDriver;
	}

	/**
	 * Setter for property jdbcDriver.
	 * 
	 * @param jdbcDriver
	 *            New value of property jdbcDriver.
	 * 
	 */
	public void setJdbcDriver(String jdbcDriver) {
		this.jdbcDriver = jdbcDriver;
	}

	/**
	 * Getter for property jdbcConnection.
	 * 
	 * @return Value of property jdbcConnection.
	 * 
	 */
	public String getJdbcConnection() {
		return this.jdbcConnection;
	}

	/**
	 * Setter for property jdbcConnection.
	 * 
	 * @param jdbcConnection
	 *            New value of property jdbcConnection.
	 * 
	 */
	public void setJdbcConnection(String jdbcConnection) {
		this.jdbcConnection = jdbcConnection;
	}

	/**
	 * Getter for property jdbcUserId.
	 * 
	 * @return Value of property jdbcUserId.
	 * 
	 */
	public String getJdbcUserId() {
		return this.jdbcUserId;
	}

	/**
	 * Setter for property jdbcUserId.
	 * 
	 * @param jdbcUserId
	 *            New value of property jdbcUserId.
	 * 
	 */
	public void setJdbcUserId(String jdbcUserId) {
		this.jdbcUserId = jdbcUserId;
	}

	/**
	 * Getter for property jdbcPassword.
	 * 
	 * @return Value of property jdbcPassword.
	 * 
	 */
	public String getJdbcPassword() {
		return this.jdbcPassword;
	}

	/**
	 * Setter for property jdbcPassword.
	 * 
	 * @param jdbcPassword
	 *            New value of property jdbcPassword.
	 * 
	 */
	public void setJdbcPassword(String jdbcPassword) {
		this.jdbcPassword = jdbcPassword;
	}

	/**
	 * Getter for property jdbcDBPoolMinConn.
	 * 
	 * @return Value of property jdbcDBPoolMinConn.
	 * 
	 */
	public int getJdbcDBPoolMinConn() {
		return this.jdbcDBPoolMinConn;
	}

	/**
	 * Setter for property jdbcDBPoolMinConn.
	 * 
	 * @param jdbcDBPoolMinConn
	 *            New value of property jdbcDBPoolMinConn.
	 * 
	 */
	public void setJdbcDBPoolMinConn(int jdbcDBPoolMinConn) {
		this.jdbcDBPoolMinConn = jdbcDBPoolMinConn;
	}

	/**
	 * Getter for property jdbcDBPoolMaxConn.
	 * 
	 * @return Value of property jdbcDBPoolMaxConn.
	 * 
	 */
	public int getJdbcDBPoolMaxConn() {
		return this.jdbcDBPoolMaxConn;
	}

	/**
	 * Setter for property jdbcDBPoolMaxConn.
	 * 
	 * @param jdbcDBPoolMaxConn
	 *            New value of property jdbcDBPoolMaxConn.
	 * 
	 */
	public void setJdbcDBPoolMaxConn(int jdbcDBPoolMaxConn) {
		this.jdbcDBPoolMaxConn = jdbcDBPoolMaxConn;
	}

	/**
	 * Getter for property jdbcDBPoolMaxConnTime.
	 * 
	 * @return Value of property jdbcDBPoolMaxConnTime.
	 * 
	 */
	public double getJdbcDBPoolMaxConnTime() {
		return this.jdbcDBPoolMaxConnTime;
	}

	/**
	 * Setter for property jdbcDBPoolMaxConnTime.
	 * 
	 * @param jdbcDBPoolMaxConnTime
	 *            New value of property jdbcDBPoolMaxConnTime.
	 * 
	 */
	public void setJdbcDBPoolMaxConnTime(double jdbcDBPoolMaxConnTime) {
		this.jdbcDBPoolMaxConnTime = jdbcDBPoolMaxConnTime;
	}

	/**
	 * Getter for property jdbcDBPoolMaxCheckoutSeconds.
	 * 
	 * @return Value of property jdbcDBPoolMaxCheckoutSeconds.
	 * 
	 */
	public int getJdbcDBPoolMaxCheckoutSeconds() {
		return this.jdbcDBPoolMaxCheckoutSeconds;
	}

	/**
	 * Setter for property jdbcDBPoolMaxCheckoutSeconds.
	 * 
	 * @param jdbcDBPoolMaxCheckoutSeconds
	 *            New value of property jdbcDBPoolMaxCheckoutSeconds.
	 * 
	 */
	public void setJdbcDBPoolMaxCheckoutSeconds(int jdbcDBPoolMaxCheckoutSeconds) {
		this.jdbcDBPoolMaxCheckoutSeconds = jdbcDBPoolMaxCheckoutSeconds;
	}

	/**
	 * Getter for property jdbcDBPoolLogAppend.
	 * 
	 * @return Value of property jdbcDBPoolLogAppend.
	 * 
	 */
	public String getJdbcDBPoolLogAppend() {
		return this.jdbcDBPoolLogAppend;
	}

	/**
	 * Setter for property jdbcDBPoolLogAppend.
	 * 
	 * @param jdbcDBPoolLogAppend
	 *            New value of property jdbcDBPoolLogAppend.
	 * 
	 */
	public void setJdbcDBPoolLogAppend(String jdbcDBPoolLogAppend) {
		this.jdbcDBPoolLogAppend = jdbcDBPoolLogAppend;
	}

	/**
	 * Getter for property jdbcDBPoolDebugLevel.
	 * 
	 * @return Value of property jdbcDBPoolDebugLevel.
	 * 
	 */
	public int getJdbcDBPoolDebugLevel() {
		return this.jdbcDBPoolDebugLevel;
	}

	/**
	 * Setter for property jdbcDBPoolDebugLevel.
	 * 
	 * @param jdbcDBPoolDebugLevel
	 *            New value of property jdbcDBPoolDebugLevel.
	 * 
	 */
	public void setJdbcDBPoolDebugLevel(int jdbcDBPoolDebugLevel) {
		this.jdbcDBPoolDebugLevel = jdbcDBPoolDebugLevel;
	}

	/**
	 * Getter for property jdbcDBLogFile.
	 * 
	 * @return Value of property jdbcDBLogFile.
	 * 
	 */
	public String getJdbcDBLogFile() {
		return this.jdbcDBLogFile;
	}

	/**
	 * Setter for property jdbcDBLogFile.
	 * 
	 * @param jdbcDBLogFile
	 *            New value of property jdbcDBLogFile.
	 * 
	 */
	public void setJdbcDBLogFile(String jdbcDBLogFile) {
		this.jdbcDBLogFile = jdbcDBLogFile;
	}

}
