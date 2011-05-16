package cdu.computer.hxl.exception;

/**
 * 数据库连接异常
 * 
 * @author hxl
 * 
 */
public class DBConnectionException extends Exception {

	private static final long serialVersionUID = -6970689210052670478L;

	public DBConnectionException(String message) {
		super(message);
	}

}
