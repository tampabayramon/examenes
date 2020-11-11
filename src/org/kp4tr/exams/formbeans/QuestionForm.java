package org.kp4tr.exams.formbeans;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.kp4tr.exams.beans.ExamQuestionsSubelementDescBean;
import org.kp4tr.exams.beans.QuestionBean;

public class QuestionForm extends ActionForm {

	private static final long serialVersionUID = -5759468459659387832L;

	private String startExamName;
	
	private String singleQuestionFlag;
	
	private String singleQuestionNo;

	private ArrayList examQuestions;

	private String func;

	private String examSize;

	private int correctAnswers;

	private int wrongAnswers;
	
	private String passOrFail;

	private ExamQuestionsSubelementDescBean subelementDesc;
	
	private String startDateAndTime;

	public QuestionForm() {
	}

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest request) {
		ActionErrors errs = new ActionErrors();
		return errs;
	}

	public ArrayList getExamQuestions() {
		return examQuestions;
	}

	public void setExamQuestions(ArrayList examQuestions) {
		this.examQuestions = examQuestions;
	}

	/**
	 * @return Returns the examSize.
	 */
	public String getExamSize() {
		return examSize;
	}

	public void setExamSize(String examSize) {
		this.examSize = examSize;
	}

	/*
	 * public String getExamType() { return examType; }
	 * 
	 * public void setExamType(String examType) { this.examType = examType; }
	 */

	public String getStartExamName() {
		return startExamName;
	}

	public void setStartExamName(String startExamName) {
		this.startExamName = startExamName;
	}

	public ExamQuestionsSubelementDescBean getSubelementDesc() {
		return subelementDesc;
	}

	public void setSubelementDesc(ExamQuestionsSubelementDescBean subelementDesc) {
		this.subelementDesc = subelementDesc;
	}

	public void initUserAnswer(int examSize) {

		for (int idx = 0; idx < examSize; idx++) {
			QuestionBean qb = (QuestionBean) this.examQuestions.get(idx);
			qb.setUserAnswer("");
			this.examQuestions.set(idx, qb);
		}
	}

	public String getUserAnswer(int questionKey) {
		QuestionBean qb = (QuestionBean) this.examQuestions.get(questionKey);
		String returnAnswer = (String) qb.getUserAnswer();
		if (returnAnswer != null) {
			return returnAnswer;
		} else {
			return "";
		}
	}

	public void setUserAnswer(int questionKey, String newUserAnswer) {
		QuestionBean qb = (QuestionBean) this.examQuestions.get(questionKey);
		qb.setUserAnswer(newUserAnswer);
		this.examQuestions.set(questionKey, qb);
	}

	public String getUserAnswer0() {
		return getUserAnswer(0);
	}

	public String getUserAnswer1() {
		return getUserAnswer(1);
	}

	public String getUserAnswer2() {
		return getUserAnswer(2);
	}

	public String getUserAnswer3() {
		return getUserAnswer(3);
	}

	public String getUserAnswer4() {
		return getUserAnswer(4);
	}

	public String getUserAnswer5() {
		return getUserAnswer(5);
	}

	public String getUserAnswer6() {
		return getUserAnswer(6);
	}

	public String getUserAnswer7() {
		return getUserAnswer(7);
	}

	public String getUserAnswer8() {
		return getUserAnswer(8);
	}

	public String getUserAnswer9() {
		return getUserAnswer(9);
	}

	public String getUserAnswer10() {
		return getUserAnswer(10);
	}

	public String getUserAnswer11() {
		return getUserAnswer(11);
	}

	public String getUserAnswer12() {
		return getUserAnswer(12);
	}

	public String getUserAnswer13() {
		return getUserAnswer(13);
	}

	public String getUserAnswer14() {
		return getUserAnswer(14);
	}

	public String getUserAnswer15() {
		return getUserAnswer(15);
	}

	public String getUserAnswer16() {
		return getUserAnswer(16);
	}

	public String getUserAnswer17() {
		return getUserAnswer(17);
	}

	public String getUserAnswer18() {
		return getUserAnswer(18);
	}

	public String getUserAnswer19() {
		return getUserAnswer(19);
	}

	public String getUserAnswer20() {
		return getUserAnswer(20);
	}

	public String getUserAnswer21() {
		return getUserAnswer(21);
	}

	public String getUserAnswer22() {
		return getUserAnswer(22);
	}

	public String getUserAnswer23() {
		return getUserAnswer(23);
	}

	public String getUserAnswer24() {
		return getUserAnswer(24);
	}

	public String getUserAnswer25() {
		return getUserAnswer(25);
	}

	public String getUserAnswer26() {
		return getUserAnswer(26);
	}

	public String getUserAnswer27() {
		return getUserAnswer(27);
	}

	public String getUserAnswer28() {
		return getUserAnswer(28);
	}

	public String getUserAnswer29() {
		return getUserAnswer(29);
	}

	public String getUserAnswer30() {
		return getUserAnswer(30);
	}

	public String getUserAnswer31() {
		return getUserAnswer(31);
	}

	public String getUserAnswer32() {
		return getUserAnswer(32);
	}

	public String getUserAnswer33() {
		return getUserAnswer(33);
	}

	public String getUserAnswer34() {
		return getUserAnswer(34);
	}

	public String getUserAnswer35() {
		return getUserAnswer(35);
	}

	public String getUserAnswer36() {
		return getUserAnswer(36);
	}

	public String getUserAnswer37() {
		return getUserAnswer(37);
	}

	public String getUserAnswer38() {
		return getUserAnswer(38);
	}

	public String getUserAnswer39() {
		return getUserAnswer(39);
	}

	public String getUserAnswer40() {
		return getUserAnswer(40);
	}

	public String getUserAnswer41() {
		return getUserAnswer(41);
	}

	public String getUserAnswer42() {
		return getUserAnswer(42);
	}

	public String getUserAnswer43() {
		return getUserAnswer(43);
	}

	public String getUserAnswer44() {
		return getUserAnswer(44);
	}

	public String getUserAnswer45() {
		return getUserAnswer(45);
	}

	public String getUserAnswer46() {
		return getUserAnswer(46);
	}

	public String getUserAnswer47() {
		return getUserAnswer(47);
	}

	public String getUserAnswer48() {
		return getUserAnswer(48);
	}

	public String getUserAnswer49() {
		return getUserAnswer(49);
	}

	public void setUserAnswer0(String answer) {
		this.setUserAnswer(0, answer);
	}

	public void setUserAnswer1(String answer) {
		this.setUserAnswer(1, answer);
	}

	public void setUserAnswer2(String answer) {
		this.setUserAnswer(2, answer);
	}

	public void setUserAnswer3(String answer) {
		this.setUserAnswer(3, answer);
	}

	public void setUserAnswer4(String answer) {
		this.setUserAnswer(4, answer);
	}

	public void setUserAnswer5(String answer) {
		this.setUserAnswer(5, answer);
	}

	public void setUserAnswer6(String answer) {
		this.setUserAnswer(6, answer);
	}

	public void setUserAnswer7(String answer) {
		this.setUserAnswer(7, answer);
	}

	public void setUserAnswer8(String answer) {
		this.setUserAnswer(8, answer);
	}

	public void setUserAnswer9(String answer) {
		this.setUserAnswer(9, answer);
	}

	public void setUserAnswer10(String answer) {
		this.setUserAnswer(10, answer);
	}

	public void setUserAnswer11(String answer) {
		this.setUserAnswer(11, answer);
	}

	public void setUserAnswer12(String answer) {
		this.setUserAnswer(12, answer);
	}

	public void setUserAnswer13(String answer) {
		this.setUserAnswer(13, answer);
	}

	public void setUserAnswer14(String answer) {
		this.setUserAnswer(14, answer);
	}

	public void setUserAnswer15(String answer) {
		this.setUserAnswer(15, answer);
	}

	public void setUserAnswer16(String answer) {
		this.setUserAnswer(16, answer);
	}

	public void setUserAnswer17(String answer) {
		this.setUserAnswer(17, answer);
	}

	public void setUserAnswer18(String answer) {
		this.setUserAnswer(18, answer);
	}

	public void setUserAnswer19(String answer) {
		this.setUserAnswer(19, answer);
	}

	public void setUserAnswer20(String answer) {
		this.setUserAnswer(20, answer);
	}

	public void setUserAnswer21(String answer) {
		this.setUserAnswer(21, answer);
	}

	public void setUserAnswer22(String answer) {
		this.setUserAnswer(22, answer);
	}

	public void setUserAnswer23(String answer) {
		this.setUserAnswer(23, answer);
	}

	public void setUserAnswer24(String answer) {
		this.setUserAnswer(24, answer);
	}

	public void setUserAnswer25(String answer) {
		this.setUserAnswer(25, answer);
	}

	public void setUserAnswer26(String answer) {
		this.setUserAnswer(26, answer);
	}

	public void setUserAnswer27(String answer) {
		this.setUserAnswer(27, answer);
	}

	public void setUserAnswer28(String answer) {
		this.setUserAnswer(28, answer);
	}

	public void setUserAnswer29(String answer) {
		this.setUserAnswer(29, answer);
	}

	public void setUserAnswer30(String answer) {
		this.setUserAnswer(30, answer);
	}

	public void setUserAnswer31(String answer) {
		this.setUserAnswer(31, answer);
	}

	public void setUserAnswer32(String answer) {
		this.setUserAnswer(32, answer);
	}

	public void setUserAnswer33(String answer) {
		this.setUserAnswer(33, answer);
	}

	public void setUserAnswer34(String answer) {
		this.setUserAnswer(34, answer);
	}

	public void setUserAnswer35(String answer) {
		this.setUserAnswer(35, answer);
	}

	public void setUserAnswer36(String answer) {
		this.setUserAnswer(36, answer);
	}

	public void setUserAnswer37(String answer) {
		this.setUserAnswer(37, answer);
	}

	public void setUserAnswer38(String answer) {
		this.setUserAnswer(38, answer);
	}

	public void setUserAnswer39(String answer) {
		this.setUserAnswer(39, answer);
	}

	public void setUserAnswer40(String answer) {
		this.setUserAnswer(40, answer);
	}

	public void setUserAnswer41(String answer) {
		this.setUserAnswer(41, answer);
	}

	public void setUserAnswer42(String answer) {
		this.setUserAnswer(42, answer);
	}

	public void setUserAnswer43(String answer) {
		this.setUserAnswer(43, answer);
	}

	public void setUserAnswer44(String answer) {
		this.setUserAnswer(44, answer);
	}

	public void setUserAnswer45(String answer) {
		this.setUserAnswer(45, answer);
	}

	public void setUserAnswer46(String answer) {
		this.setUserAnswer(46, answer);
	}

	public void setUserAnswer47(String answer) {
		this.setUserAnswer(47, answer);
	}

	public void setUserAnswer48(String answer) {
		this.setUserAnswer(48, answer);
	}

	public void setUserAnswer49(String answer) {
		this.setUserAnswer(49, answer);
	}

	public int getCorrectAnswers() {
		return correctAnswers;
	}

	public void setCorrectAnswers(int correctAnswers) {
		this.correctAnswers = correctAnswers;
	}

	public int getWrongAnswers() {
		return wrongAnswers;
	}

	public void setWrongAnswers(int wrongAnswers) {
		this.wrongAnswers = wrongAnswers;
	}

	public String getPassOrFail() {
		return passOrFail;
	}

	public void setPassOrFail(String passOrFail) {
		this.passOrFail = passOrFail;
	}

	public String getSingleQuestionFlag() {
		return singleQuestionFlag;
	}

	public void setSingleQuestionFlag(String singleQuestionFlag) {
		this.singleQuestionFlag = singleQuestionFlag;
	}

	public String getSingleQuestionNo() {
		return singleQuestionNo;
	}

	public void setSingleQuestionNo(String singleQuestionNo) {
		this.singleQuestionNo = singleQuestionNo;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String function) {
		this.func = function;
	}

	/**
	 * @return Returns the startDateAndTime.
	 */
	public String getStartDateAndTime() {
		return startDateAndTime;
	}
	/**
	 * @param startDateAndTime The startDateAndTime to set.
	 */
	public void setStartDateAndTime(String startDateAndTime) {
		this.startDateAndTime = startDateAndTime;
	}
}