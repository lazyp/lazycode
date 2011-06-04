package cdu.computer.hxl.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;

import cdu.computer.hxl.factory.MenuFactory;
import cdu.computer.hxl.util.Constants;
import cdu.computer.hxl.util.FileUtils;
import cdu.computer.hxl.util.Resource;
import cdu.computer.hxl.util.ThreadExecutorUtils;

/**
 * 
 * @author hxl
 * @date 2011-03-16
 */
public class MainUI {
	private BaseJFrame mainFrame = null;

	private final JPanel mainPanel = new JPanel();

	private final BaseJPanel top = new TopPanel();

	private final LeftPanel left = new LeftPanel();

	private final CenterPanel center = new CenterPanel();

	private JMenuBar bar = new JMenuBar();

	private JMenu startMenu = MenuFactory.createMenu("开始",
			new String[] { "退出" }, new ActionListener[] { new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			} });

	// private JMenu editMenu = MenuFactory.createMenu("编辑", new String[] {
	// "添加",
	// "修改", "删除" }, new ActionListener[] { null, null, null });

	private JMenu helpMenu = MenuFactory.createMenu("帮助", new String[] { "说明",
			"作者" }, new ActionListener[] { new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new SystemIntroduceUI(null, "系统说明").showDialog();
		}
	}, new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			new AboutAuthorDialogUI(null, "作者介绍").showDialog();
		}
	} });

	public MainUI() {

	}

	public MainUI(BaseJFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void initUI() {

		mainFrame.setFrameSize(Constants.MAIN_WIDTH, Constants.MAIN_HEIGHT)
				.setFrameResizable(true).setFrameCenter().showStatus();

		mainFrame.setMinimumSize(new Dimension(700, 600));

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

		GridBagConstraints gbc = new GridBagConstraints();
		GridBagLayout gbl = new GridBagLayout();
		// mainFrame.setLayout(gbl);
		mainPanel.setLayout(gbl);

		bar.add(startMenu);
		// bar.add(editMenu);
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

		// gbc.weighty = 0;
		// gbc.gridheight = 1;
		// gbl.setConstraints(statusPanel, gbc);

		/*
		 * mainFrame.add(top); mainFrame.add(horizontal); mainFrame.add(left);
		 * mainFrame.add(vertical); mainFrame.add(center);
		 * mainFrame.add(statusPanel);
		 */
		mainPanel.add(top);
		mainPanel.add(horizontal);
		mainPanel.add(left);
		mainPanel.add(vertical);
		mainPanel.add(center);

		mainFrame.add(mainPanel, BorderLayout.CENTER);

	}

	// public void setStatusText(String text) {
	// this.statusPanel.chanageStatusText(text);
	// }

	/**
	 * 顶部菜单栏
	 * 
	 * @author hxl
	 * 
	 */
	private class TopPanel extends BaseJPanel {

		private static final long serialVersionUID = 2469742777806648489L;
		private BaseJButton newCreateBtn = null;
		// private BaseJButton saveBtn = null;
		private BaseJButton modifyBtn = null;
		private BaseJButton delBtn = null;

		private BaseJButton refresh = null;
		private BaseJButton backup = null;
		private BaseJButton restore = null;
		// private BaseJButton copyBtn = null;
		// private BaseJButton cutBtn = null;
		// private BaseJButton pasteBtn = null;

		private Image image = null;

		public TopPanel() {
			try {
				image = ImageIO.read(Resource
						.getResourceURL("images/toolbg.jpg"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			initUI();
		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);

			Graphics2D g2 = (Graphics2D) g.create();

			g2.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);

			g2.dispose();
		}

		@Override
		protected void init() {

		}

		protected void initUI() {
			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			// this.setBackground(Color.BLACK);

			newCreateBtn = new ToolButton("新建", new ImageIcon(
					Resource.getResourceURL("images/tbnew.png")), this);
			
			newCreateBtn.setMnemonic(KeyEvent.VK_F3);
			newCreateBtn.setToolTipText("快捷键Alt+F3");
			
			newCreateBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					BaseJList leftList = left.getLeftList();
					int index = leftList.getSelectedIndex();
					if (index == 1) {
						new NewCostRecordUI("新增支出记录", mainFrame).showDialog();
					} else if (index == 3) {
						new NewCostCategoryUI("新增支出类别", mainFrame).showDialog();
					} else if (index == 7) {
						new NewIncomeRecordUI("新增收入记录", mainFrame).showDialog();
					} else if (index == 9) {
						new NewIncomeCategoryUI("新增收入类别", mainFrame)
								.showDialog();
					} else {
						JOptionPane.showMessageDialog(mainFrame, "无效选择", "提示",
								JOptionPane.WARNING_MESSAGE);
					}
				}
			});

			// saveBtn = new ToolButton("保存", new ImageIcon(
			// Resource.getResourceURL("images/tbsave.png")), this);

			modifyBtn = new ToolButton("修改", new ImageIcon(
					Resource.getResourceURL("images/tbmodify.png")), this);

			modifyBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String title = center.getSelectedTitle();
					boolean flag = false;
					if (title != null) {
						if (title.trim().equals("支出记录管理")) {
							CostManagerUI cmg = (CostManagerUI) center.getTab()
									.getSelectedComponent();
							Object[] rowData = cmg.getSelectedRowData();
							if (rowData == null)
								flag = true;
							else {
								NewCostRecordUI nru = new NewCostRecordUI(
										"修改支出记录", mainFrame);
								nru.setMoney(String.valueOf(rowData[1]));
								nru.setTime(String.valueOf(rowData[5]));
								nru.setRemarkText(String.valueOf(rowData[4]));
								nru.setRowid((Integer) rowData[0]);
								nru.showDialog();
							}
						} else if (title.trim().equals("支出类别管理")) {
							CostCategoryManagerUI ccmg = (CostCategoryManagerUI) center
									.getTab().getSelectedComponent();
							Object[] rowData = ccmg.getSelectedRowData();
							if (rowData == null)
								flag = true;
							else {
								NewCostCategoryUI ncu = new NewCostCategoryUI(
										"修改支出类别", mainFrame);
								ncu.setRowid((Integer) rowData[0]);
								ncu.setNameText(String.valueOf(rowData[1]));
								ncu.setRemarkText(String.valueOf(rowData[2]));
								ncu.showDialog();
							}
						} else if (title.trim().equals("收入记录管理")) {
							IncomeManagerUI imu = (IncomeManagerUI) center
									.getTab().getSelectedComponent();
							Object[] rowData = imu.getSelectedData();
							if (rowData == null)
								flag = true;
							else {
								NewIncomeRecordUI niru = new NewIncomeRecordUI(
										"修改收入记录", mainFrame);
								niru.setRowid((Integer) rowData[0]);
								niru.setAmount(String.valueOf(rowData[1]));
								niru.setTimeText(String.valueOf(rowData[5]));
								niru.setRemark(String.valueOf(rowData[4]));
								niru.showDialog();
							}

						} else if (title.trim().equals("收入类别管理")) {
							IncomeCategoryManagerUI icmu = (IncomeCategoryManagerUI) center
									.getTab().getSelectedComponent();
							Object[] rowData = icmu.getSelectedData();
							if (rowData == null)
								flag = true;
							else {
								NewIncomeCategoryUI ncu = new NewIncomeCategoryUI(
										"修改收入类别", mainFrame);
								ncu.setRowid((Integer) rowData[0]);
								ncu.setCategoryName(String.valueOf(rowData[1]));
								ncu.setRemark(String.valueOf(rowData[2]));
								ncu.showDialog();
							}

						}
					}
					if (flag)
						JOptionPane.showMessageDialog(mainFrame, "请选择一行数据!",
								"提示", JOptionPane.WARNING_MESSAGE);
				}
			});

			delBtn = new ToolButton("删除", new ImageIcon(
					Resource.getResourceURL("images/tbdel.png")), this);

			delBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String title = center.getSelectedTitle();
					boolean flag = false;
					if (title != null) {
						if (title.trim().equals("支出记录管理")) {
							final CostManagerUI cmg = (CostManagerUI) center
									.getTab().getSelectedComponent();
							Object[] rowData = cmg.getSelectedRowData();
							if (rowData == null)
								flag = true;
							else {
								final int rowid = (Integer) rowData[0];
								new ThreadExecutorUtils() {

									@Override
									protected void task() {
										mainFrame.setStatusText("正在删除记录...");
										cmg.removeRow(rowid);
										mainFrame.setStatusText("删除成功!");
									}
								}.exec();

							}
						} else if (title.trim().equals("支出类别管理")) {
							final CostCategoryManagerUI ccmg = (CostCategoryManagerUI) center
									.getTab().getSelectedComponent();
							if (ccmg.isSelected()) {
								int result = JOptionPane.showConfirmDialog(
										null, "注意：会同时删除对应的消费记录,请谨慎操作!", "警告",
										JOptionPane.WARNING_MESSAGE);
								if (result == 0) {
									new ThreadExecutorUtils() {

										@Override
										protected void task() {
											mainFrame
													.setStatusText("正在删除记录...");
											ccmg.removeRow();
											mainFrame.setStatusText("删除成功!");
										}

									}.exec();
								}
							} else {
								flag = true;
							}

						} else if (title.trim().equals("收入记录管理")) {
							final IncomeManagerUI imu = (IncomeManagerUI) center
									.getTab().getSelectedComponent();
							if (!imu.isSelected())
								flag = true;
							else {
								new ThreadExecutorUtils() {

									@Override
									protected void task() {
										mainFrame.setStatusText("正在删除记录...");
										imu.removeRow();
										mainFrame.setStatusText("删除成功!");
									}
								}.exec();
							}
						} else if (title.trim().equals("收入类别管理")) {
							final IncomeCategoryManagerUI icmu = (IncomeCategoryManagerUI) center
									.getTab().getSelectedComponent();
							if (icmu.isSelected()) {

								int result = JOptionPane.showConfirmDialog(
										null, "注意：会同时删除对应的收入记录,请谨慎操作!", "警告",
										JOptionPane.WARNING_MESSAGE);
								if (result == 0) {

									new ThreadExecutorUtils() {

										@Override
										protected void task() {
											mainFrame
													.setStatusText("正在删除记录...");
											icmu.removeRow();
											mainFrame.setStatusText("删除成功!");
										}
									}.exec();
								}
							} else
								flag = true;
						}
					}
					if (flag)
						JOptionPane.showMessageDialog(mainFrame, "请选择一行数据!",
								"提示", JOptionPane.WARNING_MESSAGE);
				}
			});

			refresh = new ToolButton("刷新", new ImageIcon(
					Resource.getResourceURL("images/tbudpate.png")), this);
			refresh.setToolTipText("快捷键Alt+F5");
			refresh.setMnemonic(KeyEvent.VK_F5);
	

			refresh.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					String title = center.getSelectedTitle();
					if (title != null) {
						if (title.trim().equals("支出记录管理")) {
							final CostManagerUI cmg = (CostManagerUI) center
									.getTab().getSelectedComponent();
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									cmg.getCostDataTable().setForeground(
											Color.WHITE);
									cmg.reloadData(null);
								}
							}.exec();
						} else if (title.trim().equals("支出类别管理")) {
							final CostCategoryManagerUI ccmg = (CostCategoryManagerUI) center
									.getTab().getSelectedComponent();
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									// ccmg.getTable().setForeground(Color.WHITE);
									ccmg.reloadData();
								}
							}.exec();
						} else if (title.trim().equals("收入记录管理")) {
							final IncomeManagerUI imu = (IncomeManagerUI) center
									.getTab().getSelectedComponent();
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									imu.getTable().setForeground(Color.WHITE);
									imu.reloadData(null);
								}
							}.exec();
						} else if (title.trim().equals("收入类别管理")) {
							final IncomeCategoryManagerUI icmu = (IncomeCategoryManagerUI) center
									.getTab().getSelectedComponent();
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									// icmu.getTable().setForeground(Color.WHITE);
									icmu.reloadData();
								}
							}.exec();
						} else if (title.trim().equals("支出分布图")) {
							center.addTabComponent("支出分布图",
									new CostAllocationChartUI());
						} else if (title.trim().equals("收入分布图")) {
							center.addTabComponent("收入分布图",
									new IncomeAllocationChartUI());
						} else if (title.trim().equals("平衡分布图")) {
							center.addTabComponent("平衡分布图",
									new BalanceChartUI());
						}
					}
				}
			});
			backup = new ToolButton("备份", new ImageIcon(
					Resource.getResourceURL("images/data.jpg")), this);
			backup.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					left.backup();

				}
			});
			restore = new ToolButton("还原", new ImageIcon(
					Resource.getResourceURL("images/data.jpg")), this);
			restore.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					left.restore();

				}
			});

			setTopToolButtonEnabled(new boolean[] { false, false, false, false,
					false, false });
			// copyBtn = new ToolButton("复制", new ImageIcon(
			// Resource.getResourceURL("images/tbcopy.png")), this);
			//
			// cutBtn = new ToolButton("剪贴", new ImageIcon(
			// Resource.getResourceURL("images/tbcut.png")), this);
			//
			// pasteBtn = new ToolButton("粘贴", new ImageIcon(
			// Resource.getResourceURL("images/tbpaste.png")), this);

		}

		public void setTopToolButtonEnabled(boolean[] b) {
			newCreateBtn.setEnabled(b[0]);
			modifyBtn.setEnabled(b[1]);
			delBtn.setEnabled(b[2]);
			refresh.setEnabled(b[3]);
			backup.setEnabled(b[4]);
			restore.setEnabled(b[5]);
		}

		private class ToolButton extends BaseJButton {

			private static final long serialVersionUID = -3547510803997708494L;
			private static final int TOOLBUTTON_WIDTH = 50;
			private static final int TOOLBUTTON_HEIGHT = 50;

			@SuppressWarnings("unused")
			public ToolButton(String title) {
				this(title, null, null);
			}

			@SuppressWarnings("unused")
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

			// private void setBackgroundColor(Color c) {
			// this.setBackground(c);
			// }
		}

	}

	private class LeftPanel extends BaseJPanel {

		private static final long serialVersionUID = -3731623495310154977L;
		private final BaseJList leftList = new BaseJList();

		public LeftPanel() {
			this.initUI();
		}

		protected void initUI() {
			/*
			 * 左侧列表项名称
			 */
			String[][] leftMenu = new String[][] { { "//资金支出管理", "/" },
					{ "新增支出记录", "images/listselect.png" },
					{ "管理支出记录", "images/listselect.png" },
					{ "添加支出类别", "images/listselect.png" },
					{ "支出类别管理", "images/listselect.png" },
					{ "支出分布图", "images/listselect.png" }, { "//资金收入管理", "/" },
					{ "新增收入记录", "images/listselect.png" },
					{ "收入记录管理", "images/listselect.png" },
					{ "添加收入来源", "images/listselect.png" },
					{ "收入类别管理", "images/listselect.png" },
					{ "收入分布图", "images/listselect.png" }, { "//资金收支统计", "/" },
					{ "收支平衡图表", "images/listselect.png" },
					{ "//系统             ", "/" },
					{ "密码更改 ", "images/listselect.png" },
					{ "数据备份 ", "images/listselect.png" },
					{ "数据还原", "images/listselect.png" } };

			this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
			this.setPreferredSize(new Dimension(210, 0));
			this.setBackground(new Color(52, 55, 59));

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

							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, false, false, false, false,
											false });
							// setStatusText("添加支出新纪录...");
							new NewCostRecordUI("新增支出记录", mainFrame)
									.showDialog();

						} else if (index == 7) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, false, false, false, false,
											false });
							// setStatusText("添加收入新纪录...");
							new NewIncomeRecordUI("新增收入记录", mainFrame)
									.showDialog();
						} else if (index == 3) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, false, false, false, false,
											false });
							// setStatusText("添加支出类别...");
							new NewCostCategoryUI("添加支出类别", mainFrame)
									.showDialog();
						} else if (index == 9) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, false, false, false, false,
											false });
							// setStatusText("添加收入来源...");
							new NewIncomeCategoryUI("新增收入类别", mainFrame)
									.showDialog();
						} else if (index == 2) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, true, true, true, false,
											false });

							final CostManagerUI cmu = new CostManagerUI();
							center.addTabComponent("支出记录管理", cmu);
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									mainFrame.setStatusText("正在加载数据...");
									cmu.loadData(null);
									mainFrame.setStatusText("加载完毕");
								}
							}.exec();
						} else if (index == 4) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, true, true, true, false,
											false });

							final CostCategoryManagerUI ccm = new CostCategoryManagerUI();
							center.addTabComponent("支出类别管理", ccm);
							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									mainFrame.setStatusText("正在加载数据...");
									ccm.loadData();
									mainFrame.setStatusText("加载完毕");
								}
							}.exec();

						} else if (index == 8) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, true, true, true, false,
											false });

							final IncomeManagerUI in = new IncomeManagerUI();

							center.addTabComponent("收入记录管理", in);

							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									mainFrame.setStatusText("正在加载数据，请等待...");
									in.loadData(null);
									mainFrame.setStatusText("加载完毕");
								}
							}.exec();
						} else if (index == 10) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, true, true, true, false,
											false });

							final IncomeCategoryManagerUI ic = new IncomeCategoryManagerUI();
							center.addTabComponent("收入类别管理", ic);

							new ThreadExecutorUtils() {

								@Override
								protected void task() {
									mainFrame.setStatusText("正在加载数据...");
									ic.loadData();
									mainFrame.setStatusText("加载完毕");
								}
							}.exec();

						} else if (index == 5) {

							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, true, false,
											false });
							center.addTabComponent("支出分布图",
									new CostAllocationChartUI());
						} else if (index == 11) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, true, false,
											false });
							center.addTabComponent("收入分布图",
									new IncomeAllocationChartUI());

						} else if (index == 13) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, true, false,
											false });
							center.addTabComponent("平衡分布图",
									new BalanceChartUI());
						} else if (index == 15) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, false, false,
											false });

							new ChangePasswordUI(mainFrame).showDialog();

						} else if (index == 16) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, false, true,
											false });
							backup();
						} else if (index == 17) {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, false, false,
											true });
							restore();
						}
					}

				}
			});
			this.add(leftList);
		}

		public void backup() {
			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("数据备份");

			int result = fileChooser.showSaveDialog(mainFrame);

			if (JFileChooser.APPROVE_OPTION == result) {
				Thread t = new Thread(new Runnable() {

					public void run() {
						mainFrame.setStatusText("正在保存备份...");

						File file = fileChooser.getSelectedFile();
						String path = file.getAbsolutePath();
						// JOptionPane.showMessageDialog(null,
						// path);

						FileUtils fileUtils = new FileUtils();
						ZipOutputStream zos = fileUtils.getZipOputStream(file);
						ZipEntry zipEntry = new ZipEntry("fcms.db");
						try {
							zos.putNextEntry(zipEntry);
						} catch (IOException e2) {
							e2.printStackTrace();
						}

						DataInputStream ds = fileUtils
								.getDataInpuStream(new File("db/fcms.db"));

						byte[] data = new byte[512];
						int size = 0;
						try {
							while ((size = ds.read(data)) != -1) {
								zos.write(data, 0, size);
							}

						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {

							try {
								zos.finish();
								zos.closeEntry();
								zos.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}

						}
						mainFrame.setStatusText("备份成功");

					}
				});

				new ProgressBarUI("正在备份数据，请耐心等待...", t, mainFrame);

			}
		}

		public void restore() {
			final JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("数据还原");
			int result = fileChooser.showOpenDialog(mainFrame);
			if (JFileChooser.APPROVE_OPTION == result) {
				Thread t = new Thread(new Runnable() {

					public void run() {

						File file = fileChooser.getSelectedFile();
						FileUtils fileUtils = new FileUtils();
						DataInputStream ds = fileUtils.getDataInpuStream(file);
						DataOutputStream dos = fileUtils
								.getDataOutputStream(new File("db/fcms.db"));
						byte[] b = new byte[512];
						int size = -1;
						try {
							while ((size = ds.read(b)) != -1) {
								dos.write(b, 0, size);
							}
							dos.flush();
						} catch (IOException e1) {
							e1.printStackTrace();
						} finally {
							try {
								ds.close();
								dos.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				});

				new ProgressBarUI("正在还原数据，请耐心等待...", t, mainFrame);
			}
		}

		/**
		 * @return the leftList
		 */
		public BaseJList getLeftList() {
			return leftList;
		}

		@Override
		protected void init() {
		}
	}

	private class CenterPanel extends BaseJPanel {

		private static final long serialVersionUID = -1862161663716703489L;
		private BaseJTabbedPane tab = null;

		public CenterPanel() {
			tab = new BaseJTabbedPane(this);
			tab.addChangeListener(new ChangeListener() {

				public void stateChanged(ChangeEvent e) {
					int index = tab.getSelectedIndex();
					if (index == -1) {

						((TopPanel) top).setTopToolButtonEnabled(new boolean[] {
								false, false, false, false, false, false });
					} else {
						String title = tab.getTitleAt(index);
						// System.out.println(title + "1");
						if (title.trim().equals("收入记录管理")
								|| title.trim().equals("收入类别管理")
								|| title.trim().equals("支出记录管理")
								|| title.trim().equals("支出类别管理")) {
							// System.out.println(title + "2");
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											true, true, true, true, false,
											false });
						} else {
							((TopPanel) top)
									.setTopToolButtonEnabled(new boolean[] {
											false, false, false, true, false,
											false });
						}
					}
				}
			});
		}

		@Override
		protected void init() {
			setLayout(new BorderLayout());

		}

		public void addTabComponent(String title, Component component) {
			tab.addTabComponent(title, component);
			// tab.setSelectedComponent(component);
		}

		public String getSelectedTitle() {
			int index = this.getTab().getSelectedIndex();
			if (index == -1)
				return null;
			return this.getTab().getTitleAt(index);
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
