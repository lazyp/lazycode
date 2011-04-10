package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 日期控件
 * 
 * @author hxl
 * 
 */
public class DateCompontent extends BaseJWindow {
	private static final String[] weeks = new String[] { "日", "一", "二", "三",
			"四", "五", "六" };
	private static final String[] months = new String[] { "一月", "二月", "三月",
			"四月", "五月", "六月", "七月", "八月", "九月", "十月", "十一月", "十二月" };

	private static BaseJPanel datePanel = null;

	private static final int cellWidth = 16;

	private static final int cellHeight = 16;

	private static final Calendar calendar = Calendar.getInstance();

	private Color curColor = new Color(255, 255, 255);;

	private static int month = 0;
	private static int day = 0;
	private static int curMaxDay = 0;
	private static int[][] dates = new int[6][7];

	private int rex = 0;
	private int rey = 0;

	private int prex = 0;
	private int prey = 0;

	private DateLabel dateLabel = new DateLabel();

	private int isRepaint = 0;// 0表示第一次画，1表示清除，2表示重绘

	static {

		curMaxDay = calendar.getMaximum(Calendar.DATE);

		month = calendar.get(Calendar.MONTH);
		day = calendar.get(Calendar.DATE);

		calendar.set(Calendar.DATE, 1);
		int firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

		calendar.set(Calendar.MONTH, month - 1);

		int preMonthDaySum = calendar.getMaximum(Calendar.DATE);

		int first = preMonthDaySum - firstDayOfWeek - 1;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dates[i][j] = first;
				if (first == curMaxDay)
					first = 0;
				first++;
			}
		}

		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DATE, day);

		datePanel = new BaseJPanel() {

			@Override
			protected void init() {
				this.setPanelSize(112, 146);
			
				JButton jbt = new JButton("关闭");
				jbt.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						System.exit(0);

					}

				});
				add(jbt);
			}

		};
	}

	public DateCompontent() {
		super();
		dateLabel.setSize(112, 140);
		dateLabel.addMouseListener(new MouseListenerImpl());
		datePanel.add(dateLabel);
	}

	@Override
	protected void init() {
		super.init();
		setWindowSize(112, 146);
		setContentPane(datePanel);
		// this.addMouseListener(new MouseListenerImpl());

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

	/**
	 * 显示日期label
	 * 
	 * @author hxl
	 * 
	 */
	private class DateLabel extends JLabel {
		public DateLabel() {
			System.out.println("??????????");

		}

		@Override
		public void paintComponent(Graphics g) {

			System.out.println("!!!!!" + isRepaint);
			Graphics g2 = g.create();

			if (isRepaint == 0) {
				g2.setColor(Color.BLACK);
				g2.fillRect(0, 0, this.getWidth(), this.getHeight());
				int x = 0;
				int y = 0;

				g2.setColor(Color.GREEN);

				for (int i = 0; i < 7; i++) {
					g2.drawString(weeks[i], x, 24);
					x += cellWidth;

				}

				g2.setColor(Color.GRAY);
				g2.drawLine(0, 25, this.getWidth(), 25);

				g2.setColor(new Color(110, 110, 110, 110));

				x = 0;
				y = 25;

				for (int i = 0; i < 6; i++) {
					g2.drawLine(x, y + cellHeight, this.getWidth(), y
							+ cellHeight);

					y += cellHeight;
				}

				x = 16;

				for (int i = 0; i < 7; i++) {
					g2.drawLine(x, 25, x, this.getHeight() - 25);
					x += cellWidth;
				}

				x = 0;
				y = 41;

				g2.setColor(Color.GREEN);

				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 7; j++) {
						String str = "0" + dates[i][j];
						g2.drawString(str.substring(str.length() - 2), x, y);
						x += 16;
					}
					y += 16;
					x = 0;
				}
			} else if (isRepaint == 1) {
				System.out.println("~~~~~");
				g2.setColor(Color.BLACK);
				g2.fillRect(prex + 1, prey + 1, cellWidth - 1, cellHeight - 1);
				if (!(prey == 0 && prex == 0)) {
					g2.setColor(Color.GREEN);
					System.out.println(prex + "," + prey);
					String str = "0" + dates[(prey - 25) / 16][prex / 16];
					g2.drawString(str.substring(str.length() - 2), prex, prey
							+ cellHeight);
				}
				isRepaint = 2;
				repaint(rex + 1, rey + 1, cellWidth - 1, cellHeight - 1);
			} else if (isRepaint == 2) {

				g2.setColor(Color.WHITE);
				g2.fillRect(rex + 1, rey + 1, cellWidth - 1, cellHeight - 1);

				g2.setColor(Color.red);

				String str = "0" + dates[(rey - 25) / 16][rex / 16];

				g2.drawString(str.substring(str.length() - 2), rex, rey
						+ cellHeight);
				prex = rex;
				prey = rey;
				isRepaint = 1;
			}
			g2.dispose();

		}

	}

	/**
	 * 处理鼠标事件
	 * 
	 * @author hxl
	 * 
	 */
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

			isRepaint = 1;
			dateLabel
					.repaint(prex + 1, prey + 1, cellWidth - 1, cellHeight - 1);

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
