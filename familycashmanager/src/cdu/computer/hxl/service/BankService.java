package cdu.computer.hxl.service;

import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;

public class BankService {
	private DBCRUDHandler dbHandler = null;

	public List<Map<String, Object>> loadAllBank() {
		List<Map<String, Object>> bank = dbHandler.search(new String[] {
				"rowid", "bankname" }, null, "bank");
		return bank;
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
