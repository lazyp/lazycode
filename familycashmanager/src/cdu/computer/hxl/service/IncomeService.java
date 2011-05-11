package cdu.computer.hxl.service;

import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;

public class IncomeService {
	private DBCRUDHandler dbHandler = null;

	public IncomeService() {

	}

	public IncomeService(DBCRUDHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	public void addIncomeItem(Map<String, Object> data) {
		dbHandler.add(data, "income");
	}

	public void addIncomeCategory(Map<String, Object> data) {
		dbHandler.add(data, "income_category");
	}

	public int getRowIdForBank(Map<String, Object> whereMap) {
		List<Map<String, Object>> result = dbHandler.search(
				new String[] { "rowid" }, whereMap, "bank");
		int rowid = -1;
		if (result != null && result.size() > 0)
			rowid = (Integer) result.get(0).get("rowid");
		return rowid;
	}

	/**
	 * @return the dbHandler
	 */
	public DBCRUDHandler getDbHandler() {
		return dbHandler;
	}

	/**
	 * @param dbHandler
	 *            the dbHandler to set
	 */
	public void setDbHandler(DBCRUDHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

}
