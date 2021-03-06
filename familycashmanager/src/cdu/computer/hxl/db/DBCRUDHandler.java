package cdu.computer.hxl.db;

import java.util.List;
import java.util.Map;

/**
 * 操作数据的增、修、删、查操作
 * 
 * @author hxl
 * 
 */
public interface DBCRUDHandler {

	/**
	 * 添加数据
	 * 
	 * @param dataMap
	 * @param table
	 */
	public void add(Map<String, Object> dataMap, String table);

	/**
	 * 删除数据，根据whereMap
	 * 
	 * @param whereMap
	 * @param table
	 */
	public void delete(Map<String, Object> whereMap, String table);

	/**
	 * 更新数据,根据id
	 * 
	 * @param id
	 * @param updateDataMap
	 * @param table
	 */
	public void update(Integer id, Map<String, Object> updateDataMap,
			String table);

	/**
	 * 读取一条数据
	 * 
	 * @param id
	 * @param table
	 * @return map
	 */
	public Map<String, Object> readOne(String[] field, Integer id, String table);

	/**
	 * 查询数据
	 * 
	 * @param whereDataMap
	 * @param table
	 * @return list of map
	 */
	public List<Map<String, Object>> search(String[] field,
			Map<String, Object> whereDataMap, String table);

}
