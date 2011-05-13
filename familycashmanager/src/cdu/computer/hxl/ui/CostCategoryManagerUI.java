package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
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

	public CostCategoryManagerUI() {
		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"序列号", "名称", "备注", "时间" }));
		panel_1.add(table.getTableHeader(), BorderLayout.NORTH);
		panel_1.add(table, BorderLayout.CENTER);
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

	public void loadData() {
		// new ThreadExecutorUtils() {
		//
		// @Override
		// protected void task() {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		List<Map<String, Object>> data = cService.loadCostCategoryForList(null);
		int size = data.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> one = data.get(i);
			Object[] rowData = new Object[4];
			rowData[0] = one.get("rowid");
			rowData[1] = one.get("categoryname");
			rowData[2] = one.get("remark");
			rowData[3] = one.get("datetime");
			model.addRow(rowData);
		}
		// }
		// }.exec();
	}
}
