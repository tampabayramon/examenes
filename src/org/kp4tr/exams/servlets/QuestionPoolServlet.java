/*
 * QuestionPoolServlet.java
 *
 * Created on August 6, 2003, 10:35 PM
 */

package org.kp4tr.exams.servlets;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.kp4tr.exams.dao.HSQLDBShutdownDAO;
import org.kp4tr.exams.daohelpers.DBConnectionBean;
import org.kp4tr.exams.daohelpers.DbConnectionFactory;
import org.kp4tr.exams.util.SessionKeys;

/**
 * 
 * @author Ramon Gonzalez
 */

public class QuestionPoolServlet extends ActionServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558724379036985125L;

	// Define a static logger variable so that it references the
	// Logger instance named "MyApp".
	static Logger log = Logger.getLogger(QuestionPoolServlet.class);

	private String debug;

	public void init() throws ServletException {
		super.init();
		String prefix = getServletContext().getRealPath("/");
		System.out.println("Webapp servlet path at: " + prefix);

		debug = getServletConfig().getInitParameter("debug");

		try {
			// Set up a simple configuration that logs on the console.
			BasicConfigurator.configure();
			getServletContext().setAttribute(SessionKeys.LOG, log);
			log.info("Entering application " + this.getClass().toString());
		} catch (Exception e) {
			System.out.println("Could not get log4j to work: "
					+ this.getClass().toString() + ":" + e);
		}

		try {
			initJDBCParams();

		} catch (Exception e) {
			log
					.fatal("Fatal Exception caught while initApplicationScope() in init():"
							+ e);
		}
	}

	private void initJDBCParams() {

		try {
			DBConnectionBean jdbc = new DBConnectionBean();
			jdbc.setJdbcDriver(getServletConfig()
					.getInitParameter("jdbcDriver"));
			jdbc.setJdbcConnection(getServletConfig().getInitParameter(
					"jdbcConnection"));
			jdbc.setJdbcUserId(getServletConfig()
					.getInitParameter("jdbcUserId"));
			jdbc.setJdbcPassword(getServletConfig().getInitParameter(
					"jdbcPassword"));
			jdbc.setJdbcDBPoolMinConn(Integer.parseInt(getServletConfig()
					.getInitParameter("jdbcDBPoolMinConn")));
			jdbc.setJdbcDBPoolMaxConn(Integer.parseInt(getServletConfig()
					.getInitParameter("jdbcDBPoolMaxConn")));
			jdbc.setJdbcDBPoolMaxConnTime(Double.parseDouble(getServletConfig()
					.getInitParameter("jdbcDBPoolMaxConnTime")));
			jdbc.setJdbcDBPoolMaxCheckoutSeconds(Integer
					.parseInt(getServletConfig().getInitParameter(
							"jdbcDBPoolMaxCheckoutSeconds")));
			jdbc.setJdbcDBPoolLogAppend(getServletConfig().getInitParameter(
					"jdbcDBPoolLogAppend"));
			jdbc.setJdbcDBPoolDebugLevel(Integer.parseInt(getServletConfig()
					.getInitParameter("jdbcDBPoolDebugLevel")));
			jdbc.setJdbcDBLogFile(getServletConfig().getInitParameter(
					"jdbcDBLogFile"));
			DbConnectionFactory.initDBConnection(jdbc);
		} catch (Exception e) {
			log.error("Exception caught in initApplicationScope():" + e);
		}

	}

	private void debug(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			System.out.println();
			System.out.println("Servlet init parameters:");
			Enumeration e = this.getInitParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = this.getInitParameter(key);
				System.out.println("   " + key + " = " + value);
			}
			System.out.println();

			System.out.println("Context init parameters:");
			ServletContext context = this.getServletContext();
			e = context.getInitParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				Object value = context.getInitParameter(key);
				System.out.println("   " + key + " = " + value);
			}
			System.out.println();

			System.out.println("Context attributes:");
			e = context.getAttributeNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				Object value = context.getAttribute(key);
				System.out.println("   " + key + " = " + value);
			}
			System.out.println();

			System.out.println("Request attributes:");
			e = request.getAttributeNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				Object value = request.getAttribute(key);
				System.out.println("   " + key + " = " + value);
			}
			System.out.println();

			System.out.println("Servlet Name: " + this.getServletName());
			System.out.println("Protocol: " + request.getProtocol());
			System.out.println("Scheme: " + request.getScheme());
			System.out.println("Server Name: " + request.getServerName());
			System.out.println("Server Port: " + request.getServerPort());
			System.out.println("Server Info: " + context.getServerInfo());
			System.out.println("Remote Addr: " + request.getRemoteAddr());
			System.out.println("Remote Host: " + request.getRemoteHost());
			System.out.println("Character Encoding: "
					+ request.getCharacterEncoding());
			System.out.println("Content Length: " + request.getContentLength());
			System.out.println("Content Type: " + request.getContentType());
			System.out.println("Locale: " + request.getLocale());
			System.out.println("Default Response Buffer: "
					+ response.getBufferSize());
			System.out.println();

			System.out.println("Parameter names in this request:");
			e = request.getParameterNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String[] values = request.getParameterValues(key);
				System.out.print("   " + key + " = ");
				for (int i = 0; i < values.length; i++) {
					System.out.print(values[i] + " ");
				}
				System.out.println();
			}
			System.out.println();

			System.out.println("Headers in this request:");
			e = request.getHeaderNames();
			while (e.hasMoreElements()) {
				String key = (String) e.nextElement();
				String value = request.getHeader(key);
				System.out.println("   " + key + ": " + value);
			}
			System.out.println();

			System.out.println("Cookies in this request:");
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					Cookie cookie = cookies[i];
					System.out.println("   " + cookie.getName() + " = "
							+ cookie.getValue());
				}
			}
			System.out.println();

			System.out.println("Request Is Secure: " + request.isSecure());
			System.out.println("Auth Type: " + request.getAuthType());
			System.out.println("HTTP Method: " + request.getMethod());
			System.out.println("Remote User: " + request.getRemoteUser());
			System.out.println("Request URI: " + request.getRequestURI());
			System.out.println("Context Path: " + request.getContextPath());
			System.out.println("Servlet Path: " + request.getServletPath());
			System.out.println("Path Info: " + request.getPathInfo());
			System.out.println("Path Trans: " + request.getPathTranslated());
			System.out.println("Query String: " + request.getQueryString());
			System.out.println();

			HttpSession session = request.getSession();
			System.out.println("Requested Session Id: "
					+ request.getRequestedSessionId());
			System.out.println("Current Session Id: " + session.getId());
			System.out.println("Session Created Time: "
					+ session.getCreationTime());
			System.out.println("Session Last Accessed Time: "
					+ session.getLastAccessedTime());
			System.out.println("Session Max Inactive Interval Seconds: "
					+ session.getMaxInactiveInterval());
			System.out.println();

			System.out.println("Session values: ");
			Enumeration names = session.getAttributeNames();
			while (names.hasMoreElements()) {
				String name = (String) names.nextElement();
				System.out.println("   " + name + " = "
						+ session.getAttribute(name));
			}
			System.out.println("###");
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	/**
	 * Override the struts doGet so we can do any special processing that needs
	 * done before the generic struts processing.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (debug.equals("true")) {
			log.debug("doGet request from " + request.getRemoteAddr());
			debug(request, response);
		}
		super.process(request, response);
	}

	/**
	 * Override the struts doPost so we can do any special processing that needs
	 * done before the generic struts processing.
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		if (debug.equals("true")) {
			log.debug("doPost request from " + request.getRemoteAddr());
			debug(request, response);
		}
		super.process(request, response);

	}

	public void destroy() {
		// TODO Auto-generated method stub
		// SHUTDOWN COMPACT

		HSQLDBShutdownDAO hsqldb = new HSQLDBShutdownDAO();
		hsqldb.execute();
		super.destroy();
	}

}