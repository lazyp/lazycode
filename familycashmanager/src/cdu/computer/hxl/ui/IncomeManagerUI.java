package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;

public class IncomeManagerUI extends BaseJPanel {

	private static final long serialVersionUID = -4654370598634175173L;

	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");

	private JTextField sourceTextField;
	private JTextField timeTextField;
	private JTable table = null;

	private Object[][] data = null;

	/**
	 * Create the panel.
	 */
	public IncomeManagerUI() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("\u6765\u6E90\uFF1A");
		panel.add(label);

		sourceTextField = new JTextField();
		panel.add(sourceTextField);
		sourceTextField.setColumns(10);

		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		panel.add(label_1);

		timeTextField = new JTextField();
		panel.add(timeTextField);
		timeTextField.setColumns(10);

		JButton searchButton = new JButton("\u67E5\u8BE2");

		searchButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String source = sourceTextField.getText();
				String time = timeTextField.getText();

				Map<String, Object> whereMap = new HashMap<String, Object>();

				if (time != null && !time.trim().equals("")) {
					whereMap.put("date", time);
				}

				if (source != null && !source.trim().equals("")) {
					Map<String, Object> whereDataMap = new HashMap<String, Object>();
					whereDataMap.put("categoryname", source);
					List<Map<String, Object>> result = inService
							.loadIncomeCategoryForList(whereDataMap);
					if (result != null && result.size() > 0) {
						whereMap.put("sourceid",
								(Integer) result.get(0).get("rowid"));
					}
				}
				table.setForeground(Color.RED);
				reloadData(whereMap);
			}
		});

		panel.add(searchButton);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable() {

			private static final long serialVersionUID = -2539742588095119460L;

			@Override
			public boolean isCellEditable(int row, int column) {

				return false;
			}

		};
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"\u5E8F\u5217\u53F7", "\u91D1\u989D",
				"\u6536\u5165\u6765\u6E90", "\u5B58\u5165", "±¸×¢",
				"\u65F6\u95F4" }));
		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(table);
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_1.add(scrollPane, BorderLayout.CENTER);

	}

	@Override
	protected void init() {

	}

	/**
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * @param table
	 *            the table to set
	 */
	public void setTable(JTable table) {
		this.table = table;
	}

	public Object[] getSelectedData() {
		int rownum = table.getSelectedRow();
		// System.out.println(rownum);
		if (rownum == -1)
			return null;
		return data[rownum];
	}

	public void loadData(Map<String, Object> whereMap) {

		final DefaultTableModel model = (DefaultTableModel) getTable()
				.getModel();
		data = inService.loadIncomeRecord(whereMap);
		int sum = data.length;
		for (int i = 0; i < sum; i++) {
			model.addRow(data[i]);
		}
	}

	public void reloadData(Map<String, Object> whereMap) {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		dtm.getDataVector().removeAllElements();

		this.loadData(whereMap);
	}

	public boolean isSelected() {
		return table.getSelectedRow() == -1 ? false : true;
	}

	public void removeRow() {
		int rownum = table.getSelectedRow();
		int rowid = (Integer) data[rownum][0];
		inService.deleteIncomeRecord(rowid);
	}

}
