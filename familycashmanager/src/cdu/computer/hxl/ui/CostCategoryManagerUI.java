package cdu.computer.hxl.ui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CostCategoryManagerUI extends BaseJPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	public CostCategoryManagerUI() {

	}

	@Override
	protected void init() {
		setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		JLabel label = new JLabel("New label");
		panel.add(label);

		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("New label");
		panel.add(label_1);

		textField_1 = new JTextField();
		panel.add(textField_1);
		textField_1.setColumns(10);

		JPanel panel_1 = new JPanel();
		add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {
				"序列号", "名称", "时间" }));
		panel_1.add(table.getTableHeader() , BorderLayout.NORTH);
		panel_1.add(table, BorderLayout.CENTER);
	}
}
