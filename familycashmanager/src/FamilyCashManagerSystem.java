import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

import com.jtattoo.plaf.noire.NoireLookAndFeel;
import com.jtattoo.plaf.smart.SmartLookAndFeel;

import cdu.computer.hxl.db.DBCRUDHandler;
import cdu.computer.hxl.factory.ObjectFactory;
import cdu.computer.hxl.service.MemberService;
import cdu.computer.hxl.ui.BaseJFrame;
import cdu.computer.hxl.ui.LoginUI;
import cdu.computer.hxl.ui.MainUI;
import cdu.computer.hxl.util.Constants;
import cdu.computer.hxl.util.Resource;
import cdu.computer.hxl.util.VarCacheFactory;

/**
 * 家庭财务管理系统主类
 * 
 * @author hxl
 * @date 2011-03-16
 */
public class FamilyCashManagerSystem {

	public static void main(String[] args) throws IOException,
			URISyntaxException {

		Properties props = new Properties();
		props.put("logoString", "");// 菜单栏左边的文字
		// props.put("menuSelectionBackgroundColor", "150 160 160");// 菜单栏左边的背景色
		// props.put("buttonColor", "0 0 0");
		// props.put("buttonColorLight", "0 0 0");
		// props.put("buttonColorDark", "0 0 0");
		// props.put("rolloverColor", "0 0 0");
		// props.put("rolloverColorLight", "0 0 0");
		// props.put("rolloverColorDark", "0 0 0");
		props.put("windowTitleForegroundColor", "0 200 0");// 设置窗体title的颜色
		// props.put("windowTitleBackgroundColor", "0 255 0");
		// props.put("windowTitleColorLight", "0 255 0");
		// props.put("windowTitleColorDark", "0 255 0");
		// props.put("windowBorderColor", "0 0 0");

		NoireLookAndFeel.setCurrentTheme(props);

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
		Image loginImg = ImageIO.read(Resource
				.getResourceStream("images/login.jpg"));

		Image image = ImageIO.read(Resource
				.getResourceURL("images/trayIcon.jpg"));

		final BaseJFrame mainFrame = new BaseJFrame()
				.setFrameTitle("家庭财务管理系统")
				.setFrameSize(Constants.LOGIN_WIDTH, Constants.LOGIN_HEIGHT)
				.setFrameIconImage(image).setFrameResizable(false)
				.setFrameCenter();

		final LoginUI login = LoginUI.instance().mainFrame(mainFrame)
				.topImage(loginImg).build();
		final MainUI mainUI = new MainUI(mainFrame);

		login.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (login.isAccessLogin()) {

					mainFrame.getContentPane().removeAll();
					// mainFrame.showStatus();
					// mainFrame.getContentPane().repaint();
					// mainFrame.getContentPane().validate();
					mainUI.initUI();
				} else {
					JOptionPane.showMessageDialog(mainFrame,
							"登录失败 ，请检查用户名和密码是否正确!", "提示",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		mainFrame.showFrame();
	}
}
