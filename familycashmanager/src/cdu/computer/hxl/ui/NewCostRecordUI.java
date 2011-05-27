package cdu.computer.hxl.ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingConstants;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.BankService;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.util.Accessor;
import cdu.computer.hxl.util.ThreadExecutorUtils;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 新增支出记录窗口
 * 
 * @author hxl
 * 
 */
public class NewCostRecordUI extends BaseJDialog {

	private static final long serialVersionUID = -2303701219177219958L;

	private final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");
	private final BankService bService = (BankService) ObjectFactory
			.getInstance("bankService");

	private static final Color color = new Color(52, 55, 59);// 默认的颜色

	private JPanel panel = null;
	private JTextField moneyTextField = null;
	private JTextField timeTextField = null;
	private BaseJComboBox sourceComboBox = null;
	private JTextArea remarkTextArea = null;
	private BaseJComboBox useComboBox = null;
	private int rowid = -1;
	private String title = "";

	private boolean isLegal1 = false;
	private boolean isLegal2 = false;

	public NewCostRecordUI(String title, BaseJFrame owner) {
		super(owner, title, true);
		this.title = title;
		initUI();
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

		this.setResizable(false);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel titlelabel = new JLabel(title);
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

		moneyTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				String money = moneyTextField.getText();
				if (!Accessor.isNumber(money)) {
					moneyTextField.setBackground(Color.RED);
					isLegal1 = false;
				} else {
					moneyTextField.setBackground(color);
					isLegal1 = true;
				}
			}

		});

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
		// timeTextField.setBackground(Color.red);
		panel.add(timeTextField);

		timeTextField.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(FocusEvent e) {
				String text = timeTextField.getText();

				if (!Accessor.isLegalTime(text)) {
					timeTextField.setBackground(Color.RED);
					isLegal2 = false;
				} else {
					timeTextField.setBackground(color);
					isLegal2 = true;
				}

			}

		});

		timeTextField.setColumns(10);

		JLabel useLabel = new JLabel("\u7528\u9014\uFF1A");
		useLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		useLabel.setBounds(34, 82, 54, 15);
		panel.add(useLabel);

		JLabel remarkLabel = new JLabel("\u5907\u6CE8\uFF1A");
		remarkLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		remarkLabel.setBounds(34, 229, 54, 15);
		panel.add(remarkLabel);

		remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		remarkTextArea.setBounds(122, 211, 183, 45);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(remarkTextArea);
		panel.add(scrollPane);

		JButton submit = new JButton("保存");
		submit.setBounds(122, 285, 84, 23);
		panel.add(submit);
		submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (isLegal1 && isLegal2) {
					setVisible(false);
					new ThreadExecutorUtils() {

						@SuppressWarnings("unchecked")
						@Override
						protected void task() {

							double money = Double.parseDouble(moneyTextField
									.getText());

							int useid = (Integer) ((Map<String, Object>) useComboBox
									.getSelectedItem()).get("rowid");
							int sourceid = (Integer) ((Map<String, Object>) sourceComboBox
									.getSelectedItem()).get("rowid");
							String remark = remarkTextArea.getText();
							String datetime = timeTextField.getText();

							Map<String, Object> dataMap = new HashMap<String, Object>();
							dataMap.put("amount", money);
							dataMap.put("bankid", sourceid);
							dataMap.put("useid", useid);
							dataMap.put("remark", remark);
							dataMap.put("date", datetime);
							dataMap.put("rowid", rowid);

							getOwner().setStatusText("正在保存数据...");
							cService.addCost(dataMap);
							getOwner().setStatusText("保存成功");
						}
					}.exec();
				} else {
					JOptionPane.showMessageDialog(getOwner(), "有不合法输入，请检查...",
							"警告", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		JButton resetbnt = new JButton("\u6E05\u7A7A");
		resetbnt.setBounds(216, 285, 84, 23);

		resetbnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				moneyTextField.setText("");
				timeTextField.setText("");
				remarkTextArea.setText("");
			}
		});

		panel.add(resetbnt);

		useComboBox = new BaseJComboBox();
		useComboBox.setModel(new DefaultComboBoxModel());
		useComboBox.setBounds(122, 79, 116, 21);
		panel.add(useComboBox);

		JLabel sourceLabel = new JLabel("\u6765\u6E90\uFF1A");
		sourceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		sourceLabel.setBounds(34, 120, 54, 15);
		panel.add(sourceLabel);

		sourceComboBox = new BaseJComboBox();
		sourceComboBox.setModel(new DefaultComboBoxModel());
		sourceComboBox.setBounds(122, 117, 116, 21);
		panel.add(sourceComboBox);

		/*
		 * 加载用途列表
		 */

		new ThreadExecutorUtils() {

			@Override
			protected void task() {

				DefaultComboBoxModel dcb = (DefaultComboBoxModel) useComboBox
						.getModel();
				List<Map<String, Object>> category = cService
						.loadCostCategoryForList(null);
				int size = category.size();
				for (int i = 0; i < size; i++) {
					Map<String, Object> mm = category.get(i);
					mm.put("name", mm.get("categoryname"));
					mm.remove("categoryname");
					dcb.addElement(mm);
				}
			}
		}.exec();

		/*
		 * 加载银行列表
		 */
		new ThreadExecutorUtils() {

			@Override
			protected void task() {
				DefaultComboBoxModel dcb = (DefaultComboBoxModel) sourceComboBox
						.getModel();
				List<Map<String, Object>> bank = bService.loadAllBank();
				int size = bank.size();
				for (int i = 0; i < size; i++) {
					Map<String, Object> mm = bank.get(i);
					mm.put("name", mm.get("bankname"));
					mm.remove("bankname");
					dcb.addElement(mm);
				}
			}
		}.exec();
		super.initUI();
	}

	public void setMoney(String money) {
		this.moneyTextField.setText(money);
	}

	public void setTime(String time) {
		this.timeTextField.setText(time);
	}

	public void setRemarkText(String remarkText) {
		this.remarkTextArea.setText(remarkText);
	}

	public void setRowid(int id) {
		this.rowid = id;
	}

	public void setSource(Object o) {
		sourceComboBox.setSelectedItem(o);
	}

	public void setUseComboBox(Object o) {
		useComboBox.setSelectedItem(o);
	}

	public static void main(String[] args) {

	}
}
