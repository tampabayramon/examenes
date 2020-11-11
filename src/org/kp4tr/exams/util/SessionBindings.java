package org.kp4tr.exams.util;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SessionBindings extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/plain");
		PrintWriter out = res.getWriter();

		// Get the current session object, create one if necessary
		HttpSession session = req.getSession(true);

		// Add a CustomBindingListener
		session.setAttribute("bindings.listener", new CustomBindingListener(
				getServletContext()));

		out.println("This page intentionally left blank");
	}
}

class CustomBindingListener implements HttpSessionBindingListener {

	// Save a ServletContext to be used for its log() method
	ServletContext context;

	public CustomBindingListener(ServletContext context) {
		this.context = context;
	}

	public void valueBound(HttpSessionBindingEvent event) {
		context.log("BOUND as " + event.getName() + " to "
				+ event.getSession().getId());
	}

	public void valueUnbound(HttpSessionBindingEvent event) {
		context.log("UNBOUND as " + event.getName() + " from "
				+ event.getSession().getId());
	}
}