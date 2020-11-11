package org.kp4tr.exams.util;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import uk.ltd.getahead.dwr.WebContext;
import uk.ltd.getahead.dwr.WebContextFactory;

public class ValidateSession {

	WebContext ctx = WebContextFactory.get();

	HttpServletRequest request = ctx.getHttpServletRequest();

	HttpSession session = request.getSession();

	String sessionUpdateMessage = "Session Updated - Server Time: ";

	String sessionInvalidMessage = "Invalid User or session expired.";

	public String creationTime() {
		String creationTime = new Date(session.getCreationTime()).toString();
		return creationTime;
	}

	public String getSessionMaxInactiveInterval() {
		return Integer.toString(session.getMaxInactiveInterval());
	}

	public String setSessionMaxInactiveInterval(int newMaxInactiveInterval) {
		session.setMaxInactiveInterval(newMaxInactiveInterval);
		return "New session maxInactiveInterval:"
				+ session.getMaxInactiveInterval();
	}

	public String getSessionValidateUser() {

		String returnMessage = "";

		try {
			String examType = (String) session.getAttribute("examType");

			if (examType != null) {
				returnMessage = sessionUpdateMessage + new Date().toString()
						+ " - " + examType;
			} else {
				returnMessage = sessionInvalidMessage;
			}
		} catch (Exception e) {
			returnMessage = sessionInvalidMessage;
		}
		return returnMessage;
	}

}
