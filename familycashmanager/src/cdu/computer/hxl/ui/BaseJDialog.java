package cdu.computer.hxl.ui;

import javax.swing.JDialog;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
/**
 * 
 * @author hxl
 * @date 2010-03-26
 */
public class BaseJDialog extends JDialog {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		BaseJDialog bjd = new BaseJDialog();
		bjd.setTitle("зїеп");
		bjd.setSize(200, 100);
		bjd.setVisible(true);

	}

}
