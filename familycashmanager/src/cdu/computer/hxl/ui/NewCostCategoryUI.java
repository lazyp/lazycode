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
import javax.swing.JTextArea;

public class NewCostCategoryUI extends BaseJDialog {

	private  JPanel contentPanel = null;
	private Frame owner = null;
	private JTextField nameTextField;

	/**
	 * Create the dialog.
	 */
	public NewCostCategoryUI(Frame owner) {
		super(owner, "新增支出类别", true);
		this.owner = owner;
	}

	@Override
	protected void initUI() {
		setBounds(0, 0, 350, 250);
		
		contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		getContentPane().setLayout(null);

		JLabel tileLabel = new JLabel("\u6DFB\u52A0\u652F\u51FA\u7C7B\u522B");
		tileLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tileLabel.setBounds(127, 21, 89, 15);
		getContentPane().add(tileLabel);

		JLabel categoryLabel = new JLabel("\u7C7B\u522B\u540D\u79F0\uFF1A");
		categoryLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		categoryLabel.setBounds(32, 66, 73, 15);
		getContentPane().add(categoryLabel);

		nameTextField = new JTextField();
		nameTextField.setBounds(127, 63, 153, 21);
		getContentPane().add(nameTextField);
		nameTextField.setColumns(10);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setBounds(51, 110, 54, 15);
		getContentPane().add(remarkLabel);
		
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		remarkTextArea.setBounds(127, 108, 153, 42);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 108, 153, 42);
		scrollPane.setViewportView(remarkTextArea);
		getContentPane().add(scrollPane);

		JButton submitbnt = new JButton("\u4FDD\u5B58");
		submitbnt.setBounds(127, 182, 73, 23);
		getContentPane().add(submitbnt);

		JButton clearbnt = new JButton("\u6E05\u7A7A");
		clearbnt.setBounds(207, 182, 73, 23);
		getContentPane().add(clearbnt);
	    /*
	     * 父类已经实现
	     */
//		setLocationRelativeTo(owner);
//		this.setVisible(true);
	}
}
