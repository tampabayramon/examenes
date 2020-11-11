package org.kp4tr.exams.formbeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.kp4tr.exams.beans.ExamStatisticsBean;

public class SelectExamForm extends ActionForm {

	/**
	 *  
	 */
	private static final long serialVersionUID = 7778830133550315733L;

	public SelectExamForm() {
	}

	private ExamStatisticsBean examStatistics;

	private String activeSessions;

	private String maxActiveSessions;

	private String totalSessions;

	private String lastSessionDateTime;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		ActionErrors errs = new ActionErrors();
		// if (getStartExamName() == null || getStartExamName().length() < 1) {
		// errs.add("error.examtypenotselected", new
		// ActionError("org.kp4tr.ApplicationResources"));
		// }
		return errs;
	}

	public ExamStatisticsBean getExamStatistics() {
		return examStatistics;
	}

	public void setExamStatistics(ExamStatisticsBean examStatistics) {
		this.examStatistics = examStatistics;
	}

	/**
	 * @return Returns the activeSessions.
	 */
	public String getActiveSessions() {
		return activeSessions;
	}
	/**
	 * @param activeSessions The activeSessions to set.
	 */
	public void setActiveSessions(String activeSessions) {
		this.activeSessions = activeSessions;
	}
	/**
	 * @return Returns the lastSessionDateTime.
	 */
	public String getLastSessionDateTime() {
		return lastSessionDateTime;
	}
	/**
	 * @param lastSessionDateTime The lastSessionDateTime to set.
	 */
	public void setLastSessionDateTime(String lastSessionDateTime) {
		this.lastSessionDateTime = lastSessionDateTime;
	}
	/**
	 * @return Returns the maxActiveSessions.
	 */
	public String getMaxActiveSessions() {
		return maxActiveSessions;
	}
	/**
	 * @param maxActiveSessions The maxActiveSessions to set.
	 */
	public void setMaxActiveSessions(String maxActiveSessions) {
		this.maxActiveSessions = maxActiveSessions;
	}
	/**
	 * @return Returns the totalSessions.
	 */
	public String getTotalSessions() {
		return totalSessions;
	}
	/**
	 * @param totalSessions The totalSessions to set.
	 */
	public void setTotalSessions(String totalSessions) {
		this.totalSessions = totalSessions;
	}
}