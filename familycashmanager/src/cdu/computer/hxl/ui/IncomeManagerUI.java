package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.IncomeService;

public class IncomeManagerUI extends BaseJPanel {

	private static final IncomeService inService = (IncomeService) ObjectFactory
			.getInstance("incomeService");

	private JTextField textField = null;
	private JTextField textField_1 = null;
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

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		panel.add(label_1);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		JButton button = new JButton("\u67E5\u8BE2");
		panel.add(button);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable() {

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

	public void loadData() {

		final DefaultTableModel model = (DefaultTableModel) getTable()
				.getModel();
		data = inService.loadIncomeRecord(null);
		int sum = data.length;
		for (int i = 0; i < sum; i++) {
			model.addRow(data[i]);
		}
	}

	public void reloadData() {

		DefaultTableModel dtm = (DefaultTableModel) table.getModel();

		// int count = dtm.getRowCount();
		// // System.out.println(count + "@");
		// for (int i = 0; i < count; i++) {
		// dtm.removeRow(0);
		// }
		dtm.getDataVector().removeAllElements();

		// data = inService.loadIncomeRecord(null);
		// int len = data.length;
		// for (int i = 0; i < len; i++) {
		// dtm.addRow(data[i]);
		// }
		this.loadData();
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
