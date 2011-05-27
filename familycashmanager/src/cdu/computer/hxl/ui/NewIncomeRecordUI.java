package cdu.computer.hxl.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.BankService;
import cdu.computer.hxl.service.IncomeService;
import cdu.computer.hxl.util.Accessor;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class NewIncomeRecordUI extends BaseJDialog {

	private static final long serialVersionUID = 6375647562062306231L;

	private static final Color color = new Color(52, 55, 59);// 默认的颜色

	private static final IncomeService incomeService = (IncomeService) ObjectFactory
			.getInstance("incomeService");
	private JPanel contentPanel = null;
	private JTextField amountTextField = null;
	private JTextField timeTextField = null;
	private BaseJComboBox saveComboBox = null;
	private BaseJComboBox sourceComboBox = null;
	private JTextArea remarkTextArea = null;
	private JButton submitbnt = null;
	private JButton clearbtn = null;

	private String title = "";
	private int rowid = -1;

	private boolean isLegal1 = false;
	private boolean isLegal2 = false;

	public NewIncomeRecordUI(String title, BaseJFrame owner) {
		super(owner, title, true);
		this.title = title;
		initUI();
	}

	@Override
	protected void initUI() {

		setSize(400, 350);
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);
		getContentPane().setLayout(null);

		JLabel titleLabel = new JLabel(title);
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

		amountTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String amount = amountTextField.getText();
				if (!Accessor.isNumber(amount)) {
					amountTextField.setBackground(Color.RED);
					isLegal1 = false;
				} else {
					amountTextField.setBackground(color);
					isLegal1 = true;
				}
			}

		});
		// amountTextField.setColumns(10);

		JLabel sourceLabel = new JLabel("\u6765\u6E90\uFF1A");
		sourceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceLabel.setBounds(51, 83, 54, 15);
		getContentPane().add(sourceLabel);

		sourceComboBox = new BaseJComboBox();
		// sourceComboBox.setModel(new DefaultComboBoxModel(new String[] {
		// "\u5DE5\u8D44", "\u5916\u5305", "\u5176\u5B83" }));
		final DefaultComboBoxModel sourcemodel = new DefaultComboBoxModel();
		sourceComboBox.setModel(sourcemodel);
		sourceComboBox.setBounds(145, 80, 83, 21);
		getContentPane().add(sourceComboBox);

		JLabel saveLabel = new JLabel("\u5B58\u5165\uFF1A");
		saveLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		saveLabel.setBounds(51, 167, 54, 15);
		getContentPane().add(saveLabel);

		saveComboBox = new BaseJComboBox();
		// saveComboBox.setModel(new DefaultComboBoxModel(
		// new String[] { "\u5DE5\u5546\u94F6\u884C" }));
		saveComboBox.setBounds(145, 164, 83, 21);
		final DefaultComboBoxModel savemodel = new DefaultComboBoxModel();
		saveComboBox.setModel(savemodel);
		getContentPane().add(saveComboBox);

		JLabel timeLabel = new JLabel("\u65F6\u95F4\uFF1A");
		timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		timeLabel.setBounds(51, 120, 54, 15);
		getContentPane().add(timeLabel);

		timeTextField = new JTextField();
		timeTextField.setBounds(145, 117, 140, 21);
		getContentPane().add(timeTextField);
		timeTextField.setColumns(10);

		timeTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				String time = timeTextField.getText();
				if (!Accessor.isLegalTime(time)) {
					timeTextField.setBackground(Color.RED);
					isLegal2 = false;
				} else {
					timeTextField.setBackground(color);
					isLegal2 = true;
				}
			}

		});

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setBounds(51, 223, 54, 15);
		getContentPane().add(remarkLabel);
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setSize(140, 52);
		scrollPanel.setLocation(145, 204);

		remarkTextArea = new JTextArea();

		remarkTextArea.setLineWrap(true);
		remarkTextArea.setRows(2);
		remarkTextArea.setColumns(10);
		// remarkTextArea.setBounds(145, 204, 138, 50);
		scrollPanel.setViewportView(remarkTextArea);

		getContentPane().add(scrollPanel);

		submitbnt = new JButton("保存");
		submitbnt.setBounds(145, 266, 66, 23);

		submitbnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (isLegal1 && isLegal2) {
					final double money = Double.parseDouble(amountTextField
							.getText());
					final String time = timeTextField.getText();

					@SuppressWarnings("unchecked")
					final Integer saveid = (Integer) ((Map<String, Object>) saveComboBox
							.getSelectedItem()).get("rowid");

					@SuppressWarnings("unchecked")
					final Integer sourceid = (Integer) ((Map<String, Object>) sourceComboBox
							.getSelectedItem()).get("rowid");

					final String remark = remarkTextArea.getText();
					new ThreadExecutorUtils() {

						@Override
						protected void task() {
							getOwner().setStatusText("正在保存收入记录...");

							Map<String, Object> data = new HashMap<String, Object>();

							data.put("rowid", rowid);
							data.put("amount", money);
							data.put("remark", remark);
							data.put("bankid", saveid);
							data.put("sourceid", sourceid);
							data.put("date", time);

							incomeService.addIncomeItem(data);

							getOwner().setStatusText("保存成功");
						}
					}.exec();
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(getOwner(), "有不合法输入，请检查...",
							"警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		getContentPane().add(submitbnt);

		clearbtn = new JButton("\u6E05\u7A7A");
		clearbtn.setBounds(219, 266, 66, 23);

		clearbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				amountTextField.setText("");
				timeTextField.setText("");
				remarkTextArea.setText("");
			}
		});

		getContentPane().add(clearbtn);

		new ThreadExecutorUtils() {

			@Override
			protected void task() {
				BankService bService = (BankService) ObjectFactory
						.getInstance("bankService");
				List<Map<String, Object>> rankdata = bService.loadAllBank();
				int size = rankdata.size();
				for (int i = 0; i < size; i++) {
					Map<String, Object> mm = rankdata.get(i);
					mm.put("name", mm.get("bankname"));
					mm.remove("bankname");
					savemodel.addElement(mm);
				}
			}
		}.exec();

		new ThreadExecutorUtils() {

			@Override
			protected void task() {
				List<Map<String, Object>> incomeList = incomeService
						.loadIncomeCategoryForList(null);
				int size = incomeList.size();
				for (int i = 0; i < size; i++) {
					Map<String, Object> mm = incomeList.get(i);
					mm.put("name", mm.get("categoryname"));
					mm.remove("categoryname");
					sourcemodel.addElement(mm);
				}
			}
		}.exec();

		super.initUI();
	}

	public void setAmount(String amount) {
		amountTextField.setText(amount);
	}

	public void setTimeText(String time) {
		this.timeTextField.setText(time);
	}

	public void setRemark(String remark) {
		remarkTextArea.setText(remark);
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}

}
