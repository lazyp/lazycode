package cdu.computer.hxl.ui;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.basic.BasicButtonUI;

/**
 * tab面板的一个基础类
 * 
 * @author hxl
 * @date 2010-03-27
 */
public class BaseJTabbedPane extends JTabbedPane {

	private Container container = null;

	public BaseJTabbedPane(Container container) {
		this.container = container;
		init();
	}

	protected void init() {
		if (container != null)
			container.add(this);

		this.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		this.setVisible(true);

	}

	public void addTabComponent(String tabTitle, Component comp) {
		int index = indexOfTab(tabTitle);
		if (index == -1) {
			add(tabTitle, comp);
			// System.out.println(getTabCount());
			setTabComponentAt(getTabCount() - 1, new ButtonTabComponent(
					BaseJTabbedPane.this, getTabCount() - 1));
			setSelectedComponent(comp);
		} else {
			// System.out.println();

			// setSelectedIndex(index);
			setComponentAt(index, comp);
			setSelectedComponent(comp);
		}
	}

	private static class ButtonTabComponent extends JPanel {
		private BaseJTabbedPane bjp = null;
		private int i = -1;

		public ButtonTabComponent(BaseJTabbedPane bjp, int index) {
			// System.out.println(bjp);
			this.bjp = bjp;
			this.i = index;

			initUI();
		}

		private void initUI() {

			setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

			this.setOpaque(false);

			// this.setPreferredSize(new Dimension(45, 20));

			BaseJLabel label = new BaseJLabel() {
				@Override
				public String getText() {
					int index = bjp
							.indexOfTabComponent(ButtonTabComponent.this);
					if (index != -1)
						return bjp.getTitleAt(index);
					return super.getText();
				}
			};
			label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
			add(label);
			bjp.setTabComponentAt(i, new TabButton(bjp));
			setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

			add(new TabButton(bjp));
		}

	}

	private static class TabButton extends BaseJButton implements
			ActionListener {

		public TabButton(BaseJTabbedPane bjp) {
			this.bjp = bjp;
			this.init();

		}

		@Override
		protected void init() {
			// super.init();
			// setSize(6, 6);
			this.setPreferredSize(new Dimension(17, 17));
			setUI(new BasicButtonUI());
			setFocusable(false);
			setContentAreaFilled(false);
			setBorder(BorderFactory.createEtchedBorder());
			setBorderPainted(false);
			setFocusable(false);
			setRolloverEnabled(true);

			addActionListener(this);
			addMouseListener(mouseListener);

		}

		@Override
		public void updateUI() {
		}

		// paint the cross
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g.create();
			// shift the image for pressed buttons
			if (getModel().isPressed()) {
				g2.translate(1, 1);
			}
			g2.setStroke(new BasicStroke(2));
			g2.setColor(Color.BLACK);
			if (getModel().isRollover()) {
				g2.setColor(Color.RED);
			}
			int delta = 6;
			g2.drawLine(delta, delta, getWidth() - delta - 1, getHeight()
					- delta - 1);
			g2.drawLine(getWidth() - delta - 1, delta, delta, getHeight()
					- delta - 1);
			g2.dispose();
		}

		public void actionPerformed(ActionEvent e) {
			int index = bjp.indexOfTabComponent(this.getParent());
			if (index != -1)
				bjp.remove(index);

		}

		private BaseJTabbedPane bjp = null;

		private static final MouseListener mouseListener = new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(true);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				Component component = e.getComponent();
				if (component instanceof AbstractButton) {
					AbstractButton button = (AbstractButton) component;
					button.setBorderPainted(false);
				}
			}

		};
	}

	public static void main(String[] args) {
//		try {
//			UIManager.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (InstantiationException e) {
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			e.printStackTrace();
//		} catch (UnsupportedLookAndFeelException e) {
//			e.printStackTrace();
//		}
//		JFrame frame = new JFrame("测试");
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		BaseJTabbedPane bjp = new BaseJTabbedPane(frame);
//		JLabel label = new JLabel("ddddddd");
//		bjp.addTabComponent("tab1", new JLabel(
//				"testeststestestsetststeststeststest"));
//		bjp.addTabComponent("tab2", new JLabel(
//				"testeststestestsetststeststeststes2sst"));
//		bjp.addTabComponent("tab3", new JLabel(
//				"testeststestestsetststeststeststes2sst"));
//		bjp.addTabComponent("tab4", label);
//
//		bjp.addTabComponent("tab5", label);
//		frame.setSize(200, 150);
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
	}
}
