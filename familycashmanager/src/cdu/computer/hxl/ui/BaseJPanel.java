package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * 
 * @author hxl
 * @date 2011-03-14
 */
public abstract class BaseJPanel extends JPanel {
	private Container container = null;
	private Image img = null;

	public BaseJPanel() {
		this(null, null);
	}

	public BaseJPanel(Container container) {
		this(null, container);

	}
	public BaseJPanel(Image img, Container container) {
		this.img = img;
		this.container = container;
		if (container != null)
			container.add(this);
		this.init();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (img != null) {
			Graphics g2 = g.create();
			// System.out.println(img.getWidth(null) + "," +
			// img.getHeight(null));
			g2.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
			g2.dispose();
		}
	}

	/**
	 * 设置panel的大小
	 * 
	 * @param width
	 * @param height
	 * @return this
	 */
	public BaseJPanel setPanelSize(int width, int height) {
		// System.out.println(width + "," + height);
		// this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		return this;
	}

	/**
	 * 设置panel的前景色
	 * 
	 * @param color
	 * @return this
	 */
	public BaseJPanel setPanelForeground(Color color) {
		this.setForeground(color);
		return this;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * 初始化
	 */
	abstract protected void init();
}
