package cdu.computer.hxl.util;

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
