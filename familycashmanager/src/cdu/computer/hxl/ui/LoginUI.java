package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.MemberService;
import cdu.computer.hxl.util.VarCacheFactory;

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

/**
 * ��¼Panel��ʵ��
 * 
 * @author hxl
 * @date 2011-03-14
 */
public class LoginUI {

	private static final long serialVersionUID = -5986434162400009724L;
	private JLabel loginNameLabel = new JLabel("��¼����");
	private JLabel loginPassLabel = new JLabel("��    �룺");
	// private JTextField loginNameField = null;
	final JTextField loginNameField = new JTextField(18);
	private JPasswordField passField = new JPasswordField(18);
	private BaseJButton resetButton = new BaseJButton("����");
	private BaseJButton submitButton = new BaseJButton("��¼");

	private static JPanel lgBgPanel = null;

	private BaseJFrame mainFrame = null;
	private Image bgImage = null;
	private Image topImage = null;

	static {
		lgBgPanel = new JPanel();
	}

	// private BaseJPanel top = null;
	private JLabel top = null;

	private BaseJPanel bottom = null;

	public LoginUI() {

	}

	@SuppressWarnings("serial")
	private void init() {

		passField.setEchoChar('*');

		lgBgPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

		top = new JLabel() {

			@Override
			public void paint(Graphics g) {
				super.paint(g);
				Graphics g2 = g.create();
				g2.drawImage(topImage, 0, 0, mainFrame.getWidth(), 80, null);
				g2.dispose();
			}

		};
		top.setPreferredSize(new Dimension(mainFrame.getWidth(), 80));

		lgBgPanel.add(top);

		bottom = new BaseJPanel(lgBgPanel) {

			@Override
			protected void init() {

				// if(mainFrame != null){
				/*
				 * mainFrame.addMouseListener(new MouseAdapter() {
				 * 
				 * @Override public void mouseClicked(MouseEvent e) {
				 * loginNameField.setFocusable(false);
				 * passField.setFocusable(false); }
				 * 
				 * }); // }
				 */

				loginNameField.setFocusable(false);
				passField.setFocusable(false);

				loginNameField.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						loginNameField.setFocusable(true);
						loginNameField.requestFocus();
					}

				});
				loginNameField.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
						loginNameField.setBorder(BorderFactory
								.createLineBorder(Color.GREEN));
					}

					public void focusLost(FocusEvent e) {
						loginNameField.setBorder(BorderFactory
								.createLineBorder(Color.BLACK));

					}

				});

				passField.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						passField.setFocusable(true);
						passField.requestFocus();
					}

				});
				passField.addFocusListener(new FocusListener() {

					public void focusGained(FocusEvent e) {
						passField.setBorder(BorderFactory
								.createLineBorder(Color.GREEN));
					}

					public void focusLost(FocusEvent e) {
						passField.setBorder(BorderFactory
								.createLineBorder(Color.BLACK));

					}

				});
				resetButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						loginNameField.setText("");

						passField.setText("");
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

		// mainFrame.add(top, BorderLayout.NORTH);
		mainFrame.add(lgBgPanel);
	}

	public boolean isAccessLogin() {
		MemberService mservice = (MemberService) ObjectFactory
				.getInstance("memberService");
		String uname = loginNameField.getText();
		String upass = new String(passField.getPassword());

		boolean b = mservice.checkMember(uname, upass);
		if (b) {
			VarCacheFactory.uname = uname;
			VarCacheFactory.upass = upass;
		}
		return b;
	}

	public JButton getResetButton() {
		return resetButton;
	}

	public JButton getSubmitButton() {
		return submitButton;
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
