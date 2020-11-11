/*
 * QuestionPoolParser.java
 * 
 * Created on December 25, 2002, 6:21 PM
 */

package org.kp4tr.exams.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.kp4tr.exams.beans.QuestionBean;

public class QuestionPoolParser {

	public QuestionPoolParser() {
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	private static void parseFile() {

		/*
		 * INSERT INTO FCCQUESTIONS_ES
		 * VALUES('G1','D',1,'B','','&iquest;Qu&eacute; elementos para
		 * ex&aacute;menes est&aacute; usted autorizado a preparar cuando posee
		 * una licencia de operador de la clase &quot;General&quot;?','A.
		 * Ninguno','B. Elementos 1 y 2 solamente','C. Elemento 1 solamente','D.
		 * Elementos 1, 2 y 3','')
		 */
		String inputFile = "C:\\temp\\examenes2008\\2008ExtraEng.txt";
		String outputFile = "C:\\temp\\examenes2008\\2008ExtraEng.sql";
		String line = null;
		String outLine = null;

		String question_subelement = null;
		String question_subelementgroup = null;
		String question_number = null;
		String question_answer = null;
		String question_reference = null;

		try {
			BufferedReader listFile = new BufferedReader(new FileReader(
					inputFile));
			BufferedWriter outListFile = new BufferedWriter(new FileWriter(
					outputFile));
			while ((line = listFile.readLine()) != null) {

				if (line.startsWith("E")) {
					question_subelement = line.substring(0, 2);
					question_subelementgroup = line.substring(2, 3);
					question_number = line.substring(3, 5);
					question_answer = line.substring(7, 8);
					outLine = ("INSERT INTO FCCQUESTIONS_ES VALUES ('"
							+ question_subelement + "','"
							+ question_subelementgroup + "'," + question_number
							+ ",'" + question_answer + "',''" + ",");
					
				} else {
					outLine = "'" + line + "',";
				}

				if (line.equals("~~")) {
					outLine = "'" + "');";
				}

				if (line.length() < 2) {
					// nada
				} else {

					outListFile.write(outLine
							+ System.getProperty("line.separator"));
					System.out.println(outLine);
				}

			}
			outListFile.flush();
			outListFile.close();
			listFile.close();

		} catch (Exception e) {
			System.out.println("Error reading " + inputFile + ". " + e);
		}
	}

	private static void splitFile() {
		String inputFile = "C:\\temp\\examenes2007\\examsDB.script";
		String outputT = "C:\\temp\\examenes2007\\Technician.sql";
		String outputG = "C:\\temp\\examenes2007\\General.sql";
		String outputE = "C:\\temp\\examenes2007\\Extra.sql";
		String line = null;
		String outLine = null;

		try {

			BufferedReader inFile = new BufferedReader(
					new FileReader(inputFile));
			BufferedWriter outFileT = new BufferedWriter(
					new FileWriter(outputT));
			BufferedWriter outFileG = new BufferedWriter(
					new FileWriter(outputG));
			BufferedWriter outFileE = new BufferedWriter(
					new FileWriter(outputE));

			while ((line = inFile.readLine()) != null) {

				try {
					if (line.substring(36, 37).equals("T")) {
						outFileT.write(line
								+ System.getProperty("line.separator"));
					}
					if (line.substring(36, 37).equals("G")) {
						outFileG.write(line
								+ System.getProperty("line.separator"));
					}
					if (line.substring(36, 37).equals("E")) {
						outFileE.write(line
								+ System.getProperty("line.separator"));
					}

					System.out.println(line.substring(36, 37));
				} catch (StringIndexOutOfBoundsException se) {

				}
			}

			outFileT.flush();
			outFileT.close();
			outFileG.flush();
			outFileG.close();
			outFileE.flush();
			outFileE.close();
			inFile.close();

		} catch (Exception e) {
			System.out.println("Error reading " + inputFile + ". " + e);
		}
	}

	private static void removeCRFile() {

		String inputFile = "C:\\temp\\examenes2008\\2008ExtraEng.sql";
		String outputFile = "C:\\temp\\examenes2008\\2008ExtraEng_out.sql";
		String line = null;
		String outLine = null;

		String question_subelement = null;
		String question_subelementgroup = null;
		String question_number = null;
		String question_answer = null;
		String question_reference = null;

		try {
			BufferedReader listFile = new BufferedReader(new FileReader(
					inputFile));
			BufferedWriter outListFile = new BufferedWriter(new FileWriter(
					outputFile));
			int counter = 0;
			while ((line = listFile.readLine()) != null) {
				if (counter == 7) {
					counter = 0;
					outListFile.write(outLine
							+ System.getProperty("line.separator"));
					outLine = "";
				}

				if (counter == 0) {
					System.out.println(line);
				}
				counter++;
				outLine += line;
			}
			outListFile.flush();
			outListFile.close();
			listFile.close();

		} catch (Exception e) {
			System.out.println("Error reading " + inputFile + ". " + e);
		}
	}

	public static void queryDB() {

		Statement SQL_Statement = null;
		ResultSet rsquery1 = null;
		Connection connection = null;
		String fccQuestionsTable = "FCCQUESTIONS_ES";
		String subElement = "T0";
		String subElementGroup = "C";
		String poolQuestionNumber = "9";
		String outputFile = "C:\\temp\\el3s.04";
		String outputFileAns = "C:\\temp\\el3s-ans.04";
		//QUESTION_ANSWER

		String query = "SELECT * FROM " + fccQuestionsTable + "  WHERE "
				+ fccQuestionsTable + ".question_subelement = '" + subElement
				+ "' and " + fccQuestionsTable
				+ ".question_subelementgroup = '" + subElementGroup + "' and "
				+ fccQuestionsTable + ".question_number = '"
				+ poolQuestionNumber + "';";

		String queryAll = "SELECT * FROM " + fccQuestionsTable + 
		" ORDER BY QUESTION_SUBELEMENT, QUESTION_SUBELEMENTGROUP, QUESTION_NUMBER" + ";";

		String queryG = "SELECT * FROM " + fccQuestionsTable + "  WHERE "
				+ fccQuestionsTable + ".question_subelement = '" + subElement
				+ "' and " + fccQuestionsTable
				+ ".question_subelementgroup = '" + subElementGroup + "';";

		String subElementNumber = "";

		try {
			
			BufferedWriter outListFile = new BufferedWriter(new FileWriter(
					outputFile));
			BufferedWriter outListFileAns = new BufferedWriter(new FileWriter(
					outputFileAns));
			
			Text2Image t2i = new Text2Image();

			// Step 1: Load the JDBC driver.
			Class.forName("org.hsqldb.jdbcDriver");

			// Step 2: Establish the connection to the database.
			String url = "jdbc:hsqldb:hsql://localhost:9001/exams";
			connection = DriverManager.getConnection(url, "sa", "");
			SQL_Statement = connection.createStatement();
			System.out.println(queryAll);
			rsquery1 = SQL_Statement.executeQuery(queryAll);
			int counter = 0;

			while (rsquery1.next()) {
				QuestionBean qb = new QuestionBean();
				qb.setQuestion_subelement(rsquery1
						.getString("question_subelement"));
				qb.setQuestion_subelementgroup(rsquery1
						.getString("question_subelementgroup"));
				qb.setQuestion_number(Integer.toString(rsquery1
						.getInt("question_number")));
				qb.setQuestion_answer(rsquery1.getString("question_answer"));
				qb.setQuestion_reference(rsquery1
						.getString("question_reference"));
				qb.setQuestion(rsquery1.getString("question"));
				qb.setMultiplechoice_A(rsquery1.getString("multiplechoice_A"));
				qb.setMultiplechoice_B(rsquery1.getString("multiplechoice_B"));
				qb.setMultiplechoice_C(rsquery1.getString("multiplechoice_C"));
				qb.setMultiplechoice_D(rsquery1.getString("multiplechoice_D"));
				qb.setFigureImage(rsquery1.getString("figureImage"));
				subElementNumber = qb.getQuestion_subelement()
						+ qb.getQuestion_subelementgroup()
						+ qb.getQuestion_number();

				qb.setQuestion_subelementNumber(subElementNumber.trim());

				String html = filterHTML(qb.getQuestion());
				String ans1 = filterHTML(qb.getMultiplechoice_A());
				String ans2 = filterHTML(qb.getMultiplechoice_B());
				String ans3 = filterHTML(qb.getMultiplechoice_C());
				String ans4 = filterHTML(qb.getMultiplechoice_D());
				String ans = filterHTML(qb.getQuestion_answer());

				//System.out.println(html);
				String imagePath = "C:\\Documents and Settings\\user1\\workspace\\examenes2007\\WebContent\\images\\es\\";
/*				t2i.generateTextImage(html, ans1, ans2, ans3, ans4, imagePath
						+ subElementNumber + ".jpg", imagePath
						+ "template_es.jpg");
*/
				
				outListFile.write(
						qb.getQuestion_subelementNumber()
						+ " "
						+ filterHTML(qb.getQuestion())
						+ System.getProperty("line.separator"));
				
				outListFile.write("  " 
						+ ans1
						+ System.getProperty("line.separator"));
				outListFile.write("  " 
						+ ans2
						+ System.getProperty("line.separator"));
				outListFile.write("  " 
						+ ans3
						+ System.getProperty("line.separator"));
				outListFile.write("  " 
						+ ans4
						+ System.getProperty("line.separator"));
				
				outListFile.write(System.getProperty("line.separator"));
				
				//answer
				outListFileAns.write(
						qb.getQuestion_subelementNumber()
						+ " "
						+ qb.getQuestion_answer()
						+ " " + System.getProperty("line.separator"));
				
				
			}

			connection.close();
			SQL_Statement.close();
			outListFile.flush();
			outListFile.close();
			outListFileAns.flush();
			outListFileAns.close();
			
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	private static String filterHTML(String html) {

		html = html.replaceAll("&iquest;", "¿");
		html = html.replaceAll("&aacute;", "á");
		html = html.replaceAll("&eacute;", "é");
		html = html.replaceAll("&iacute;", "í");
		html = html.replaceAll("&oacute;", "ó");
		html = html.replaceAll("&uacute;", "ú");
		html = html.replaceAll("&ntilde;", "ñ");
		html = html.replaceAll("&quot;", "\'");

		return html;
	}

	public static void main(String[] args) {
		parseFile();
		// splitFile();
		removeCRFile();
		//queryDB();
	}

}
