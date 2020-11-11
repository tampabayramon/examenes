package org.kp4tr.exams.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.font.LineBreakMeasurer;
import java.awt.font.TextAttribute;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

public class Text2Image {

	private int lines = 0;

	public void generateTextImage(String text, String ans1, String ans2,
			String ans3, String ans4, String fileOutName, String fileInName) {

		// listFonts();

		try {
			List layouts = null;
			Font font = new Font("Times New Roman", Font.BOLD, 16);

			BufferedImage bi = ImageIO.read(new File(fileInName));
			// Graphics g = bi.createGraphics();
			Graphics2D g2d = (Graphics2D) bi.createGraphics();

			// g2d.setColor(Color.white);
			// g2d.fillRect(0, 0, bi.getWidth(), bi.getHeight());
			g2d.setColor(Color.black);
			g2d.setFont(font);
			int point_y = 5;

			if (layouts == null)
				layouts = getLayouts(g2d, font, text, bi.getWidth());

			g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

			FontMetrics metrics = g2d.getFontMetrics();
			Iterator it = layouts.iterator();
			Point pen = new Point(0, point_y);

			writeImage(g2d, it, pen, font);

			font = new Font("Times New Roman", Font.PLAIN, 16);

			point_y = point_y + 85;

			layouts = getLayouts(g2d, font, ans1, bi.getWidth());
			it = layouts.iterator();
			pen = new Point(0, point_y);
			writeImage(g2d, it, pen, font);
			if (lines == 3) {
				point_y = point_y + 45;
			} else {
				point_y = point_y + 30;
			}

			point_y = point_y(point_y);
			layouts = getLayouts(g2d, font, ans2, bi.getWidth());
			it = layouts.iterator();

			pen = new Point(0, point_y);
			writeImage(g2d, it, pen, font);
			if (lines == 3) {
				point_y = point_y + 45;
			} else {
				point_y = point_y + 30;
			}


			point_y = point_y(point_y);
			layouts = getLayouts(g2d, font, ans3, bi.getWidth());
			it = layouts.iterator();
			pen = new Point(0, point_y);
			writeImage(g2d, it, pen, font);
			if (lines == 3) {
				point_y = point_y + 45;
			} else {
				point_y = point_y + 30;
			}


			point_y = point_y(point_y);
			layouts = getLayouts(g2d, font, ans4, bi.getWidth());
			it = layouts.iterator();
			pen = new Point(0, point_y);
			writeImage(g2d, it, pen, font);

			ImageIO.write(bi, "jpeg", new File(fileOutName));

			bi.flush();

		} catch (Exception ex) {
			System.out.println("Exception: " + ex);
		}

	}

	private int point_y(int point_y) {
		if (lines == 1) {
			// point_y = point_y +15;
		}
		if (lines == 2) {
			point_y = point_y + 15;
		}
		if (lines == 3) {
			point_y = point_y + 15;
		}

		if (lines == 4) {
			point_y = point_y + 15;
		}

		return point_y;
	}

	private List getLayouts(Graphics g, Font font, String text, int width) {
		this.lines = 0;
		List layouts = new ArrayList();

		Graphics2D g2d = (Graphics2D) g;
		FontRenderContext frc = g2d.getFontRenderContext();

		AttributedString attrStr = new AttributedString(text);
		attrStr.addAttribute(TextAttribute.FONT, font, 0, text.length());
		LineBreakMeasurer measurer = new LineBreakMeasurer(attrStr
				.getIterator(), frc);
		float wrappingWidth;

		wrappingWidth = width;

		while (measurer.getPosition() < text.length()) {
			TextLayout layout = measurer.nextLayout(wrappingWidth);
			layouts.add(layout);
			lines++;
		}

		return layouts;
	}

	private void writeImage(Graphics2D g2d, Iterator it, Point pen, Font font) {
		while (it.hasNext()) {
			TextLayout layout = (TextLayout) it.next();
			pen.y += (layout.getAscent());
			g2d.setFont(font);
			layout.draw(g2d, pen.x, pen.y);
			pen.y += layout.getDescent();
		}

	}

	public void listFonts() {
		String[] ge = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();
		for (int i = 0; i < ge.length; i++) {
			System.out.println(ge[i]);
		}
	}

	public static void main(String[] args) {
		String text = "Many people believe that Vincent van Gogh painted his best works "
				+ "during the two-year period he spent in Provence. Here is where he "
				+ "painted The Starry Night--which some consider to be his greatest "
				+ "work of all. However, as his artistic brilliance reached new "
				+ "heights in Provence, his physical and mental health plummeted. ";

		Text2Image t2i = new Text2Image();
		// t2i.generateTextImage(text, "c:\\test_out.jpg", "c:\\test.jpg");
	}
}
