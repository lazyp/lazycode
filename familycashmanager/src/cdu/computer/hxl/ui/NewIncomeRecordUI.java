package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

public class NewIncomeRecordUI extends BaseJDialog {

	private static final long serialVersionUID = 6375647562062306231L;
	private  JPanel contentPanel = null;
	private JTextField amountTextField = null;
	private JTextField textField = null;
	private JTextField timeTextField;
	
	private Frame owner = null;

	public NewIncomeRecordUI(Frame owner) {
		super(owner, "新增收入记录", true);
	}

	@Override
	protected void initUI() {
		
		setSize(400, 300);
		contentPanel=new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel("\u6DFB\u52A0\u6536\u5165\u8BB0\u5F55");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setBounds(145, 10, 83, 15);
		getContentPane().add(titleLabel);

		JLabel moneyLabel = new JLabel("\u91D1\u989D\uFF1A");
		moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		moneyLabel.setBounds(51, 47, 54, 15);
		getContentPane().add(moneyLabel);

		amountTextField = new JTextField();
		amountTextField.setHorizontalAlignment(SwingConstants.LEFT);
		amountTextField.setBounds(145, 44, 83, 21);
		getContentPane().add(amountTextField);
		amountTextField.setColumns(10);

		JLabel sourceLabel = new JLabel("\u6765\u6E90\uFF1A");
		sourceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceLabel.setBounds(51, 83, 54, 15);
		getContentPane().add(sourceLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u5DE5\u8D44", "\u5916\u5305", "\u5176\u5B83" }));
		comboBox.setBounds(145, 80, 83, 21);
		getContentPane().add(comboBox);

		JLabel timeLabel = new JLabel("\u65F6\u95F4\uFF1A");
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(51, 120, 54, 15);
		getContentPane().add(timeLabel);

		timeTextField = new JTextField();
		timeTextField.setBounds(145, 117, 140, 21);
		getContentPane().add(timeTextField);
		timeTextField.setColumns(10);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setBounds(51, 162, 54, 15);
		getContentPane().add(remarkLabel);

		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setSize(140, 52);
		scrollPanel.setLocation(145, 158);
		
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setRows(2);
		remarkTextArea.setColumns(10);
		remarkTextArea.setBounds(145, 158, 140, 36);
		scrollPanel.setViewportView(remarkTextArea);
		
		getContentPane().add(scrollPanel);

		JButton submitbnt = new JButton("\u786E\u8BA4");
		submitbnt.setBounds(145, 220, 66, 23);
		getContentPane().add(submitbnt);

		JButton btn = new JButton("\u6E05\u7A7A");
		btn.setBounds(219, 220, 66, 23);
		getContentPane().add(btn);
		this.setLocationRelativeTo(owner);
		this.setVisible(true);
	}
}
