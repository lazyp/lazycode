package test;

import java.util.Calendar;

import javax.swing.JOptionPane;

public class TEst {
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, 1);
		c.set(Calendar.DATE, 1);
		System.out.println(c.get(Calendar.MONTH));
	}
}
