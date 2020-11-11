/*
 SnoopServlet
 Written by Gabriel Wong <gabrielw@ezwebtools.com>
 http://www.ezwebtools.com
 */
package org.kp4tr.exams.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Tests the Servlet Client and Server Environment.
 */

public class SnoopServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5596374897956112163L;

	private boolean debug = true;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			String debug = config.getInitParameter("debug");
			if ((debug != null) && debug.equals("false")) {
				this.debug = false;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	public void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.print("<html><title>Snoop</title><form method=post action="
					+ request.getRequestURI() + ">");
			out
					.print("<p><center><b><FONT SIZE=+2>SnoopServlet</FONT></b></center>");

			// Cookies
			out.print("<p><b>Cookies</b><br>");
			out.print("<table border=1 >");
			out.print("<tr><th>Name</th><th>Value</th></tr>");
			if (debug) {
				System.out.println("**Cookies**");
			}
			Cookie[] cookies = request.getCookies();
			Cookie cookie;
			if (cookies != null) {
				for (int ct = 0; ct < cookies.length; ct++) {
					cookie = cookies[ct];
					out.print("<tr><td>");
					out.print(cookie.getName());
					out.print("</td><td>");
					out.print(cookie.getValue());
					out.print("</td><tr>");
					if (debug) {
						System.out.println(cookie.getName() + ": "
								+ cookie.getValue());
					}
				}
			}
			out.print("</table>");

			// Headers
			out.print("<p><b>Request Headers</b><br>");
			out.print("<table border=1 >");
			out.print("<tr><th>Name</th><th>Value</th></tr>");
			if (debug) {
				System.out.println("**Request Headers**");
			}
			Enumeration headers = request.getHeaderNames();
			String headerName;
			String headerValue;
			while (headers.hasMoreElements()) {
				headerName = (String) headers.nextElement();
				headerValue = request.getHeader(headerName);
				out.print("<tr><td>");
				out.print(headerName);
				out.print("</td><td>");
				out.print(headerValue);
				out.print("</td><tr>");
				if (debug) {
					System.out.println(headerName + ": " + headerValue);
				}
			}
			out.print("</table>");

			// Method (GET/POST)
			out.print("<p><b>Method</b><br>");
			if (debug) {
				System.out.println("**Method**");
			}
			out.print("Method: " + request.getMethod());
			if (debug) {
				System.out.println("Method: " + request.getMethod());
			}
			out.print("<br><input type=submit name=Submit value=Submit><br>");

			// Parameters
			out.print("<p><b>Request Parameters</b><br>");
			out.print("<table border=1 >");
			out.print("<tr><th>Name</th><th>Value</th></tr>");
			if (debug) {
				System.out.println("**Request Parameters**");
			}
			Enumeration parameters = request.getParameterNames();
			String parameterName;
			String parameterValue;
			while (parameters.hasMoreElements()) {
				parameterName = (String) parameters.nextElement();
				parameterValue = request.getParameter(parameterName);
				out.print("<tr><td>");
				out.print(parameterName);
				out.print("</td><td>");
				out.print(parameterValue);
				out.print("</td><tr>");
				if (debug) {
					System.out.println(parameterName + ": " + parameterValue);
				}
			}
			out.print("</table>");

			// PathInfo
			out.print("<p><b>Path Info</b><br>");
			if (debug) {
				System.out.println("**Path Info**");
			}
			out.print("Path Info: " + request.getPathInfo());
			if (debug) {
				System.out.println("Path Info: " + request.getPathInfo());
			}

			// Path Translated
			out.print("<p><b>Path Translated</b><br>");
			if (debug) {
				System.out.println("**Path Translated**");
			}
			out.print("Path Translated: " + request.getPathTranslated());
			if (debug) {
				System.out.println("Path Translated: "
						+ request.getPathTranslated());
			}

			// Query String
			out.print("<p><b>Query String</b><br>");
			if (debug) {
				System.out.println("**Query String**");
			}
			out.print("Query String: " + request.getQueryString());
			if (debug) {
				System.out.println("Query String: " + request.getQueryString());
			}

			// Request URI
			out.print("<p><b>Request URI</b><br>");
			if (debug) {
				System.out.println("**Request URI**");
			}
			out.print("Request URI: " + request.getRequestURI());
			if (debug) {
				System.out.println("Request URI: " + request.getRequestURI());
			}

			// Servlet Path
			out.print("<p><b>Servlet Path</b><br>");
			if (debug) {
				System.out.println("**Servlet Path**");
			}
			out.print("Servlet Path: " + request.getServletPath());
			if (debug) {
				System.out.println("Servlet Path: " + request.getServletPath());
			}

			out.print("</form></html>");
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

}
