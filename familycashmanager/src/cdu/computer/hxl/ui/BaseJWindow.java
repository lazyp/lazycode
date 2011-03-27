package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author hxl
 * @date 2010-03-26
 */
public class BaseJWindow extends JWindow {
	public BaseJWindow(JFrame frame) {
		super(frame);
		init();
	}

	/**
	 * 组件初始化操作，可以由子类实现或者扩展
	 */
	protected void init() {
		this.setLocationRelativeTo(null);
		this.setAlwaysOnTop(true);
		this.setBackground(Color.WHITE);
	}

	/**
	 * 设置大小
	 * 
	 * @param width
	 * @param height
	 */
	protected void setWindowSize(int width, int height) {
		super.setSize(new Dimension(width, height));
	}

	@Override
	public void paint(Graphics g) {

		g.setColor(Color.GREEN);
		g.drawRect(0, 0, this.getWidth(), this.getHeight());
		this.setBounds(0, 0, this.getWidth(), this.getHeight());
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		BaseJWindow bjw = new BaseJWindow(null);
		bjw.setBackground(Color.WHITE);
		bjw.setSize(new Dimension(200, 300));

		bjw.setVisible(true);
	}
}
