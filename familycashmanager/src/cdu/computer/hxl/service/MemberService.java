package cdu.computer.hxl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;

public class MemberService {
	private DBCRUDHandler dbHandler = null;

	public MemberService() {
	}

	public MemberService(DBCRUDHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	/**
	 * 
	 * @param uname
	 * @param upass
	 * @return true or false
	 */
	public boolean checkMember(String uname, String upass) {
		Map<String, Object> whereDataMap = new HashMap<String, Object>();
		whereDataMap.put("uname", uname);
		whereDataMap.put("upass", upass);
		List<Map<String, Object>> member = dbHandler.search(new String[] {
				"uname", "upass" }, whereDataMap, "member");
		if (member != null && member.size() == 1)
			return true;
		return false;
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
