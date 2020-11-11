package org.kp4tr.exams.util;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.directwebremoting.WebContext;
import org.directwebremoting.WebContextFactory;

public class SessionUtil {

	WebContext ctx = WebContextFactory.get();

	HttpServletRequest request = ctx.getHttpServletRequest();

	HttpSession session = request.getSession();

	String lastAccessTimeString = "";

	long currentAccessTime = 0;

	String currentAccessTimeString = "";

	public String getServerInfo() {
		return WebContextFactory.get().getServletContext().getServerInfo()
				+ " running on JDK "
				+ System.getProperty("java.specification.version")
				+ " using DWR " + WebContextFactory.get().getVersion();
	}

	public String getSessionMaxInactiveInterval() {
		return Integer.toString(session.getMaxInactiveInterval());
	}

	public String setSessionMaxInactiveInterval(int newMaxInactiveInterval) {
		session.setMaxInactiveInterval(newMaxInactiveInterval);
		return "New session maxInactiveInterval:"
				+ session.getMaxInactiveInterval();
	}

	public String getLastAccessedTime() {
		HttpSession session = WebContextFactory.get().getSession();
		long creationTime = session.getCreationTime();
		currentAccessTime = session.getLastAccessedTime();
		this.currentAccessTimeString = new java.util.Date(currentAccessTime)
				.toString();
		if (session.getAttribute("lastAccessedTime") != null) {
			this.lastAccessTimeString = (String) session
					.getAttribute("lastAccessedTime");
		}

		session.setAttribute("lastAccessedTime", this.currentAccessTimeString);
		return "C: " + this.currentAccessTimeString + " L: "
				+ this.lastAccessTimeString;
	}

	public String getCreationTime() {
		long theTime = WebContextFactory.get().getSession().getCreationTime();
		return new java.util.Date(theTime).toString();
	}

	public String invalidateSession() {
		HttpSession session = WebContextFactory.get().getSession();
		session.invalidate();
		return "Session invalidated.";
	}

	public String getSessionTotals() {
		HttpSession session = WebContextFactory.get().getSession();
		ArrayList sessionTotals = (ArrayList) session
				.getAttribute(SessionKeys.SESSIONLISTENER);
		String str = (String) sessionTotals.get(0) + " ";
		str += (String) sessionTotals.get(1) + " ";
		str += (String) sessionTotals.get(2) + " ";
		str += (String) sessionTotals.get(3);
		return "Session totals: " + str;
	}

}
