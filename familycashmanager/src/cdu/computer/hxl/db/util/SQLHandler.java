package cdu.computer.hxl.db.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 组件sql语句工具类
 * 
 * @author hxl
 * 
 */
public class SQLHandler {
	public static String createInsertSqlForStatement(
			Map<String, Object> dataMap, String table) {
		String sql = "insert into " + table + " (";
		String sqltail = ")values(";

		List<Map.Entry<String, Object>> lst = mapToList(dataMap);
		int size = dataMap.size();

		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey();
			if (i != size - 1)
				sql += " , ";
			sqltail += buildParam(entry.getValue());
			if (i != size - 1)
				sqltail += " , ";
		}

		return sql + sqltail + ")";
	}

	public static String createInsertSqlForPrepareStatement(
			Map<String, Object> dataMap, String table) {
		String sql = "insert into " + table + " (";
		String sqltail = ")values(";
		List<Map.Entry<String, Object>> lst = mapToList(dataMap);
		int size = dataMap.size();
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey();
			if (i != size - 1)
				sql += " , ";
			sqltail += "?";
			if (i != size - 1)
				sqltail += " , ";
		}
		return sql + sqltail + ")";
	}

	public static String createDeleteSqlForStatement(
			Map<String, Object> whereDataMap, String table) {
		String sql = "delete from " + table + " where ";
		List<Map.Entry<String, Object>> lst = mapToList(whereDataMap);
		int size = whereDataMap.size();
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + " = ";
			sql += buildParam(entry.getValue());
			if (i != size - 1)
				sql += "  and ";
		}
		return sql;
	}

	public static String createDeleteSqlForPrepareStatement(
			Map<String, Object> whereDataMap, String table) {
		String sql = "delete from " + table + " where ";
		List<Map.Entry<String, Object>> lst = mapToList(whereDataMap);
		int size = whereDataMap.size();
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + " = ";
			sql += "?";
			if (i != size - 1)
				sql += "  and ";
		}
		return sql;
	}

	public static String createReadSqlForStatement(String[] field,
			Map<String, Object> whereDataMap, String table) {
		String sql = "select ";
		int size = field.length;
		for (int i = 0; i < size; i++) {
			sql += field[i];
			if (i != size - 1)
				sql += " , ";
		}
		sql += " from " + table;
		String in = null;
		if (whereDataMap != null) {
			sql += " where ";
			List<Map.Entry<String, Object>> lst = mapToList(whereDataMap);
			size = whereDataMap.size();
			for (int i = 0; i < size; i++) {
				Map.Entry<String, Object> entry = lst.get(i);
				String key = entry.getKey();
				if (key.indexOf("in") != -1) {
					in = key + " (" + entry.getValue() + ")";
					continue;
				}
				sql += key + " = " + buildParam(entry.getValue());
				if (i != size - 1)
					sql += " and ";
			}
		}
		if (in != null)
			if (size > 1)
				sql += " and " + in;
			else
				sql += in;

		return sql;
	}

	public static String createReadSqlForPrepareStatement(String[] field,
			Map<String, Object> whereDataMap, String table) {
		String sql = "select ";
		int size = field.length;
		for (int i = 0; i < size; i++) {
			sql += field[i];
			if (i != size - 1)
				sql += " , ";
		}
		sql += " from " + table + " where ";
		List<Map.Entry<String, Object>> lst = mapToList(whereDataMap);
		size = whereDataMap.size();
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + " = ? ";
			if (i != size - 1)
				sql += " and ";
		}
		return sql;
	}

	public static String createUpdateSqlForStatement(
			Map<String, Object> dataMap, Map<String, Object> whereDataMap,
			String table) {
		String sql = "update " + table + " set ";
		int size = dataMap.size();
		List<Map.Entry<String, Object>> lst = mapToList(dataMap);
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + "=" + buildParam(entry.getValue());
			if (i != size - 1)
				sql += " , ";
		}

		sql += " where ";
		size = whereDataMap.size();
		lst = mapToList(whereDataMap);
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + "=" + buildParam(entry.getValue());
			if (i != size - 1)
				sql += " and ";
		}
		return sql;
	}

	public static String createUpdateSqlForPrepareStatement(
			Map<String, Object> dataMap, Map<String, Object> whereDataMap,
			String table) {
		String sql = "update set ";
		int size = dataMap.size();
		List<Map.Entry<String, Object>> lst = mapToList(dataMap);
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + "=?";
			if (i != size - 1)
				sql += " , ";
		}

		sql += " where ";
		size = whereDataMap.size();
		lst = mapToList(whereDataMap);
		for (int i = 0; i < size; i++) {
			Map.Entry<String, Object> entry = lst.get(i);
			sql += entry.getKey() + "=?";
			if (i != size - 1)
				sql += " and ";
		}
		return sql;
	}

	private static List<Map.Entry<String, Object>> mapToList(
			Map<String, Object> map) {
		Set<Map.Entry<String, Object>> set = map.entrySet();
		List<Map.Entry<String, Object>> lst = new ArrayList<Map.Entry<String, Object>>(
				map.size());
		Iterator<Map.Entry<String, Object>> iter = set.iterator();
		for (; iter.hasNext();) {
			lst.add(iter.next());
		}
		return lst;
	}

	private static Object buildParam(Object param) {
		if (param instanceof Integer || param instanceof Double
				|| param instanceof Float)
			return param;
		else if (param instanceof String)
			return "'" + param + "'";
		return param;
	}

	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("amount", 23.03);
		map.put("reason", "不知道");
		map.put("bankid", 1);
		map.put("date", "2010-2-2");
		System.out.println(SQLHandler
				.createInsertSqlForStatement(map, "income"));
		System.out.println(SQLHandler.createInsertSqlForPrepareStatement(map,
				"income"));
	}
}
