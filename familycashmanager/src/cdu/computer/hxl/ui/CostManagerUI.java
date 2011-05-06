package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.UIManager;

/**
 * 支出记录管理界面
 * 
 * @author hxl
 * 
 */
public class CostManagerUI extends BaseJPanel {

	private static final long serialVersionUID = -9039061244691608492L;
	private JTextField textField;
	private JTextField textField_1;
	private JTable costDataTable;

	public CostManagerUI() {

	}

	@Override
	protected void init() {
		setLayout(new BorderLayout(0, 0));

		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.BLACK);
		add(searchPanel, BorderLayout.NORTH);

		JLabel label = new JLabel("\u91D1\u989D\uFF1A");
		searchPanel.add(label);

		textField = new JTextField();
		searchPanel.add(textField);
		textField.setColumns(10);

		JLabel label_1 = new JLabel("\u65F6\u95F4\uFF1A");
		searchPanel.add(label_1);

		textField_1 = new JTextField();
		searchPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JButton searchButton = new JButton("\u67E5\u8BE2");
		searchPanel.add(searchButton);

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout(0, 0));

		costDataTable = new JTable();
		costDataTable.setSelectionBackground(new Color(240,188,66));
		costDataTable.setSelectionForeground(Color.WHITE);
		costDataTable.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"\u5E8F\u5217\u53F7", "\u91D1\u989D", "\u7528\u5904", "\u6765\u6E90", "\u65F6\u95F4"
			}
		));
		tablePanel.add(costDataTable.getTableHeader(), BorderLayout.NORTH);
		tablePanel.add(costDataTable, BorderLayout.CENTER);
		add(tablePanel, BorderLayout.CENTER);
	}

}
