package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;

/**
 * 支出记录管理界面
 * 
 * @author hxl
 * 
 */
public class CostManagerUI extends BaseJPanel {

	private static final long serialVersionUID = -9039061244691608492L;
	private final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");

	private JTextField amountTextField = null;
	private JTextField timeTextField = null;
	private JTable costDataTable = null;
	private JTextField useTextField = null;

	private Object[][] data = null;

	public CostManagerUI() {
		setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.BLACK);
		add(searchPanel, BorderLayout.NORTH);

		JLabel label_2 = new JLabel("用处:");
		searchPanel.add(label_2);

		useTextField = new JTextField();
		searchPanel.add(useTextField);
		useTextField.setColumns(10);

		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		searchPanel.add(label);

		amountTextField = new JTextField();
		searchPanel.add(amountTextField);
		amountTextField.setColumns(10);

		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		searchPanel.add(label_1);

		timeTextField = new JTextField();
		searchPanel.add(timeTextField);
		timeTextField.setColumns(10);

		JButton searchButton = new JButton("查询");

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String amount = amountTextField.getText();
				String use = useTextField.getText();
				String time = timeTextField.getText();

				Map<String, Object> whereMap = new HashMap<String, Object>();

				if (amount != null && !amount.trim().equals("")) {
					whereMap.put("amount", Integer.parseInt(amount));
				}
				if (time != null && !time.trim().equals("")) {
					whereMap.put("date", time);
				}
				if (use != null && !use.trim().equals("")) {
					Map<String, Object> whereDataMap = new HashMap<String, Object>();
					whereDataMap.put("categoryname like ", use);

					List<Map<String, Object>> result = cService
							.loadCostCategoryForList(whereDataMap);
					String useids = "";

					for (int i = 0; i < result.size(); i++) {
						useids += result.get(i).get("rowid") + " , ";
					}

					whereMap.put("useid in ",
							useids.substring(0, useids.trim().length() - 1));
				}
				reloadData(whereMap);
				costDataTable.setForeground(Color.RED);
			}
		});

		searchPanel.add(searchButton);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));

		costDataTable = new JTable() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		// costDataTable.setSelectionBackground(new Color(240, 188, 66));
		// costDataTable.setSelectionForeground(Color.WHITE);
		costDataTable.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "序列号", "金额", "用途", "来源", "备注", "时间" }));

		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(costDataTable);

		tablePanel.add(costDataTable.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(scrollPane, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
	}

	@Override
	protected void init() {

	}

	public void loadData(Map<String, Object> whereMap) {
		DefaultTableModel dtm = (DefaultTableModel) costDataTable.getModel();
		data = cService.loadCostForObject(whereMap);
		int len = data.length;
		for (int i = 0; i < len; i++) {
			dtm.addRow(data[i]);
		}
	}

	public void reloadData(Map<String, Object> whereMap) {

		DefaultTableModel dtm = (DefaultTableModel) costDataTable.getModel();
		dtm.getDataVector().removeAllElements();
		loadData(whereMap);
	}

	public void removeRow(int rowid) {
		cService.deleteCost(rowid);
	}

	public Object[] getSelectedRowData() {
		int rownum = costDataTable.getSelectedRow();
		if (rownum == -1)
			return null;
		return data[rownum];

	}
}
