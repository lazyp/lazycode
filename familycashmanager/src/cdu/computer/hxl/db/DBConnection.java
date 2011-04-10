package cdu.computer.hxl.db;

import java.sql.Connection;

import cdu.computer.hxl.exception.DBConnectionException;

/**
 * 连接数据库的接口
 * 
 * @author hxl
 * 
 */
public interface DBConnection {
	/**
	 * 根据配置文件取得数据的连接，由子类实现
	 * 
	 * @return connection 返回一个数据库连接实例 *
	 * @throws DBConnectionException
	 *             抛出连接异常
	 */
	public Connection connection() throws DBConnectionException;

}
