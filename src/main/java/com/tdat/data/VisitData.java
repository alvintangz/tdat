package com.tdat.data;

import com.tdat.app.App;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to represent an individual's entry (row) on a iCare spreadsheet
 */
public class VisitData {
	private Map<String, String> data;

	public VisitData() { 
		data = new HashMap<String, String>();
	}

	public Map<String, String> getData() {
		return data;
	}

	public String getColumnData(String columnName) {
		if (!this.data.containsKey(columnName)){
			return App.EMPTY;
		}
		return this.data.get(columnName);
	}

	public void addColumnData(String columnName, String value) {
		this.data.put(columnName, value);
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}

	public boolean columnDataExists(String columnName) {
		return this.data.containsKey(columnName);
	}
}
