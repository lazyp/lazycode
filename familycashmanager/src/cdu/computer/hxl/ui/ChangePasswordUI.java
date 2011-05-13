package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.MemberService;
import cdu.computer.hxl.util.ThreadExecutorUtils;
import cdu.computer.hxl.util.VarCacheFactory;

public class ChangePasswordUI extends BaseJDialog {
	private static final long serialVersionUID = 2406791156960660538L;
	private JPanel contentPanel = null;
	private JPasswordField oldPass = null;
	private JPasswordField newPass = null;

	public ChangePasswordUI(BaseJFrame owner) {
		super(owner, "密码修改", true);
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
		newPass.setEchoChar('*');
		newPass.setBounds(97, 87, 126, 21);
		contentPanel.add(newPass);

		JButton submitButton = new JButton("修改");
		submitButton.setBounds(80, 142, 65, 23);

		submitButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new ThreadExecutorUtils() {

					@Override
					protected void task() {
						setVisible(false);
						String oldpassStr = new String(oldPass.getPassword());
						String newpassStr = new String(newPass.getPassword());
						getOwner().setStatusText("正在修改结果...");
						MemberService mService = (MemberService) ObjectFactory
								.getInstance("memberService");
						if (mService.modifyPass(VarCacheFactory.uname,
								oldpassStr, newpassStr)) {
							getOwner().setStatusText("修改密码成功");
						} else {
							getOwner().setStatusText("修改密码失败");
						}

					}
				}.exec();
			}
		});

		contentPanel.add(submitButton);

		JButton clearButton = new JButton("清空");
		clearButton.setBounds(166, 142, 65, 23);
		contentPanel.add(clearButton);

		clearButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				oldPass.setText("");
				newPass.setText("");
			}
		});
		super.initUI();
	}
}
