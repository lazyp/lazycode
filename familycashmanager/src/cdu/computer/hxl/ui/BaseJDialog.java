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
	private BaseJFrame owner = null;

	public BaseJDialog(BaseJFrame owner, String title, boolean model) {
		super(owner, title, model);
		this.owner = owner;
	}

	public void showDialog() {
		setVisible(true);
	}

	protected void setCenterForOwner() {
		setLocationRelativeTo(owner);
	}

	protected void initUI() {
		this.setCenterForOwner();
	}

	/**
	 * @return the owner
	 */
	public BaseJFrame getOwner() {
		return owner;
	}

	/**
	 * @param owner
	 *            the owner to set
	 */
	public void setOwner(BaseJFrame owner) {
		this.owner = owner;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
