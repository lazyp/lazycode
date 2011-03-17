package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JButton;

/**
 * 
 * @author hxl
 * @date 2011-03-14
 * 
 */
public class BaseJButton extends JButton {

	private static final long serialVersionUID = 2770928799807258451L;
	private Container container = null;

	public BaseJButton(String title) {
		this(title, null);

	}

	public BaseJButton(String title, Container container) {
		super(title);
		this.container = container;
		init();
	}

	protected void init() {

		if (container != null)
			container.add(this);

		this.setBackground(Color.BLACK);
		this.setForeground(Color.WHITE);
	}

}
