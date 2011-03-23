package cdu.computer.hxl.ui;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 * 封装与菜单有关的一些操作
 * 
 * @author hxl
 * @date 2011-03-17
 * 
 */
public class MenuFactory {
	public static JMenu createMenu(String menuName, String[] items) {
		JMenu menu = new JMenu(menuName);
		for (int i = 0; i < items.length; i++) {
			menu.add(new JMenuItem(items[i]));
		}
		return menu;
	}
}
