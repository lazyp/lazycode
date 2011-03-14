package cdu.computer.hxl.ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
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
		this.init();
	}

	public BaseJPanel(Container container) {
		this();
		this.container = container;
		if (container != null)
			container.add(this);
	}

	public BaseJPanel(Image img, Container container) {
		this(container);
		this.img = img;
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

	protected BaseJPanel setPanelSize(int width, int height) {
		// System.out.println(width + "," + height);
		// this.setSize(width, height);
		this.setPreferredSize(new Dimension(width, height));
		return this;
	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}

	/**
	 * ≥ı ºªØ
	 */
	abstract protected void init();
}
