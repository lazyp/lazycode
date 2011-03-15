package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

/**
 * µÇÂ¼PanelµÄÊµÏÖ
 * 
 * @author hxl
 * @date 2011-03-14
 */
public class LoginUI {

	private static final long serialVersionUID = -5986434162400009724L;
	private JLabel loginNameLabel = null;
	private JLabel loginPassLabel = null;
	private JTextField loginNameField = null;
	private JPasswordField passField = null;
	private JButton resetButton = null;
	private JButton submitButton = null;
	private static JPanel lgBgPanel = null;

	private BaseJFrame mainFrame = null;
	private Image bgImage = null;
	private Image topImage = null;

	static {
		lgBgPanel = new JPanel();
	}

	private BaseJPanel top = null;

	private BaseJPanel bottom = null;

	public LoginUI() {

	}

	@SuppressWarnings("serial")
	private void init() {
		top = new BaseJPanel(topImage, lgBgPanel) {
			@Override
			protected void init() {
				this.setPreferredSize(new Dimension(mainFrame.getWidth(), 80));
			}
		};

		bottom = new BaseJPanel(lgBgPanel) {

			@Override
			protected void init() {
				loginNameLabel = new JLabel("µÇÂ¼Ãû£º");
				loginPassLabel = new JLabel("ÃÜ    Âë£º");
				loginNameField = new JTextField(18);
				passField = new JPasswordField(18);
				resetButton = new JButton("ÖØÖÃ");
				submitButton = new JButton("µÇÂ¼");

				submitButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainFrame = mainFrame.setFrameSize(680, 580)
								.setFrameCenter().setFrameResizable(true);
						mainFrame.getContentPane().removeAll();
						// mainFrame.getContentPane().repaint();
						// mainFrame.getContentPane().validate();
					}
				});

				this.add(loginNameLabel);
				this.add(loginNameField);
				this.add(loginPassLabel);
				this.add(passField);
				this.add(resetButton);
				this.add(submitButton);
				this.setPreferredSize(new Dimension(mainFrame.getWidth(), 130));
			}
		};

		mainFrame.add(lgBgPanel);
	}

	protected LoginUI getInstance() {
		init();
		return this;
	}

	public static Builder instance() {
		return new LoginUI().new Builder();
	}

	public class Builder {
		public Builder() {
		};

		public Builder topImage(Image img) {
			topImage = img;
			return this;
		}

		public Builder bgImage(Image img) {
			bgImage = img;
			return this;
		}

		public Builder mainFrame(BaseJFrame frame) {
			mainFrame = frame;
			return this;
		}

		public LoginUI build() {
			return getInstance();
		}
	}
}
