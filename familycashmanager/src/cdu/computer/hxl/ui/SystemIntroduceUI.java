package cdu.computer.hxl.ui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cdu.computer.hxl.util.FileUtils;
import cdu.computer.hxl.util.Resource;

public class SystemIntroduceUI extends BaseJDialog {
	private static final long serialVersionUID = -9000059681682138953L;

	public SystemIntroduceUI(BaseJFrame owner, String title) {
		super(owner, title, true);
		initUI();
	}

	@Override
	protected void initUI() {
		setResizable(false);
		setSize(400, 300);
		FileUtils file = new FileUtils(Resource.getResourceStream("readMe.txt"));
		JScrollPane scroll = new JScrollPane();
		JTextArea about = new JTextArea();
		about.setText(file.readText());
		scroll.setViewportView(about);
		add(scroll);
		super.initUI();
	}
}
