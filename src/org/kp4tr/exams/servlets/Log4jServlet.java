/*
 * Log4jServlet.java
 *
 * Created on August 6, 2003, 10:35 PM
 */

package org.kp4tr.exams.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

/**
 * 
 * @author Ramon Gonzalez
 */

public class Log4jServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2221277598328001607L;

	public void init() {
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");
		// if the log4j-init-file is not set, then no point in trying
		if (file != null) {
			PropertyConfigurator.configure(prefix+file);
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) {
	}
}
