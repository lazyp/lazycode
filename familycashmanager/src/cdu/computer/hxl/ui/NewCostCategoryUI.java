package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.util.GetSystemTime;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class NewCostCategoryUI extends BaseJDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPanel = null;
	private BaseJFrame owner = null;
	private JTextField nameTextField = null;
	private JTextArea remarkTextArea = null;
	private int rowid = -1;

	/**
	 * Create the dialog.
	 */
	public NewCostCategoryUI(BaseJFrame owner) {
		super(owner, "新增支出类别", true);
		this.owner = owner;
		initUI();
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

		remarkTextArea = new JTextArea();
		remarkTextArea.setLineWrap(true);
		remarkTextArea.setColumns(10);
		remarkTextArea.setRows(2);
		remarkTextArea.setBounds(127, 108, 153, 42);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(127, 108, 153, 42);
		scrollPane.setViewportView(remarkTextArea);
		getContentPane().add(scrollPane);

		JButton submitbnt = new JButton("保存");
		submitbnt.setBounds(127, 182, 73, 23);

		submitbnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				new ThreadExecutorUtils() {

					@Override
					protected void task() {

						String name = nameTextField.getText();
						String remark = remarkTextArea.getText();
						Map<String, Object> data = new HashMap<String, Object>();
						data.put("rowid", rowid);
						data.put("categoryname", name);
						data.put("remark", remark);
						data.put("datetime", GetSystemTime.get());

						CostService cService = (CostService) ObjectFactory
								.getInstance("costService");

						owner.setStatusText("正在保存中...");

						cService.addCostCategory(data);

						owner.setStatusText("保存成功");
					}
				}.exec();
			}
		});

		getContentPane().add(submitbnt);

		JButton clearbnt = new JButton("\u6E05\u7A7A");
		clearbnt.setBounds(207, 182, 73, 23);

		clearbnt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nameTextField.setText("");
				remarkTextArea.setText("");
			}
		});

		getContentPane().add(clearbnt);

		// setLocationRelativeTo(owner);
		// this.setVisible(true);
		super.initUI();
	}

	public void setNameText(String nameText) {
		this.nameTextField.setText(nameText);
	}

	public void setRemarkText(String remarkText) {
		this.remarkTextArea.setText(remarkText);
	}

	public void setRowid(int rowid) {
		this.rowid = rowid;
	}
}
