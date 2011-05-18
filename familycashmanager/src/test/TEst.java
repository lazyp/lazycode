package test;

import javax.swing.JOptionPane;

public class TEst {
	public static void main(String[] args) {
		int result = JOptionPane.showConfirmDialog(null,
				"注意：会同时删除对应的消费记录,请谨慎操作!", "警告", JOptionPane.WARNING_MESSAGE);
		System.out.println(result);
	}
}
