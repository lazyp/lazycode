package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class IncomeCategoryManagerUI extends BaseJPanel {

	private JTable table = null;

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
				"\u5E8F\u5217\u53F7", "\u7C7B\u522B\u540D\u79F0","±¸×¢",
				"\u65F6\u95F4" }));
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
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

}
