package cdu.computer.hxl.ui;

import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 登录Panel的实现
 * 
 * @author hxl
 * @date 2011-03-14
 */
public class LoginPanel extends BaseJPanel {

	private static final long serialVersionUID = -5986434162400009724L;
	private JLabel loginNameLabel = null;
	private JLabel loginPassLabel = null;
	private JTextField loginNameField = null;
	private JPasswordField passField = null;
	private JButton resetButton = null;
	private JButton submitButton = null;

	private JFrame mainFrame = null;

	public LoginPanel() {
		super();
	}

	public LoginPanel(JFrame mainFrame) {
		super(mainFrame);
		this.mainFrame = mainFrame;
	}

	public LoginPanel(Image backgroundImage, JFrame mainFrame) {
		super(backgroundImage, mainFrame);
	}

	public LoginPanel setTopImage(Image img) {
		JPanel topPanel = new BaseJPanel(img, null) {
			@Override
			protected void init() {
				this.setPanelSize(mainFrame.getWidth(),
						mainFrame.getHeight() >> 1);
			}

		};
		this.getContainer().setComponentZOrder(topPanel, 0);
		// System.out.println(topPanel.getWidth());
		return this;
	}

	@Override
	protected void init() {
		loginNameLabel = new JLabel("登录名：");
		loginPassLabel = new JLabel("密    码：");
		loginNameField = new JTextField(18);
		passField = new JPasswordField(18);
		resetButton = new JButton("重置");
		submitButton = new JButton("登录");

		this.add(loginNameLabel);
		this.add(loginNameField);
		this.add(loginPassLabel);
		this.add(passField);
		this.add(resetButton);
		this.add(submitButton);

	}

}
