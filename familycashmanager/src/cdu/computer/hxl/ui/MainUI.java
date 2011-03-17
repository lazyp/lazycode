package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import cdu.computer.hxl.util.Constants;

/**
 * 
 * @author hxl
 * @date 2011-03-16
 */
public class MainUI {
	private BaseJFrame mainFrame = null;

	private JPanel top = new JPanel();

	private JPanel left = new JPanel();

	private JPanel center = new JPanel();

	private JMenuBar bar = new JMenuBar();

	private JMenu startMenu = new JMenu("¿ªÊ¼");

	private JMenuItem saveItem = new JMenuItem("±£´æ", new ImageIcon(
			Constants.SAVE_IMAGE_PATH));

	private JMenuItem exitItem = new JMenuItem("ÍË³ö");

	private JMenu editMenu = new JMenu("±à¼­");

	private JMenuItem copyItem = new JMenuItem("¸´ÖÆ");

	private JMenuItem clipItem = new JMenuItem("¼ôÌù");

	private JMenuItem pasteItem = new JMenuItem("Õ³Ìù");

	private JMenu helpMenu = new JMenu("°ïÖú");

	private JMenuItem helpItem = new JMenuItem("¹ØÓÚ");

	public MainUI() {

	}

	public MainUI(BaseJFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void initUI() {

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		mainFrame.setLayout(gbl);

		mainFrame.setFrameSize(Constants.MAIN_WIDTH, Constants.MAIN_HEIGHT)
				.setFrameResizable(true).setFrameCenter();
		bar.add(startMenu);

		startMenu.add(saveItem);
		startMenu.add(exitItem);

		editMenu.add(copyItem);
		editMenu.add(clipItem);
		editMenu.add(pasteItem);

		bar.add(editMenu);

		mainFrame.setJMenuBar(bar);

		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(top, gbc);

		gbc.weightx = 0;
		gbc.weighty = 1.0;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 1;

		gbl.setConstraints(left, gbc);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(center, gbc);

		// ////²âÊÔ////////

		top.add(new JButton("testestset"));
		top.setBorder(BorderFactory.createLineBorder(Color.red));
		left.setBorder(BorderFactory.createLineBorder(Color.red));
		left.add(new JButton("dadsdsadas"));
		center.setBorder(BorderFactory.createLineBorder(Color.GREEN));
		// ///½áÊø////////
		top.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 5));
		left.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		mainFrame.add(top);
		mainFrame.add(left);
		mainFrame.add(center);
	}
}
