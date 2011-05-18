package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;

public class IncomeCategoryManagerUI extends BaseJPanel {

	private static final long serialVersionUID = -2623766881891865704L;

	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");;

	private JTable table = null;
	private Object[][] data = null;

	/**
	 * Create the panel.
	 */
	public IncomeCategoryManagerUI() {
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
		 * 
		 * JButton button = new JButton("\u67E5\u8BE2"); panel.add(button);
		 */
		JPanel panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"\u5E8F\u5217\u53F7", "\u7C7B\u522B\u540D\u79F0", "±¸×¢",
				"\u65F6\u95F4" }));

		JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(122, 211, 183, 52);
		scrollPane.setViewportView(table);

		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
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

	public void loadData() {

		DefaultTableModel dtm = (DefaultTableModel) getTable().getModel();
		data = inService.loadIncomeCategory(null);
		int len = data.length;
		for (int i = 0; i < len; i++) {
			dtm.addRow(data[i]);
		}
	}

	public void reloadData() {
		DefaultTableModel dtm = (DefaultTableModel) getTable().getModel();
		dtm.getDataVector().removeAllElements();
		loadData();
	}

	public boolean isSelected() {
		return table.getSelectedRow() == -1 ? false : true;
	}

	public Object[] getSelectedData() {
		int row = table.getSelectedRow();
		if (row != -1)
			return data[row];
		return null;
	}

	public void removeRow() {
		int row = table.getSelectedRow();
		if (row == -1)
			return;
		inService.deleteIncomeCategory((Integer) data[row][0]);
		// if(row != -1)

	}

}
