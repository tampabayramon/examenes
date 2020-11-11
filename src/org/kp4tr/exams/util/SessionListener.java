package org.kp4tr.exams.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public final class SessionListener implements HttpSessionListener {

	static private long activeSessions = 0;

	static private long maxActiveSessions = 0;

	static private long totalSessions = 0;

	static private String lastSessionDateTime = "";

	/**
	 * @return Returns the activeSessions.
	 */
	public static long getActiveSessions() {
		return activeSessions;
	}

	public void sessionCreated(HttpSessionEvent se) {
		activeSessions++;
		if (activeSessions > maxActiveSessions) {
			maxActiveSessions = activeSessions;

		}
		totalSessions++;

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new java.text.SimpleDateFormat(
				"MMM dd, yyyy HH:mm:ss:aa");
		lastSessionDateTime = sdf.format(cal.getTime());

		HttpSession session = se.getSession();

		ArrayList sessionTotals = new ArrayList();
		sessionTotals.add(Long.toString(activeSessions));
		sessionTotals.add(Long.toString(maxActiveSessions));
		sessionTotals.add(Long.toString(totalSessions));
		sessionTotals.add(lastSessionDateTime);

		session.setAttribute(SessionKeys.SESSIONLISTENER, sessionTotals);

	}

	/**
	 * Notification that a session was invalidated.
	 * 
	 * @param se
	 *            the notification event
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		activeSessions--;

	}

	/**
	 * @return Returns the lastSessionDateTime.
	 */
	public static String getLastSessionDateTime() {
		return lastSessionDateTime;
	}

	/**
	 * @return Returns the maxActiveSessions.
	 */
	public static long getMaxActiveSessions() {
		return maxActiveSessions;
	}
}