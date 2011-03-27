package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 * 
 * @author hxl
 * @date 2011-03-14
 * 
 */
public class BaseJButton extends JButton {

	private static final long serialVersionUID = 2770928799807258451L;

	private static final int DEFAULT_WIDTH = 50;
	private static final int DEFAULT_HEIGHT = 25;

	private Container container = null;
	private Icon icon = null;

	public BaseJButton() {
	}

	public BaseJButton(String title) {
		this(title, null);

	}

	public BaseJButton(String title, Container container) {
		this(title, null, container);
	}

	public BaseJButton(String title, Icon icon, Container container) {
		super(title);
		this.container = container;
		this.icon = icon;
		init();
	}

	protected void init() {

		if (container != null)
			container.add(this);

		if (icon != null)
			this.setIcon(icon);

		this.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));

		this.setFocusable(false);
		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder());
	}

}
