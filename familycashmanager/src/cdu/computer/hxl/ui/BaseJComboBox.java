package cdu.computer.hxl.ui;

import java.awt.Component;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

public class BaseJComboBox extends JComboBox {

	public BaseJComboBox() {
		setRenderer(new MyComboBoxRenderer());
	}

	private class MyComboBoxRenderer extends JLabel implements ListCellRenderer {

		private static final long serialVersionUID = 1L;

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			// System.out.println(value);
			if (value instanceof Map) {
				@SuppressWarnings("unchecked")
				HashMap<String, Object> vm = (HashMap<String, Object>) value;

				setText((String) vm.get("name"));
			} else if (value != null) {
				setText(value.toString());
			}
			return this;
		}
	}
}
