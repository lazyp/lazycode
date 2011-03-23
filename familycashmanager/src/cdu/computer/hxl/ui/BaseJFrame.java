package cdu.computer.hxl.ui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cdu.computer.hxl.util.Constants;

/**
 * 家庭财务管理系统主显示窗体
 * 
 * @author hxl
 * @date 2011-03-06
 */
public class BaseJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private final Dimension screen = Toolkit.getDefaultToolkit()
			.getScreenSize();// 获得屏幕的大小

	private int width = 300;// 窗体默认的宽度
	private int height = 210;// 窗体默认的高度

	public BaseJFrame() {
		// this.getContentPane().setSize(new Dimension(width, height));
		this.setSize(width, height);
	}

	/**
	 * 设置财务管理系统框体的标题
	 */
	public BaseJFrame setFrameTitle(String title) {
		this.setTitle(title);
		return this;
	}

	/**
	 * 设置财务管理系统的宽度和高度
	 * 
	 * @param width
	 * @param height
	 * @return this
	 */
	public BaseJFrame setFrameSize(double width, double height) {

		/*
		 * 如果哦传入的宽度或者高度小于等于了0，那么就把窗口设置为默认高度、宽度
		 */
		if (width <= 0.0 || height <= 0.0) {
			width = this.width;
			height = this.height;
		}

		Dimension dimension = new Dimension();
		dimension.setSize(width, height);
		this.setSize(dimension);
		return this;
	}

	/**
	 * 设置窗口图标显示的图象
	 * 
	 * @param image
	 * @return this
	 */
	public BaseJFrame setFrameIconImage(Image image) {
		this.setIconImage(image);
		return this;
	}

	/**
	 * 设置此窗体是否可由用户调整大小
	 * 
	 * @return this
	 */
	public BaseJFrame setFrameResizable(boolean b) {
		this.setResizable(b);
		return this;
	}

	/**
	 * 设置窗体的位置居中
	 * 
	 * @return this object
	 */
	public BaseJFrame setFrameCenter() {
		double cashManagerWidth = this.getSize().getWidth();
		double cashManagerHeight = this.getSize().getHeight();
		double screenWidth = screen.getWidth();
		double screenHeight = screen.getHeight();

		this.setLocation(((int) (screenWidth - cashManagerWidth) / 2),
				(int) ((screenHeight - cashManagerHeight) / 2));

		return this;
	}

	public static void main(String[] args) throws IOException {

	}
}
