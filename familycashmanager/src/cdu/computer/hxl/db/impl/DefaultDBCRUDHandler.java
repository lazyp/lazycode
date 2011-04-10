package cdu.computer.hxl.db.impl;

import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;
import cdu.computer.hxl.db.DBConnection;
import cdu.computer.hxl.factory.ObjectFactory;

/**
 * 
 * @author hxl
 * 
 */
public class DefaultDBCRUDHandler implements DBCRUDHandler {
	private DBConnection conn = (DBConnection) ObjectFactory
			.getInstance("dbConnection");

	public void add(Map<String, Object> dataMap, String table) {
         
	}

	public void delete(Integer id, String table) {

	}

	public Map<String, Object> readOne(Integer id, String table) {

		return null;
	}

	public List<Map<String, Object>> search(Map<String, Object> whereDataMap,
			String table) {

		return null;
	}

	public void update(Integer id, Map<String, Object> updateDataMap,
			String table) {

	}

}
