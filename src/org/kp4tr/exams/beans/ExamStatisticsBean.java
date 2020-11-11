package org.kp4tr.exams.beans;

public class ExamStatisticsBean {

	private int technicianTaken;

	private int technicianPassed;

	private int technicianFailed;

	String technician_lasttakendate = "";

	String technician_lasttakentime = "";

	private int generalTaken;

	private int generalPassed;

	private int generalFailed;

	String general_lasttakendate = "";

	String general_lasttakentime = "";

	private int extraTaken;

	private int extraPassed;

	private int extraFailed;

	String extra_lasttakendate = "";

	String extra_lasttakentime = "";

	int totalPassed = 0;

	int totalFailed = 0;

	int totals = 0;

	public String getExtra_lasttakendate() {
		return extra_lasttakendate;
	}

	public void setExtra_lasttakendate(String extra_lasttakendate) {
		this.extra_lasttakendate = extra_lasttakendate;
	}

	public String getExtra_lasttakentime() {
		return extra_lasttakentime;
	}

	public void setExtra_lasttakentime(String extra_lasttakentime) {
		this.extra_lasttakentime = extra_lasttakentime;
	}

	public int getExtraFailed() {
		return extraFailed;
	}

	public void setExtraFailed(int extraFailed) {
		this.extraFailed = extraFailed;
	}

	public int getExtraPassed() {
		return extraPassed;
	}

	public void setExtraPassed(int extraPassed) {
		this.extraPassed = extraPassed;
	}

	public int getExtraTaken() {
		return extraTaken;
	}

	public void setExtraTaken(int extraTaken) {
		this.extraTaken = extraTaken;
	}

	public String getGeneral_lasttakendate() {
		return general_lasttakendate;
	}

	public void setGeneral_lasttakendate(String general_lasttakendate) {
		this.general_lasttakendate = general_lasttakendate;
	}

	public String getGeneral_lasttakentime() {
		return general_lasttakentime;
	}

	public void setGeneral_lasttakentime(String general_lasttakentime) {
		this.general_lasttakentime = general_lasttakentime;
	}

	public int getGeneralFailed() {
		return generalFailed;
	}

	public void setGeneralFailed(int generalFailed) {
		this.generalFailed = generalFailed;
	}

	public int getGeneralPassed() {
		return generalPassed;
	}

	public void setGeneralPassed(int generalPassed) {
		this.generalPassed = generalPassed;
	}

	public int getGeneralTaken() {
		return generalTaken;
	}

	public void setGeneralTaken(int generalTaken) {
		this.generalTaken = generalTaken;
	}

	public String getTechnician_lasttakendate() {
		return technician_lasttakendate;
	}

	public void setTechnician_lasttakendate(String technician_lasttakendate) {
		this.technician_lasttakendate = technician_lasttakendate;
	}

	public String getTechnician_lasttakentime() {
		return technician_lasttakentime;
	}

	public void setTechnician_lasttakentime(String technician_lasttakentime) {
		this.technician_lasttakentime = technician_lasttakentime;
	}

	public int getTechnicianFailed() {
		return technicianFailed;
	}

	public void setTechnicianFailed(int technicianFailed) {
		this.technicianFailed = technicianFailed;
	}

	public int getTechnicianPassed() {
		return technicianPassed;
	}

	public void setTechnicianPassed(int technicianPassed) {
		this.technicianPassed = technicianPassed;
	}

	public int getTechnicianTaken() {
		return technicianTaken;
	}

	public void setTechnicianTaken(int technicianTaken) {
		this.technicianTaken = technicianTaken;
	}

	public int getTotalFailed() {
		return totalFailed;
	}

	public void setTotalFailed(int totalFailed) {
		this.totalFailed = totalFailed;
	}

	public int getTotalPassed() {
		return totalPassed;
	}

	public void setTotalPassed(int totalPassed) {
		this.totalPassed = totalPassed;
	}

	public int getTotals() {
		return totals;
	}

	public void setTotals(int totals) {
		this.totals = totals;
	}

}
