package cdu.computer.hxl.util;

/**
 * 封装线程的建立过程
 * 
 * @author Administrator
 * 
 */
public abstract class ThreadExecutorUtils {

	protected abstract void task();

	public void exec() {
		Thread t = new Thread(new Runnable() {

			public void run() {
				task();
			}
		});
		t.start();
	}

	public void joinThread() {

	}
}
