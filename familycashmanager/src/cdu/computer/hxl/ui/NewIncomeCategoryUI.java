package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class NewIncomeCategoryUI extends BaseJDialog {

	private static final long serialVersionUID = 3359896724350259509L;
	private BaseJFrame owner = null;
	private JTextField categoryNameTextField;

	/**
	 * Create the dialog.
	 */
	public NewIncomeCategoryUI(BaseJFrame owner) {
		super(owner, "添加收入类别", true);
		this.owner = owner;

	}

	@Override
	protected void initUI() {
		setBounds(0, 0, 350, 230);
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("\u6DFB\u52A0\u6536\u5165\u7C7B\u522B");
		titleLabel.setBounds(118, 10, 93, 15);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(titleLabel);

		JLabel categoryNameLabel = new JLabel("\u7C7B\u522B\u540D\u79F0\uFF1A");
		categoryNameLabel.setBounds(34, 44, 84, 15);
		categoryNameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(categoryNameLabel);

		categoryNameTextField = new JTextField();
		categoryNameTextField.setBounds(128, 41, 146, 21);
		categoryNameTextField.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(categoryNameTextField);
		categoryNameTextField.setColumns(10);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setBounds(64, 87, 54, 15);
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(remarkLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(128, 72, 146, 50);
		getContentPane().add(scrollPane);

		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		scrollPane.setViewportView(remarkTextArea);

		JButton submitbnt = new JButton("\u4FDD\u5B58");
		submitbnt.setBounds(128, 145, 64, 23);
		getContentPane().add(submitbnt);

		JButton resetbnt = new JButton("\u6E05\u7A7A");
		resetbnt.setBounds(202, 145, 60, 23);
		getContentPane().add(resetbnt);
		
//		setLocationRelativeTo(owner);
//		setVisible(true);
	}
}
