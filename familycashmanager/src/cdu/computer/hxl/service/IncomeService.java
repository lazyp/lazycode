package cdu.computer.hxl.service;

import java.util.Calendar;
import java.util.HashMap;
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

	public void deleteIncomeRecord(int rowid) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		whereMap.put("rowid", rowid);
		dbHandler.delete(whereMap, "income");
	}

	public void deleteIncomeCategory(int rowid) {
		Map<String, Object> whereMap = new HashMap<String, Object>();
		try {
			whereMap.put("sourceid", rowid);
			dbHandler.delete(whereMap, "income");
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		whereMap.clear();

		whereMap.put("rowid", rowid);
		dbHandler.delete(whereMap, "income_category");

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
						"im.remark", "bankname", "im.date" },
						whereDataMap,
						" income as im left join income_category as ic on im.sourceid = ic.rowid left join bank on im.bankid=bank.rowid");
		int size = result.size();

		Object[][] data = new Object[size][6];

		for (int i = 0; i < size; i++) {
			Map<String, Object> row = result.get(i);
			data[i][0] = row.get("rowid");
			data[i][1] = row.get("amount");
			data[i][2] = row.get("categoryname");
			// System.out.println(String.valueOf((Integer) row.get("bankid")));
			data[i][3] = row.get("bankname");
			data[i][4] = row.get("remark");
			data[i][5] = row.get("date");
		}
		return data;
	}

	public Object[][] loadIncomeCategory(Map<String, Object> whereDataMap) {
		List<Map<String, Object>> result = loadIncomeCategoryForList(whereDataMap);
		int sum = result.size();
		Object[][] data = new Object[sum][4];
		for (int i = 0; i < sum; i++) {
			Map<String, Object> m = result.get(i);
			data[i][0] = m.get("rowid");
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

		// System.out.println(sum + "%%%%");

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

				Object o = result.get(0).get("money");
				if (o != null)
					count = (Double) o;
			}

			// System.out.println(count + "%%%%");
			statistData.put((String) m.get("categoryname"), count / sum * 1.0);
		}

		return statistData;
	}

	public Map<Integer, Double> statistiIncomeForBalance() {
		int year = Calendar.getInstance().get(Calendar.YEAR);
		double sum = 0.0;
		Map<String, Object> whereData = new HashMap<String, Object>();
		whereData.put("date like ", "%" + year + "%");
		List<Map<String, Object>> result = dbHandler.search(new String[] {
				"amount", "date" }, whereData, "income");
		Map<Integer, Double> map = new HashMap<Integer, Double>();
		for (int i = 0; i < 12; i++) {
			map.put(i, 0.0);
		}

		int size = result.size();
		for (int i = 0; i < size; i++) {
			Map<String, Object> m = result.get(i);
			double amount = (Double) m.get("amount");
			sum += amount;
			String date = String.valueOf(m.get("date"));
			Integer month = Integer.parseInt(date.split("-")[1]);
			map.put(month - 1, map.get(month - 1) + amount);
		}
		map.put(1000, sum);// ◊‹ ’»Î
		return map;
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
