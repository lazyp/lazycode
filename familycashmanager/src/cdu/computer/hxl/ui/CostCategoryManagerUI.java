package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.CostService;
import cdu.computer.hxl.util.ThreadExecutorUtils;

public class CostCategoryManagerUI extends BaseJPanel {

	private final CostService cService = (CostService) ObjectFactory
			.getInstance("costService");
	private JTable table = null;
	private Object[][] dataObj = null;

	public CostCategoryManagerUI() {
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"序列号", "名称", "备注", "时间" }));

		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(table);

		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_1.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	protected void init() {
		setLayout(new BorderLayout(0, 0));
		/*
		 * JPanel panel = new JPanel(); add(panel, BorderLayout.NORTH);
		 * 
		 * JLabel label = new JLabel("New label"); panel.add(label);
		 * 
		 * textField = new JTextField(); panel.add(textField);
		 * textField.setColumns(10);
		 * 
		 * JLabel label_1 = new JLabel("New label"); panel.add(label_1);
		 * 
		 * textField_1 = new JTextField(); panel.add(textField_1);
		 * textField_1.setColumns(10);
		 */

	}

	private void loadData(List<Map<String, Object>> dataList) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();

		int size = dataList.size();
		dataObj = new Object[size][4];
		for (int i = 0; i < size; i++) {
			Map<String, Object> one = dataList.get(i);
			Object[] rowData = new Object[4];
			rowData[0] = one.get("rowid");
			dataObj[i][0] = rowData[0];
			rowData[1] = one.get("categoryname");
			dataObj[i][1] = rowData[1];
			rowData[2] = one.get("remark");
			dataObj[i][2] = rowData[2];
			rowData[3] = one.get("datetime");
			dataObj[i][3] = rowData[3];
			model.addRow(rowData);
		}
	}

	public void loadData() {
		// new ThreadExecutorUtils() {
		//
		// @Override
		// protected void task() {
		List<Map<String, Object>> data = cService.loadCostCategoryForList(null);
		this.loadData(data);
		// }
		// }.exec();
	}

	public void reloadData() {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		// int count = dtm.getRowCount();
		// // System.out.println(count+"@");
		// for (int i = 0; i < count; i++) {
		// dtm.removeRow(0);
		// }

		dtm.getDataVector().removeAllElements();

		List<Map<String, Object>> data = cService.loadCostCategoryForList(null);
		this.loadData(data);

	}

	public Object[] getSelectedRowData() {
		int rownum = table.getSelectedRow();
		// System.out.println(rownum);
		if (rownum == -1)
			return null;
		return dataObj[rownum];
	}
}
