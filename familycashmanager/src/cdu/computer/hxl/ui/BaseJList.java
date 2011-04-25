package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 * 
 * @author hxl
 * @date 2011-03-23
 * 
 */
public class BaseJList extends JList {
	public BaseJList() {
		init();
	}

	protected void init() {
		this.setCellRenderer(new MyListCellRenderer());
	}

	private static class MyListCellRenderer extends JLabel implements
			ListCellRenderer {

		private boolean isRepaint = false;

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {

			if (value instanceof ListData) {
				ListData data = (ListData) value;

				if (data.isRepaint()) {
					isRepaint = true;
					setVerticalAlignment(SwingConstants.CENTER);
				} else
					isRepaint = false;

				setText(data.getValue());
				if (data.getIcon() != null) {
					setHorizontalAlignment(SwingConstants.LEADING);
					setIcon(data.getIcon());
					setIconTextGap(42);
				} else {
					setIcon(null);
					setHorizontalAlignment(SwingConstants.CENTER);
				}

			} else {
				String s = value.toString();
				setText(s);
			}

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			if (!isRepaint)
				setEnabled(list.isEnabled());
			else {
				setEnabled(false);

			}

			setFont(list.getFont());
			setOpaque(true);
			return this;

		}

		@Override
		protected void paintComponent(Graphics g) {
			if (isRepaint) {
				this.setOpaque(false);

				Graphics2D g2 = (Graphics2D) g.create();

				GradientPaint paint = new GradientPaint(0, 0, new Color(96, 96,
						96), 0, this.getHeight(), new Color(10, 10, 10));
				g2.setPaint(paint);
				g2.fillRect(0, 0, 210, 30);
				g2.dispose();
			}
			super.paintComponent(g);
			setForeground(Color.WHITE);
		}
	}

	public static class ListData {
		private String value;
		private Icon icon;

		private boolean isRepaint = false;

		public ListData(String value, Icon icon, boolean isRepaint) {
			this.value = value;
			this.icon = icon;
			this.isRepaint = isRepaint;
		}

		/**
		 * @return the value
		 */
		public String getValue() {
			return value;
		}

		/**
		 * @param value
		 *            the value to set
		 */
		public void setValue(String value) {
			this.value = value;
		}

		/**
		 * @return the icon
		 */
		public Icon getIcon() {
			return icon;
		}

		/**
		 * @param icon
		 *            the icon to set
		 */
		public void setIcon(Icon icon) {
			this.icon = icon;
		}

		/**
		 * @return the isRepaint
		 */
		public boolean isRepaint() {
			return isRepaint;
		}

		/**
		 * @param isRepaint
		 *            the isRepaint to set
		 */
		public void setRepaint(boolean isRepaint) {
			this.isRepaint = isRepaint;
		}

	}
}
