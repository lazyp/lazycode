package cdu.computer.hxl.db.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;
import cdu.computer.hxl.db.DBConnection;
import cdu.computer.hxl.db.util.SQLHandler;
import cdu.computer.hxl.exception.DBConnectionException;
import cdu.computer.hxl.factory.ObjectFactory;

/**
 * 数据库CURD默认实现
 * 
 * @author hxl
 * 
 */
public class DefaultDBCRUDHandler implements DBCRUDHandler {
	private DBConnection db = (DBConnection) ObjectFactory
			.getInstance("dbConnection");
	private Connection conn = null;
	private Statement stm = null;
	private ResultSet rs = null;

	public void add(Map<String, Object> dataMap, String table) {
		String sql = SQLHandler.createInsertSqlForStatement(dataMap, table);
		prepareExecute();
		try {
			int addsum = stm.executeUpdate(sql);

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			this.closeDB();
		}
	}

	public void delete(Integer id, String table) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("rowid", id);
		String sql = SQLHandler.createDeleteSqlForStatement(whereMap, table);
		prepareExecute();

		try {
			int delsum = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	public Map<String, Object> readOne(String[] field, Integer id, String table) {
		Map<String, Object> queryResult = new HashMap<String, Object>();

		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("rowid", id);
		String sql = SQLHandler.createReadSqlForStatement(field, whereMap,
				table);
		prepareExecute();
		try {
			rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int cols = metaData.getColumnCount();
			for (int i = 1; i <= cols; i++) {
				String colName = metaData.getColumnName(i);
				Object value = rs.getObject(colName);
				queryResult.put(colName, value);
			}
			if (rs.next()) {

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return queryResult;
	}

	public List<Map<String, Object>> search(String[] field,
			Map<String, Object> whereDataMap, String table) {
		List<Map<String, Object>> queryResult = new ArrayList<Map<String, Object>>();

		String sql = SQLHandler.createReadSqlForPrepareStatement(field,
				whereDataMap, table);
		prepareExecute();
		try {
			rs = stm.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int cols = metaData.getColumnCount();
			while (rs.next()) {
				Map<String, Object> data = new HashMap<String, Object>();
				for (int i = 1; i < cols; i++) {
					String colname = metaData.getColumnName(i);
					Object value = rs.getObject(colname);
					data.put(colname, value);
				}
				queryResult.add(data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return queryResult;
	}

	public void update(Integer id, Map<String, Object> updateDataMap,
			String table) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("rowid", id);
		String sql = SQLHandler.createUpdateSqlForStatement(updateDataMap,
				whereMap, table);
		prepareExecute();
		try {
			int updatesum = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	private void prepareExecute() {

		try {
			conn = db.connection();
			stm = conn.createStatement();
		} catch (DBConnectionException e) {

			e.printStackTrace();
		} catch (SQLException ee) {
			ee.printStackTrace();
		}

	}

	private void closeDB() {
		try {
			if (rs != null)
				rs.close();
			if (stm != null)
				stm.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DefaultDBCRUDHandler handler = new DefaultDBCRUDHandler();
		Map<String, Object> data = new HashMap<String, Object>();
		
  	}
}
