package cdu.computer.hxl.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import cdu.computer.hxl.util.ThreadExecutorUtils;

/**
 * 进度条
 * 
 * @author Administrator
 * 
 */
public class ProgressBarUI {
	private BaseJFrame parent = null;
	private JDialog barDialog = null;
	private JProgressBar progressBar = null;
	private JLabel lbStatus = null;

	private String statusInfo = "";

	private Thread thread = null;

	public ProgressBarUI(String statusInfo, Thread thread, BaseJFrame parent) {
		this.parent = parent;
		this.statusInfo = statusInfo;
		this.thread = thread;
		initUI();
		startThread();
		barDialog.setVisible(true);
	}

	public void initUI() {
		barDialog = new JDialog(parent, true);
		barDialog.setUndecorated(true); // 除去title

		final JPanel mainPane = new JPanel(null);
		progressBar = new JProgressBar();
		lbStatus = new JLabel("" + statusInfo);

		progressBar.setIndeterminate(true);
		mainPane.add(progressBar);
		mainPane.add(lbStatus);

		barDialog.getContentPane().add(mainPane);

		barDialog.setResizable(true);
		barDialog.setSize(390, 100);
		barDialog.setLocationRelativeTo(parent); // 设置此窗口相对于指定组件的位置

		barDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE); // 不允许关闭

		mainPane.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				layout(mainPane.getWidth(), mainPane.getHeight());
			}
		});

	}

	private void layout(int width, int height) {
		progressBar.setBounds(20, 20, 350, 15);
		lbStatus.setBounds(20, 50, 350, 25);
	}

	private void startThread() {
		new Thread() {
			public void run() {
				try {
					thread.start(); // 处理耗时任务
					// 等待事务处理线程结束
					thread.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// 关闭进度提示框
					barDialog.dispose();

					String title = "提示";
					JOptionPane.showMessageDialog(parent, "成功!", title,

					JOptionPane.INFORMATION_MESSAGE);

				}
			}
		}.start();
	}

	public static void main(String[] args) throws Exception {

		// 业务线程
		Thread thread = new Thread() {
			public void run() {
				int index = 0;

				while (index < 5) {
					try {
						sleep(1000);
						System.out.println(++index);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};

		new ProgressBarUI("正在备份,请稍候....", thread, null);

	}
}
