package cdu.computer.hxl.factory;

import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

/**
 * 封装与菜单有关的一些操作
 * 
 * @author hxl
 * @date 2011-03-17
 * 
 */
public class MenuFactory {
	public static JMenu createMenu(String menuName, String[] items,
			ActionListener[] listeners) {
		JMenu menu = new JMenu(menuName);
		for (int i = 0; i < items.length; i++) {
			JMenuItem item = new JMenuItem(items[i]);
			if (listeners[i] != null)
				item.addActionListener(listeners[i]);
			menu.add(item);
		}
		return menu;
	}

	public static PopupMenu createPopupMenu(String title, String[] items,
			ActionListener[] listeners) {
		PopupMenu popupMenu = new PopupMenu(title);
		for (int i = 0; i < items.length; i++) {
			MenuItem m = new MenuItem(items[i]);
			m.addActionListener(listeners[i]);
			popupMenu.add(m);
		}
		return popupMenu;
	}
}
