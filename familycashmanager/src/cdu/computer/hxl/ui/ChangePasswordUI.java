package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class ChangePasswordUI extends BaseJDialog {
	private static final long serialVersionUID = 2406791156960660538L;
	private JPanel contentPanel = null;
	private JPasswordField oldPass;
	private JPasswordField newPass;

	public ChangePasswordUI(BaseJFrame owner) {
		super(owner, "ÃÜÂëÐÞ¸Ä", true);
		initUI();
	}

	@Override
	protected void initUI() {
		setBounds(0, 0, 285, 234);
		getContentPane().setLayout(new BorderLayout());
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel titleLabel = new JLabel("\u5BC6\u7801\u66F4\u6539");
		titleLabel.setBounds(126, 10, 65, 15);
		contentPanel.add(titleLabel);

		JLabel oldLabel = new JLabel("\u65E7\u5BC6\u7801\uFF1A");
		oldLabel.setBounds(33, 38, 54, 15);
		contentPanel.add(oldLabel);

		oldPass = new JPasswordField();
		oldPass.setForeground(Color.RED);
		oldPass.setEchoChar('*');
		oldPass.setBounds(97, 35, 126, 21);
		contentPanel.add(oldPass);

		JLabel newLabel = new JLabel("\u65B0\u5BC6\u7801\uFF1A");
		newLabel.setBounds(33, 90, 54, 15);
		contentPanel.add(newLabel);

		newPass = new JPasswordField();
		newPass.setForeground(Color.RED);
		newPass.setBounds(97, 87, 126, 21);
		contentPanel.add(newPass);

		JButton submitButton = new JButton("ÐÞ¸Ä");
		submitButton.setBounds(80, 142, 65, 23);
		contentPanel.add(submitButton);

		JButton clearButton = new JButton("Çå¿Õ");
		clearButton.setBounds(166, 142, 65, 23);
		contentPanel.add(clearButton);
		super.initUI();
	}
}
