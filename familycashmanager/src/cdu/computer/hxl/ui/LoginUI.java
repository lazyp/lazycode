package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
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

import com.sun.java.swing.plaf.motif.MotifBorders.BevelBorder;

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
	// private JTextField loginNameField = null;
	final JTextField loginNameField = new JTextField(18);
	private JPasswordField passField = null;
	private BaseJButton resetButton = null;
	private BaseJButton submitButton = null;
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

				loginNameLabel = new JLabel("µÇÂ¼Ãû£º");
				loginPassLabel = new JLabel("ÃÜ    Âë£º");

				passField = new JPasswordField(18);
				resetButton = new BaseJButton("ÖØÖÃ");
				submitButton = new BaseJButton("µÇÂ¼");
				// if(mainFrame != null){

				mainFrame.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						loginNameField.setFocusable(false);
						passField.setFocusable(false);
					}

				});
				// }

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
