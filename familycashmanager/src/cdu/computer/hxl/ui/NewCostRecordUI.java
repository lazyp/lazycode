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
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ScrollPaneConstants;

/**
 * 新增支出记录窗口
 * 
 * @author hxl
 * 
 */
public class NewCostRecordUI extends BaseJDialog {

	private static final long serialVersionUID = -2303701219177219958L;
	private JPanel panel = null;
	private Frame owner = null;
	private JTextField moneyTextField;
	private JTextField timeTextField;

	public NewCostRecordUI(Frame owner) {
		super(owner, "新增支出记录", true);
		this.owner = owner;
	}

	@Override
	protected void initUI() {

		setBounds(0, 0, 450, 350);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			    setVisible(false);
			}
			
		});
		this.setLocationRelativeTo(owner);
		this.setResizable(false);
	


		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel titlelabel = new JLabel("\u65B0\u5EFA\u652F\u51FA\u9879\u76EE");
		titlelabel.setHorizontalAlignment(SwingConstants.CENTER);
		titlelabel.setBounds(172, 10, 92, 15);
		panel.add(titlelabel);

		JLabel moneyLabel = new JLabel("\u91D1\u989D\uFF1A");
		moneyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		moneyLabel.setBounds(34, 47, 54, 15);
		panel.add(moneyLabel);

		moneyTextField = new JTextField();
		moneyTextField.setBounds(122, 44, 116, 21);
		panel.add(moneyTextField);
		moneyTextField.setColumns(10);

		JLabel tipLabel = new JLabel("\u4EE5\u5143\u4E3A\u5355\u4F4D(2.30)");
		tipLabel.setForeground(Color.RED);
		tipLabel.setBounds(315, 47, 103, 15);
		panel.add(tipLabel);

		JLabel timeLabel = new JLabel("\u65F6\u95F4\uFF1A");
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(34, 162, 54, 15);
		panel.add(timeLabel);

		timeTextField = new JTextField();
		timeTextField.setBounds(122, 159, 183, 21);
		panel.add(timeTextField);
		timeTextField.setColumns(10);

		JLabel useLabel = new JLabel("\u7528\u9014\uFF1A");
		useLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		useLabel.setBounds(34, 82, 54, 15);
		panel.add(useLabel);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setBounds(34, 229, 54, 15);
		panel.add(remarkLabel);

		
		JTextArea remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		remarkTextArea.setBounds(122, 211, 183, 45);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(remarkTextArea);
		panel.add(scrollPane);

		JButton submit = new JButton("\u786E\u8BA4");
		submit.setBounds(122, 285, 84, 23);
		panel.add(submit);

		JButton resetbnt = new JButton("\u6E05\u7A7A");
		resetbnt.setBounds(216, 285, 84, 23);
		panel.add(resetbnt);

		JComboBox useComboBox = new JComboBox();
		useComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u4E70\u978B", "\u8863\u670D", "\u7C73", "\u83DC",
				"\u5176\u5B83" }));
		useComboBox.setBounds(122, 79, 116, 21);
		panel.add(useComboBox);

		JLabel sourceLabel = new JLabel("\u6765\u6E90\uFF1A");
		sourceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceLabel.setBounds(34, 120, 54, 15);
		panel.add(sourceLabel);

		JComboBox sourceComboBox = new JComboBox();
		sourceComboBox.setModel(new DefaultComboBoxModel(new String[] {
				"\u652F\u4ED8\u5B9D", "\u5EFA\u8BBE\u94F6\u884C\u5361",
				"\u62DB\u5546\u94F6\u884C\u5361" }));
		sourceComboBox.setBounds(122, 117, 116, 21);
		panel.add(sourceComboBox);
		this.setVisible(true);
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
