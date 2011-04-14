package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.KeyStroke;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

import cdu.computer.hxl.factory.MenuFactory;
import cdu.computer.hxl.util.Constants;
import cdu.computer.hxl.util.Resource;

/**
 * 
 * @author hxl
 * @date 2011-03-16
 */
public class MainUI {
	private BaseJFrame mainFrame = null;

	private BaseJPanel top = new TopPanel();

	private BaseJPanel left = new LeftPanel();

	private CenterPanel center = new CenterPanel();

	private JMenuBar bar = new JMenuBar();

	private JMenu startMenu = MenuFactory.createMenu("开始", new String[] { "保存",
			"退出" }, new ActionListener[] { null, null });

	private JMenu editMenu = MenuFactory.createMenu("编辑", new String[] { "添加",
			"修改", "删除" }, new ActionListener[] { null, null, null });

	private JMenu helpMenu = MenuFactory.createMenu("帮助", new String[] { "说明",
			"作者" }, new ActionListener[] { new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			new BaseJWindow(mainFrame) {
				protected void init() {
					super.init();
					this.setWindowSize(300, 200);

					this.setVisible(true);
				}
			};
		}

	}, null });

	public MainUI() {

	}

	public MainUI(BaseJFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void initUI() {

		/*
		 * 垂直横条
		 */
		JPanel vertical = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();

				GradientPaint paint = new GradientPaint(0, 0, new Color(90,
						120, 90), this.getWidth(), this.getHeight(), new Color(
						10, 90, 10));
				g2.setPaint(paint);
				g2.fillRect(0, 0, 3, this.getHeight());
				g2.dispose();
			}

		};

		/*
		 * 水平横条
		 */
		JPanel horizontal = new JPanel() {

			@Override
			protected void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g.create();

				GradientPaint paint = new GradientPaint(0, 0, new Color(90,
						120, 90), this.getWidth(), this.getHeight(), new Color(
						10, 90, 10));
				g2.setPaint(paint);
				g2.fillRect(0, 0, this.getWidth(), 3);
				g2.dispose();
			}

		};

		mainFrame.setFrameSize(Constants.MAIN_WIDTH, Constants.MAIN_HEIGHT)
				.setFrameResizable(true).setFrameCenter();

		mainFrame.setMinimumSize(new Dimension(600, 480));

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		mainFrame.setLayout(gbl);

		bar.add(startMenu);
		bar.add(editMenu);
		bar.add(helpMenu);

		mainFrame.setJMenuBar(bar);

		gbc.fill = GridBagConstraints.BOTH;

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(top, gbc);
		horizontal.setPreferredSize(new Dimension(100, 3));
		gbl.setConstraints(horizontal, gbc);

		gbc.weightx = 0;
		gbc.weighty = 1.0;
		gbc.gridheight = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 1;

		vertical.setPreferredSize(new Dimension(3, 100));
		gbl.setConstraints(left, gbc);
		gbl.setConstraints(vertical, gbc);

		gbc.weightx = 1.0;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbl.setConstraints(center, gbc);

		mainFrame.add(top);
		mainFrame.add(horizontal);
		mainFrame.add(left);
		mainFrame.add(vertical);
		mainFrame.add(center);
	}

	/**
	 * 顶部菜单栏
	 * 
	 * @author hxl
	 * 
	 */
	private class TopPanel extends BaseJPanel {

		private JPanel leftToolPane = null;
		private JPanel rightToolPane = null;

		private BaseJButton newCreateBtn = null;
		private BaseJButton saveBtn = null;
		private BaseJButton modifyBtn = null;
		private BaseJButton delBtn = null;
		private BaseJButton copyBtn = null;
		private BaseJButton cutBtn = null;
		private BaseJButton pasteBtn = null;

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g.create();
			g2.setColor(new Color(96, 96, 96));
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());

			GradientPaint paint = new GradientPaint(0, 0,
					new Color(96, 96, 96), 0, this.getHeight(), new Color(10,
							10, 10));

			g2.setPaint(paint);
			g2.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2.dispose();
		}

		@Override
		protected void init() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			// this.setBackground(Color.BLACK);

			newCreateBtn = new ToolButton("新建", new ImageIcon(
					Resource.getResourceURL("images/tbnew.png")), this);

			saveBtn = new ToolButton("保存", new ImageIcon(
					Resource.getResourceURL("images/tbsave.png")), this);

			modifyBtn = new ToolButton("修改", new ImageIcon(
					Resource.getResourceURL("images/tbmodify.png")), this);

			delBtn = new ToolButton("删除", new ImageIcon(
					Resource.getResourceURL("images/tbdel.png")), this);

			copyBtn = new ToolButton("复制", new ImageIcon(
					Resource.getResourceURL("images/tbcopy.png")), this);

			cutBtn = new ToolButton("剪贴", new ImageIcon(
					Resource.getResourceURL("images/tbcut.png")), this);

			pasteBtn = new ToolButton("粘贴", new ImageIcon(
					Resource.getResourceURL("images/tbpaste.png")), this);

		}

		private class ToolButton extends BaseJButton {
			private static final int TOOLBUTTON_WIDTH = 50;
			private static final int TOOLBUTTON_HEIGHT = 50;

			public ToolButton(String title) {
				this(title, null, null);
			}

			public ToolButton(String title, Container container) {
				this(title, null, container);
			}

			public ToolButton(String title, Icon icon, Container container) {
				super(title, icon, container);
			}

			@Override
			protected void paintComponent(Graphics g) {

				Graphics2D g2 = (Graphics2D) g.create();
				GradientPaint paint = new GradientPaint(0, 0, new Color(96, 96,
						96), 0, this.getHeight(), new Color(10, 10, 10));

				g2.setPaint(paint);
				g2.fillRect(0, 0, this.getWidth(), this.getHeight());

				// g2.setColor(Color.WHITE);
				// g2.drawString(this.getText(), 20, 20);
				g2.dispose();
				this.setOpaque(false);

				super.paintComponent(g);
			}

			@Override
			protected void init() {
				super.init();
				// this.setOpaque(true);
				this.setBackground(Color.BLACK);
				this.setPreferredSize(new Dimension(TOOLBUTTON_WIDTH,
						TOOLBUTTON_HEIGHT));
				this.setVerticalTextPosition(SwingConstants.BOTTOM);
				this.setHorizontalTextPosition(SwingConstants.CENTER);

				this.addMouseListener(new MouseAdapter() {

					@Override
					public void mouseEntered(MouseEvent e) {
						// setBackgroundColor(Color.GRAY);
					}

				});
			}

			private void setBackgroundColor(Color c) {
				this.setBackground(c);
			}
		}

	}

	private class LeftPanel extends BaseJPanel {
		private final Map<String, Object> ListHandlerInstanceMap = new HashMap<String, Object>();

		@Override
		protected void init() {

			String[][] leftMenu = new String[][] { { "//资金支出管理", "/" },
					{ "新增支出记录", "images/listselect.png" },
					{ "管理支出记录", "images/listselect.png" },
					{ "添加支出类别", "images/listselect.png" },
					{ "支出类别管理", "images/listselect.png" },
					{ "支出分布图", "images/listselect.png" }, { "//资金收入管理", "/" },
					{ "新增收入记录", "images/listselect.png" },
					{ "收入记录管理", "images/listselect.png" },
					{ "添加收入类别", "images/listselect.png" },
					{ "收入类别管理", "images/listselect.png" },
					{ "收入分布图", "images/listselect.png" }, { "//资金收支统计", "/" },
					{ "收支平衡图表", "images/listselect.png" } };

			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			this.setPreferredSize(new Dimension(210, 0));
			this.setBackground(new Color(52, 55, 59));

			BaseJList leftList = new BaseJList();

			DefaultListModel model = new DefaultListModel();
			leftList.setModel(model);

			leftList.setFixedCellHeight(30);
			leftList.setFixedCellWidth(210);

			for (int i = 0; i < leftMenu.length; i++) {
				String[] s = leftMenu[i];
				if (s[0].indexOf("//") != -1) {

					model.addElement((new BaseJList.ListData(s[0].substring(2),
							null, true)));
				} else {
					model.addElement((new BaseJList.ListData(s[0],
							new ImageIcon(Resource.getResourceURL(s[1])), false)));
				}
			}
			leftList.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent e) {
					if (!e.getValueIsAdjusting()) {
						BaseJList source = (BaseJList) e.getSource();
						int index = source.getSelectedIndex();
						if (index == 1) {
							new NewCostRecordUI(mainFrame);
						} else if (index == 7) {
							new NewIncomeRecordUI(mainFrame);
						}else if(index == 3){
							new NewCostCategoryUI(mainFrame);
						}
					}

				}
			});
			this.add(leftList);
		}
	}

	private class CenterPanel extends BaseJPanel {

		private static final long serialVersionUID = -1862161663716703489L;
		private BaseJTabbedPane tab = null;

		@Override
		protected void init() {
			setLayout(new BorderLayout());
			tab = new BaseJTabbedPane(this);
		}

		public void addTabComponent(String title, Component component) {
			tab.addTabComponent(title, component);
		}

		/**
		 * @return the tab
		 */
		public BaseJTabbedPane getTab() {
			return tab;
		}

		/**
		 * @param BaseJTabbedPane
		 */
		public void setTab(BaseJTabbedPane tab) {
			this.tab = tab;
		}

	}
}
