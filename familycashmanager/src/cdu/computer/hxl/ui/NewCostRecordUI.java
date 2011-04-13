package cdu.computer.hxl.ui;

import java.awt.Dialog;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

/**
 * 新增支出记录窗口
 * 
 * @author hxl
 * 
 */
public class NewCostRecordUI extends BaseJDialog {
	private JPanel panel = null;
	private Frame owner = null;
	private JTextField textField;
	private JTextField textField_1;

	public NewCostRecordUI(Frame owner) {
		super(owner, "新增支出记录", true);
		this.owner = owner;
	}

	@Override
	protected void initUI() {

		setBounds(0, 0, 450, 350);

		this.setResizable(false);
		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel label = new JLabel("\u65B0\u5EFA\u652F\u51FA\u9879\u76EE");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(172, 10, 92, 15);
		panel.add(label);

		JLabel label_1 = new JLabel("\u91D1\u989D\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setBounds(34, 47, 54, 15);
		panel.add(label_1);

		textField = new JTextField();
		textField.setBounds(122, 44, 66, 21);
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_2 = new JLabel("\u4EE5\u5143\u4E3A\u5355\u4F4D(2.30)");
		label_2.setForeground(Color.RED);
		label_2.setBounds(331, 47, 103, 15);
		panel.add(label_2);

		JLabel label_3 = new JLabel("\u65F6\u95F4\uFF1A");
		label_3.setHorizontalAlignment(SwingConstants.RIGHT);
		label_3.setBounds(34, 162, 54, 15);
		panel.add(label_3);

		textField_1 = new JTextField();
		textField_1.setBounds(122, 159, 183, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_4 = new JLabel("\u7528\u9014\uFF1A");
		label_4.setHorizontalAlignment(SwingConstants.RIGHT);
		label_4.setBounds(34, 82, 54, 15);
		panel.add(label_4);

		JLabel label_5 = new JLabel("\u5907\u6CE8\uFF1A");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setBounds(34, 229, 54, 15);
		panel.add(label_5);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(122, 211, 183, 45);
		panel.add(textArea);

		JButton button = new JButton("\u63D0\u4EA4");
		button.setBounds(122, 285, 84, 23);
		panel.add(button);

		JButton button_1 = new JButton("\u6E05\u9664");
		button_1.setBounds(216, 285, 84, 23);
		panel.add(button_1);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u4E70\u978B", "\u8863\u670D", "\u7C73", "\u83DC",
				"\u5176\u5B83" }));
		comboBox.setBounds(122, 79, 116, 21);
		panel.add(comboBox);

		JLabel label_6 = new JLabel("\u6765\u6E90\uFF1A");
		label_6.setHorizontalAlignment(SwingConstants.RIGHT);
		label_6.setBounds(34, 120, 54, 15);
		panel.add(label_6);

		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {
				"\u652F\u4ED8\u5B9D", "\u5EFA\u8BBE\u94F6\u884C\u5361",
				"\u62DB\u5546\u94F6\u884C\u5361" }));
		comboBox_1.setBounds(122, 117, 116, 21);
		panel.add(comboBox_1);

	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		JFrame frame = new JFrame();
		try {
			NewCostRecordUI dialog = new NewCostRecordUI(frame);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
