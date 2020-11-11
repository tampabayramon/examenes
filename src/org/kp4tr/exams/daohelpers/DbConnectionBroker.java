package org.kp4tr.exams.daohelpers;

/**
 * DbConnectionBroker.
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DbConnectionBroker A servlet-based broker for database connections. Creates
 * and manages a pool of database connections.
 */
public class DbConnectionBroker implements Runnable {
	private static final int CONN_AVAILABLE = 0;

	private static final int CONN_IN_USE = 1;

	private static final int CONN_LOCKED_FOR_HOUSEKEEPING = 2;

	public static final int DEBUG_NONE = 0;

	public static final int DEBUG_REGARDLESS = 0;

	public static final int DEBUG_ERROR = 1;

	public static final int DEBUG_WARNING = 2;

	public static final int DEBUG_INFO = 3;

	private Thread runner;

	private Connection[] connPool;

	private int[] connStatus;

	private long[] connLockTime, connCreateDate;

	private int[] connID;

	private String dbDriver, dbServer, dbLogin, dbPassword, logFileString;

	private int currConnections, connLast;

	private int maxConns, maxConnMSec, maxCheckoutSeconds, debugLevel;

	// available: set to false on destroy, checked by getConnection()
	private boolean available = true;

	private PrintStream log;

	private SQLWarning currSQLWarning;

	private String pid;

	private final int DEFAULTMAXCHECKOUTSECONDS = 60;

	private final int DEFAULTDEBUGLEVEL = 2;

	/**
	 * Creates a new Connection Broker.<br>
	 * dbDriver: JDBC driver. e.g. 'oracle.jdbc.driver.OracleDriver'<br>
	 * dbServer: JDBC connect string. e.g.
	 * 'jdbc:oracle:thin:@203.92.21.109:1526:orcl'<br>
	 * dbLogin: Database login name. e.g. 'Scott'<br>
	 * dbPassword: Database password. e.g. 'Tiger'<br>
	 * minConns: Minimum number of connections to start with.<br>
	 * maxConns: Maximum number of connections in dynamic pool.<br>
	 * logFileString: Absolute path name for log file. e.g. 'c:/temp/mylog.log'
	 * <br>
	 * logWriter: PrintStream to write the log to, maxConnTime: Time in days
	 * between connection resets. (Reset does a basic cleanup)<br>
	 * logAppend: Append to logfile (optional)<br>
	 * maxCheckoutSeconds: Max time a connection can be checked out before being
	 * recycled. Zero value turns option off, default is 60 seconds. debugLevel:
	 * Level of debug messages output to the log file. 0 -> no messages, 1 ->
	 * Errors, 2 -> Warnings, 3 -> Information
	 */
	public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns,
			String logFileString, double maxConnTime) throws IOException {
		setupLogger(logFileString, false);
		setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns,
				maxConns, maxConnTime, false, DEFAULTMAXCHECKOUTSECONDS,
				DEFAULTDEBUGLEVEL);
	}

	/**
	 * Special constructor to accept a premade PrintStream
	 */
	public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns,
			PrintStream logWriter, double maxConnTime) throws IOException {
		this.log = logWriter;
		setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns,
				maxConns, maxConnTime, false, DEFAULTMAXCHECKOUTSECONDS,
				DEFAULTDEBUGLEVEL);
	}

	public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns,
			PrintStream logWriter, double maxConnTime, boolean logAppend,
			int maxCheckoutSeconds, int debugLevel) throws IOException {
		this.log = logWriter;
		setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns,
				maxConns, maxConnTime, logAppend, maxCheckoutSeconds,
				debugLevel);
	}

	/**
	 * Special constructor to handle logfile append
	 */
	public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns,
			String logFileString, double maxConnTime, boolean logAppend)
			throws IOException {
		setupLogger(logFileString, logAppend);
		setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns,
				maxConns, maxConnTime, logAppend, DEFAULTMAXCHECKOUTSECONDS,
				DEFAULTDEBUGLEVEL);
	}

	/**
	 * Special constructor to handle connection checkout expiration
	 */
	public DbConnectionBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns,
			String logFileString, double maxConnTime, boolean logAppend,
			int maxCheckoutSeconds, int debugLevel) throws IOException {
		setupLogger(logFileString, logAppend);
		setupBroker(dbDriver, dbServer, dbLogin, dbPassword, minConns,
				maxConns, maxConnTime, logAppend, maxCheckoutSeconds,
				debugLevel);
	}

	private void setupLogger(String logFileString, boolean logAppend)
			throws IOException {
		FileOutputStream fout;

		this.logFileString = logFileString;

		try {
			fout = new FileOutputStream(logFileString, logAppend);
			log = new PrintStream(fout, true);
		} catch (IOException e1) {
			// Can't open the requested file. Open the default file.
			try {
				fout = new FileOutputStream("DCB_" + System.currentTimeMillis()
						+ ".log", logAppend);
				log = new PrintStream(fout, true);
			} catch (IOException e2) {
				throw new IOException("Can't open any log file");
			}
		}

		// Write the pid file (used to clean up dead/broken connection)
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy.MM.dd G 'at' hh:mm:ss a zzz");
		Date nowc = new Date();
		pid = formatter.format(nowc);

		BufferedWriter pidout;
		pidout = new BufferedWriter(new FileWriter(logFileString + "pid"));
		pidout.write(pid);
		pidout.close();
	}

	private void setupBroker(String dbDriver, String dbServer, String dbLogin,
			String dbPassword, int minConns, int maxConns, double maxConnTime,
			boolean logAppend, int maxCheckoutSeconds, int debugLevel)
			throws IOException {
		connPool = new Connection[maxConns];
		connStatus = new int[maxConns];
		connLockTime = new long[maxConns];
		connCreateDate = new long[maxConns];
		connID = new int[maxConns];
		currConnections = minConns;
		this.maxConns = maxConns;
		this.dbDriver = dbDriver;
		this.dbServer = dbServer;
		this.dbLogin = dbLogin;
		this.dbPassword = dbPassword;
		this.maxCheckoutSeconds = maxCheckoutSeconds;
		this.debugLevel = debugLevel;
		maxConnMSec = (int) (maxConnTime * 86400000.0); // 86400 sec/day
		if (maxConnMSec < 30000) { // Recycle no less than 30 seconds.
			maxConnMSec = 30000;
		}

		debug(DEBUG_REGARDLESS, "Starting DbConnectionBroker Version 1.0.13:");
		debug(DEBUG_REGARDLESS, "dbDriver = " + dbDriver);
		debug(DEBUG_REGARDLESS, "dbServer = " + dbServer);
		debug(DEBUG_REGARDLESS, "dbLogin = " + dbLogin);
		debug(DEBUG_REGARDLESS, "minconnections = " + minConns);
		debug(DEBUG_REGARDLESS, "maxconnections = " + maxConns);
		debug(DEBUG_REGARDLESS, "Total refresh interval = " + maxConnTime
				+ " days");
		if (logFileString != null) {
			debug(DEBUG_REGARDLESS, "log file = " + logFileString);
		}
		debug(DEBUG_REGARDLESS, "logAppend = " + logAppend);
		debug(DEBUG_REGARDLESS, "maxCheckoutSeconds = " + maxCheckoutSeconds);
		debug(DEBUG_REGARDLESS, "debugLevel = " + debugLevel);
		debug(DEBUG_REGARDLESS, "class.toString = " + this.toString());
		debug(DEBUG_REGARDLESS, "class.hashCode = " + this.hashCode());
		debug(DEBUG_REGARDLESS, "classLoader = "
				+ this.getClass().getClassLoader());

		/*
		 * Initialize the pool of connections with the mininum connections:
		 * Problems creating connections may be caused during reboot when the
		 * servlet is started before the database is ready. Handle this by
		 * waiting and trying again. The loop allows 5 minutes for db reboot.
		 */
		boolean connectionsSucceeded = false;
		int dbLoop = 20;

		try {
			for (int i = 1; i < dbLoop; i++) {
				try {
					for (int j = 0; j < currConnections; j++) {
						createConn(j);
					}
					connectionsSucceeded = true;
					break;
				} catch (SQLException e) {
					debug(DEBUG_ERROR, "Attempt (" + String.valueOf(i) + " of "
							+ String.valueOf(dbLoop)
							+ ") failed to create new connections "
							+ "set at startup: ");
					debug(DEBUG_ERROR, "   " + e);
					debug(DEBUG_ERROR, "    Will try again in 15 seconds");

					try {
						Thread.sleep(15000);
					} catch (InterruptedException e1) {
					}
				}
			}

			if (!connectionsSucceeded) {
				// All attempts at connecting to db exhausted
				debug(DEBUG_ERROR, "");
				debug(DEBUG_ERROR, "All attempts at connecting to "
						+ "database exhausted");
				throw new IOException();
			}
		} catch (Exception e) {
			throw new IOException();
		}

		// Fire up the background housekeeping thread
		runner = new Thread(this);
		runner.start();

	} // End DbConnectionBroker()

	/**
	 * Make sure the log file is the one this instance opened. If not, clean it
	 * up!
	 */
	private void checkPidFile() {
		try {
			if (logFileString != null) {
				FileReader fin = new FileReader(logFileString + "pid");
				BufferedReader in = new BufferedReader(fin);
				String curr_pid = in.readLine();

				if (!curr_pid.equals(pid)) {
					log.close();

					// Connections are dead, close them
					for (int i = 0; i < currConnections; i++) {
						try {
							connPool[i].close();
						} catch (SQLException e1) {
						} // ignore
					}

					// Returning from the run() method kills the thread
					return;
				}

				in.close();
			}
		} catch (IOException e1) {
			debug(DEBUG_REGARDLESS, "Can't read the file for pid info: "
					+ logFileString + "pid");
		}
	}

	/** Get any Warnings on connections and print to event file */
	private void logWarnings(int index) {
		try {
			currSQLWarning = connPool[index].getWarnings();
			if (currSQLWarning != null) {
				debug(DEBUG_WARNING, "Warnings on connection "
						+ String.valueOf(index) + " " + currSQLWarning);
				connPool[index].clearWarnings();
			}
		} catch (SQLException e) {
			debug(DEBUG_WARNING, "Cannot access Warnings: " + e);
		}
	}

	private void debug(int level, String message) {
		if (level > debugLevel) {
			return;
		}

		switch (level) {
		case DEBUG_NONE:
			log.print(new Date() + ": DbConnectionBroker:         ");
			break;

		case DEBUG_ERROR:
			log.print(new Date() + ": DbConnectionBroker:ERROR:   ");
			break;

		case DEBUG_WARNING:
			log.print(new Date() + ": DbConnectionBroker:WARNING: ");
			break;

		case DEBUG_INFO:
			log.print(new Date() + ": DbConnectionBroker:INFO:    ");
			break;
		}

		log.println(message);
	}

	/**
	 * Test connection with a createStatement call. If it fails, recycle the
	 * connection.
	 */
	private void checkConnection(int index) {
		Statement stmt = null;
		long age, timeInUse, maxCheckoutMillis;

		maxCheckoutMillis = maxCheckoutSeconds * 1000;
		age = System.currentTimeMillis() - connCreateDate[index];

		try {
			synchronized (connStatus) {
				if (connStatus[index] != CONN_AVAILABLE) {
					// Check the time it's been checked out and recycle
					timeInUse = System.currentTimeMillis()
							- connLockTime[index];

					debug(DEBUG_WARNING, "Connection " + index + " in use for "
							+ timeInUse + " ms");

					if ((maxCheckoutMillis != 0) && (timeInUse > maxCheckoutMillis)) {
						debug(DEBUG_WARNING, "Connection " + index
								+ " failed to be returned in time. "
								+ " Recycling...");
						throw new SQLException();
					}

					// Done with this one
					return;
				}

				connStatus[index] = CONN_LOCKED_FOR_HOUSEKEEPING;
			}

			// Force a reset at the max conn time
			if (age > maxConnMSec) {
				throw new SQLException();
			}

			stmt = connPool[index].createStatement();
			connStatus[index] = CONN_AVAILABLE;

			// Some DBs return an object even if DB is shut down
			if (connPool[index].isClosed()) {
				throw new SQLException();
			}
		} catch (SQLException e) {
			// Connection has a problem, restart it
			debug(DEBUG_WARNING, "Recycling connection " + index);

			try {
				connPool[index].close();
			} catch (SQLException e0) {
				debug(DEBUG_ERROR, "Can't close connection! Might have been "
						+ " closed already.  Trying to recycle anyway... ("
						+ e0 + ")");
			}

			try {
				createConn(index);
			} catch (SQLException e1) {
				debug(DEBUG_ERROR, "Failed to create connection: " + e1);
				// XXX wtf?
				connStatus[index] = CONN_AVAILABLE;
			}
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e1) {
			}
		}
	}

	/**
	 * Housekeeping thread. Runs in the background with low CPU overhead.
	 * Connections are checked for warnings and closure and are periodically
	 * restarted. This thread is a catchall for corrupted connections and
	 * prevents the buildup of open cursors. (Open cursors result when the
	 * application fails to close a Statement). This method acts as fault
	 * tolerance for bad connection/statement programming.
	 */
	public void run() {
		while (true) {
			checkPidFile();

			for (int i = 0; i < currConnections; i++) {
				logWarnings(i);
			}

			for (int i = 0; i < currConnections; i++) {
				checkConnection(i);
			}

			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// Returning from the run method sets the internal
				// flag referenced by Thread.isAlive() to false.
				// This is required because we don't use stop() to
				// shutdown this thread.
				return;
			}
		}
	}

	/**
	 * This method hands out the connections in round-robin order. This prevents
	 * a faulty connection from locking up an application entirely. A browser
	 * 'refresh' will get the next connection while the faulty connection is
	 * cleaned up by the housekeeping thread.
	 * 
	 * If the min number of threads are ever exhausted, new threads are added up
	 * the the max thread count. Finally, if all threads are in use, this method
	 * waits 2 seconds and tries again, up to ten times. After that, it returns
	 * a null.
	 */
	public Connection getConnection() {
		Connection conn = null;

		if (available) {
			boolean gotOne = false;

			for (int outerloop = 1; outerloop <= 10; outerloop++) {
				try {
					int loop = 0;
					int roundRobin = connLast + 1;
					if (roundRobin >= currConnections) {
						roundRobin = 0;
					}

					do {
						synchronized (connStatus) {
							if ((connStatus[roundRobin] == CONN_AVAILABLE)
									&& !connPool[roundRobin].isClosed()) {
								conn = connPool[roundRobin];
								connStatus[roundRobin] = CONN_IN_USE;
								connLockTime[roundRobin] = System
										.currentTimeMillis();
								connLast = roundRobin;
								gotOne = true;
								break;
							} else {
								loop++;
								roundRobin = (roundRobin + 1) % currConnections;
							}
						}
					} while (!gotOne && (loop < currConnections));

				} catch (SQLException e1) {
					debug(DEBUG_REGARDLESS, e1.toString());
				}

				if (gotOne) {
					break;
				}

				// we don't got one
				synchronized (this) {
					if (currConnections < maxConns) {
						try {
							createConn(currConnections);
							currConnections++;
						} catch (SQLException e) {
							debug(DEBUG_ERROR,
									"Unable to create new connection: " + e);
						}
					}

					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
					}

					debug(DEBUG_ERROR, "Connections Exhausted!  "
							+ "Will wait and try again in loop "
							+ String.valueOf(outerloop));
				}
			} // End of try 10 times loop
		} else {
			debug(DEBUG_ERROR, "Unsuccessful getConnection() "
					+ "request during destroy()");
		} // End if(available)

		debug(DEBUG_INFO, "Handing out connection " + idOfConnection(conn));
		return conn;
	}

	/**
	 * Returns the local JDBC ID for a connection.
	 */
	public int idOfConnection(Connection conn) {
		int match, tag;

		try {
			tag = conn.hashCode();
		} catch (NullPointerException e1) {
			tag = -1;
		}

		match = -1;

		for (int i = 0; i < currConnections; i++) {
			if (connID[i] == tag) {
				match = i;
				break;
			}
		}

		return match;
	}

	/**
	 * Frees a connection. Replaces connection back into the main pool for
	 * reuse.
	 */
	public String freeConnection(Connection conn) {
		String res = "";

		int thisconn = idOfConnection(conn);

		if (thisconn >= 0) {
			debug(DEBUG_INFO, "Freeing connection " + thisconn);
			connStatus[thisconn] = CONN_AVAILABLE;
			res = "freed " + conn.toString();
		} else {
			debug(DEBUG_ERROR, "idOfConnection returned -1!!!");
		}

		return res;
	}

	/**
	 * Returns the age of a connection -- the time since it was handed out to an
	 * application.
	 */
	public long getAge(Connection conn) {
		int thisconn = idOfConnection(conn);
		return System.currentTimeMillis() - connLockTime[thisconn];
	}

	private void createConn(int i) throws SQLException {
		Date now = new Date();

		try {

			Class.forName(dbDriver);

			connPool[i] = DriverManager.getConnection(dbServer, dbLogin,
					dbPassword);

			connStatus[i] = CONN_AVAILABLE;
			connID[i] = connPool[i].hashCode();
			connLockTime[i] = 0;
			connCreateDate[i] = now.getTime();
		} catch (ClassNotFoundException e2) {
			System.out.println("---------------" + e2.getMessage());
			debug(DEBUG_ERROR, "Error creating connection: " + e2);
		}
	}

	/**
	 * Shuts down the housekeeping thread and closes all connections in the
	 * pool. Call this method from the destroy() method of the servlet.
	 * Multi-phase shutdown having following sequence:
	 * <OL>
	 * <LI><code>getConnection()</code> will refuse to return connections.
	 * <LI>The housekeeping thread is shut down.<br>
	 * Up to the time of <code>millis</code> milliseconds after shutdown of
	 * the housekeeping thread, <code>freeConnection()</code> can still be
	 * called to return used connections.
	 * <LI>After <code>millis</code> milliseconds after the shutdown of the
	 * housekeeping thread, all connections in the pool are closed.
	 * <LI>If any connections were in use while being closed then a
	 * <code>SQLException</code> is thrown.
	 * <LI>The log is closed.
	 * </OL>
	 * <br>
	 * Call this method from a servlet destroy() method.
	 * 
	 * @param millis
	 *            the time to wait in milliseconds.
	 * @exception SQLException
	 *                if connections were in use after <code>millis</code>.
	 */
	public void destroy(int millis) throws SQLException {
		debug(DEBUG_REGARDLESS, "destroy(" + millis + ")");

		// Stop issuing connections
		available = false;

		// Shut down the background housekeeping thread
		runner.interrupt();

		// Wait until the housekeeping thread has died.
		try {
			runner.join(millis);
		} catch (InterruptedException e) {
		}

		/*
		 * The housekeeping thread could still be running (e.g. if millis is too
		 * small). This case is ignored. At worst, this method will throw an
		 * exception with the clear indication that the timeout was too short.
		 */

		long startTime = System.currentTimeMillis();

		// Wait for freeConnection() to return any connections
		// that are still used at this time.
		int useCount;

		while (((useCount = getUseCount()) > 0)
				&& (System.currentTimeMillis() - startTime <= millis)) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}

		// Close all connections, whether safe or not
		for (int i = 0; i < currConnections; i++) {
			try {
				connPool[i].close();
			} catch (SQLException e1) {
				debug(DEBUG_ERROR, "Unable to close connections on Destroy");
			}
		}

		if (useCount > 0) {
			String msg = "Unsafe shutdown: Had to close " + useCount
					+ " active DB connections after " + millis + "ms";
			debug(DEBUG_REGARDLESS, msg);

			// Close all open files
			if (logFileString != null) {
				log.close();
			}

			// Throwing following Exception is essential because servlet
			// authors are likely to have their own error logging requirements.
			throw new SQLException(msg);
		}

		if (logFileString != null) {
			log.close();
		}

	} // End destroy()

	/**
	 * Less safe shutdown. Uses default timeout value. This method simply calls
	 * the <code>destroy()</code> method with a <code>millis</code> value of
	 * 10000 (10 seconds) and ignores <code>SQLException</code> thrown by that
	 * method.
	 * 
	 * @see #destroy(int)
	 */
	public void destroy() {
		debug(DEBUG_WARNING, "In destroy()");

		try {
			destroy(10000);
		} catch (SQLException e) {
		}
	}

	/**
	 * Returns the number of connections in use.
	 */
	// This method could be reduced to return a counter that is
	// maintained by all methods that update connStatus.
	// However, it is more efficient to do it this way because:
	// Updating the counter would put an additional burden on the most
	// frequently used methods; in comparison, this method is
	// rarely used (although essential).
	public int getUseCount() {
		int useCount = 0;

		synchronized (connStatus) {
			for (int i = 0; i < currConnections; i++) {
				if (connStatus[i] != CONN_AVAILABLE) {
					useCount++;
				}
			}
		}

		return useCount;
	}

	/**
	 * Returns the number of connections in the dynamic pool.
	 */
	public int getSize() {
		return currConnections;
	}
} // End class

