package cdu.computer.hxl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	public Object[][] loadIncomeRecord(Map<String, Object> whereDataMap) {
		/*
		 * select amount , remark , categoryname , bankname from (income left
		 * join income_category on income.sourceid = income_category.rowid) left
		 * join bank on income.bankid=bank.rowid
		 */
		List<Map<String, Object>> result = dbHandler
				.search(new String[] { "im.rowid", "amount", "categoryname",
						"bankname", "im.date" },
						whereDataMap,
						" income as im left join income_category as ic on im.sourceid = ic.rowid left join bank on im.bankid=bank.rowid");
		int size = result.size();

		Object[][] data = new Object[size][5];

		for (int i = 0; i < size; i++) {
			Map<String, Object> row = result.get(i);
			data[i][0] = row.get("rowid");
			data[i][1] = row.get("amount");
			data[i][2] = row.get("categoryname");
			// System.out.println(String.valueOf((Integer) row.get("bankid")));
			data[i][3] = row.get("bankname");
			data[i][4] = row.get("date");
		}
		return data;
	}

	public Object[][] loadIncomeCategory(Map<String, Object> whereDataMap) {
		List<Map<String, Object>> result = loadIncomeCategoryForList(whereDataMap);
		int sum = result.size();
		Object[][] data = new Object[sum][4];
		for (int i = 0; i < sum; i++) {
			Map<String, Object> m = result.get(i);
			data[i][0] = i + 1;
			data[i][1] = m.get("categoryname");
			data[i][2] = m.get("remark");
			data[i][3] = m.get("datetime");
		}
		return data;
	}

	public List<Map<String, Object>> loadIncomeCategoryForList(
			Map<String, Object> whereDataMap) {
		List<Map<String, Object>> result = dbHandler.search(new String[] {
				"rowid", "categoryname", "remark", "datetime" }, whereDataMap,
				"income_category");
		return result;
	}

	public Map<String, Object> statistiIncome() {
		Map<String, Object> statistData = new HashMap<String, Object>();
		List<Map<String, Object>> incomeCategory = dbHandler.search(
				new String[] { "rowid", "categoryname" }, null,
				"income_category");
		int size = incomeCategory.size();
		Map<String, Object> whereDataMap = new HashMap<String, Object>();
		List<Map<String, Object>> sumList = dbHandler.search(
				new String[] { "count(*) as sum" }, null, "income");
		int count = 1;
		if (sumList != null && sumList.size() == 1)
			count = (Integer) sumList.get(0).get("sum");

		for (int i = 0; i < size; i++) {
			Object id = incomeCategory.get(i).get("rowid");
			String catename = (String) incomeCategory.get(i)
					.get("categoryname");
			whereDataMap.clear();
			whereDataMap.put("sourceid", id);
			sumList = dbHandler.search(new String[] { "count(rowid) as sum" },
					whereDataMap, "income");
			if (sumList != null && sumList.size() == 1)
				// System.out.println(((Integer) sumList.get(0).get("sum"))
				// .intValue() * 0.1 / count);
				statistData.put(catename,
						((Integer) sumList.get(0).get("sum")).intValue() * 1.0
								/ count * 100);
		}
		return statistData;
	}

	
	public Map<String, Double> statistiIncomeForMoneyallocation() {
		Map<String, Double> statistData = new HashMap<String, Double>();
		List<Map<String, Object>> sumMoneyList = dbHandler.search(
				new String[] { "sum(amount) as summoney" }, null, "income");
		double sum = 1;
		if (sumMoneyList != null && sumMoneyList.size() == 1)
			sum = (Double) sumMoneyList.get(0).get("summoney");

		List<Map<String, Object>> cateList = dbHandler.search(new String[] {
				"rowid", "categoryname" }, null, "income_category");

		Map<String, Object> whereDataMap = new HashMap<String, Object>();

		int size = cateList.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> m = cateList.get(i);
			whereDataMap.clear();
			whereDataMap.put("sourceid", m.get("rowid"));
			List<Map<String, Object>> result = dbHandler.search(
					new String[] { "sum(amount) as money" }, whereDataMap,
					"income");
			double count = 0;
			if (result != null && result.size() == 1) {
				count = (Double) result.get(0).get("money");
			}
			statistData.put((String) m.get("categoryname"), count / sum * 1.0);
		}

		return statistData;
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
