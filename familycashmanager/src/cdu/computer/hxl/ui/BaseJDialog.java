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

	public BaseJDialog(BaseJFrame owner, String title, boolean model) {
		super(owner, title, model);
		initUI();
		if (owner != null) {
			/*
			 * 默认显示在父窗口的中间位置
			 */
			setLocation(((owner.getWidth() - this.getWidth()) >> 1),
					((owner.getHeight() - this.getHeight()) >> 1));
		}
	
		this.setLocationRelativeTo(owner);
		//this.setVisible(true);
	
	}
    public void showDialog(){
    	setVisible(true);
    }
	abstract protected void initUI();

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
