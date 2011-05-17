package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
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
	private JTextField textField = null;
	private JTextField textField_1 = null;
	private JTable costDataTable = null;
	private Object[][] data = null;

	public CostManagerUI() {
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

		costDataTable = new JTable() {

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

	public void loadData() {
		DefaultTableModel dtm = (DefaultTableModel) costDataTable.getModel();
		data = cService.loadCostForObject(null);
		int len = data.length;
		for (int i = 0; i < len; i++) {
			dtm.addRow(data[i]);
		}
	}

	public void reloadData() {

		DefaultTableModel dtm = (DefaultTableModel) costDataTable.getModel();

		// int count = dtm.getRowCount();
		// // System.out.println(count+"@");
		// for (int i = 0; i < count; i++) {
		// dtm.removeRow(0);
		// }
		dtm.getDataVector().removeAllElements();

		data = cService.loadCostForObject(null);
		int len = data.length;
		for (int i = 0; i < len; i++) {
			dtm.addRow(data[i]);
		}
	}

	public void removeRow(int rowid) {
		cService.deleteCost(rowid);
	}

	public Object[] getSelectedRowData() {
		int rownum = costDataTable.getSelectedRow();
		// System.out.println(rownum);
		if (rownum == -1)
			return null;
		return data[rownum];

	}
}
