package cdu.computer.hxl.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cdu.computer.hxl.db.DBCRUDHandler;

/**
 * 用户操作服务类
 * 
 * @author hxl
 * 
 */
public class MemberService {
	private DBCRUDHandler dbHandler = null;

	public MemberService() {
	}

	public MemberService(DBCRUDHandler dbHandler) {
		this.dbHandler = dbHandler;
	}

	/**
	 * 检查登录用户名和密码是否正确
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
	 * 修改密码
	 * 
	 * @param uname
	 * @param oldupass
	 * @param newupass
	 * @return true or false
	 */
	public boolean modifyPass(String uname, String oldupass, String newupass) {
		Map<String, Object> whereDataMap = new HashMap<String, Object>();
		whereDataMap.put("uname", uname);
		whereDataMap.put("upass", oldupass);
		List<Map<String, Object>> member = dbHandler.search(new String[] {
				"rowid", "uname", "upass" }, whereDataMap, "member");
		if (member != null && member.size() == 1) {
			Map<String, Object> updateDataMap = new HashMap<String, Object>();
			updateDataMap.put("upass", newupass);
			Integer id = (Integer) member.get(0).get("rowid");
			dbHandler.update(id, updateDataMap, "member");
			return true;
		}
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
