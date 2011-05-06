package cdu.computer.hxl.ui;

/**
 * @(#)FilletBorder.java
 *
 *
 * @author 
 * @version 1.00 2011/5/6
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.*;

public class RoundBorder extends AbstractBorder {

	public RoundBorder() {
	}

	public void paintBorder(Component c, Graphics g, int x, int y, int width,
			int height) {
		g.setColor(Color.white);
		g.drawRoundRect(x, y, width, height, 10, 10);

//		g.drawLine(x + 1, y + 1, x + width - 2, y + 1);
//		g.drawLine(x + 2, y + height, x + width - 2, y + height);
//		g.drawLine(x + 1, y + 2, x + 1, y + height - 1);
//		g.drawLine(x + width - 1, y + 2, x + width - 1, y + height - 1);
//
//		g.drawLine(x, y + height - 2, x, y + height - 2);
//		g.drawLine(x + width, y + height - 2, x + width, y + height - 2);
//
//		g.setColor(new Color(213, 223, 229));
//
//		g.drawLine(x + 2, y, x + width - 2, y);
//		g.drawLine(x + 2, y + height - 1, x + width - 2, y + height - 1);
//		g.drawLine(x, y + 2, x, y + height - 3);
//		g.drawLine(x + width, y + 2, x + width, y + height - 3);
//
//		g.drawLine(x + 1, y + 1, x + 1, y + 1);
//		g.drawLine(x + width - 1, y + 1, x + width - 1, y + 1);
//		g.drawLine(x + 1, y + height - 2, x + 1, y + height - 2);
//		g.drawLine(x + width - 1, y + height - 2, x + width - 1, y + height - 2);
	}

}