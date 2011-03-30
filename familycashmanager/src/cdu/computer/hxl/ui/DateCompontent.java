package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期控件
 * 
 * @author hxl
 * 
 */
public class DateCompontent extends BaseJWindow {
	private static final String[] weeks = new String[] { "星期日", "星期一", "星期二",
			"星期三", "星期四", "星期五", "星期六" };
	private static final String[] months = new String[] { "一月", "二月", "三月",
			"四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };

	private static final int cellWidth = 16;

	private static final int cellHeight = 16;

	private static final Calendar calendar = Calendar.getInstance();

	private Color curColor = Color.green;

	private int month = 0;
	private int day = 0;

	private int rex = 0;
	private int rey = 0;

	private int prex = 0;
	private int prey = 0;

	private boolean isRepaint = false;

	@Override
	protected void init() {
		super.init();

		month = calendar.get(Calendar.MONTH) + 1;
		day = calendar.get(Calendar.DATE);

		setWindowSize(112, 146);
		this.addMouseListener(new MouseListenerImpl());
	}

	@Override
	public void paint(Graphics g) {
		// super.paint(g);

		Graphics g2 = g.create();
		if (!isRepaint) {
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.setColor(Color.GRAY);
			g2.drawLine(0, 25, this.getWidth(), 25);

			g2.setColor(new Color(110, 110, 110, 110));

			int x = 0;
			int y = 25;

			for (int i = 0; i < 6; i++) {
				g2.drawLine(x, y + cellHeight, this.getWidth(), y + cellHeight);
				y += cellHeight;
			}

			x = 16;

			for (int i = 0; i < 7; i++) {
				g2.drawLine(x, 25, x, this.getHeight() - 25);
				x += cellWidth;
			}
		} else {

			g2.setColor(Color.BLACK);
			g2.fillRect(prex + 1, prey + 1, cellWidth - 1, cellHeight - 1);

			g2.setColor(curColor);
			g2.fillRect(rex + 1, rey + 1, cellWidth - 1, cellHeight - 1);

			prex = rex;
			prey = rey;
		}
		g2.dispose();

	}

	@Override
	public void update(Graphics g) {

	}

	/**
	 * 
	 * @param month
	 * @param day
	 * @return 星期
	 */
	private String getWeek(int month, int day) {
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, day);
		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
		return weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

	private class MouseListenerImpl extends MouseAdapter {

		@Override
		public void mouseClicked(MouseEvent e) {

			int x = e.getX();
			int y = e.getY();

			if (y <= 25 || y >= 121)
				return;

			int week = x >> 4;

			if (x % 16 != 0) {
				week += 1;
			}

			rex = x - (x % 16 == 0 ? 16 : x % 16);
			rey = y - ((y - 25) % 16 == 0 ? 16 : (y - 25) % 16);

			System.out.println(rex + "," + rey);
			System.out.println(weeks[week - 1]);

			isRepaint = true;
			curColor = Color.GREEN;
			repaint(0);

		}

		@Override
		public void mouseEntered(MouseEvent e) {

		}

		@Override
		public void mouseExited(MouseEvent e) {

		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DateCompontent dateComp = new DateCompontent();
		System.out.println(dateComp.getWeek(3, 30));
	}

}
