package cdu.computer.hxl.ui;

import java.awt.Dialog;
import java.awt.Frame;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * 
 * @author hxl
 * @date 2010-03-26
 */
public abstract class BaseJDialog extends JDialog {

	public BaseJDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		initUI();
	}

	abstract protected void initUI();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
