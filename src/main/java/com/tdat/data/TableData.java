package com.tdat.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to hold all entries (rows) entered within a given year.
 */
public class TableData {
	
	private List<VisitData> allVisitsData;
	private List<String> columnList;

	public TableData() {
		allVisitsData = new ArrayList<VisitData>();
		columnList = new ArrayList<>();
	}

	public List<VisitData> getVisitsData() {
		return allVisitsData;
	}

	public void data(List<VisitData> data) {
		this.allVisitsData = data;
	}

	public List<String> getColumnList(){
		return columnList;
	}

    /**
     * Will ensure the column list is up to date.
     * @param visitData
     */
	public void addVisitData(VisitData visitData) {
		allVisitsData.add(visitData);
		for(String column: visitData.getData().keySet()){
		    if (!columnList.contains(column)){
		        columnList.add(column);
            }
        }
	}

	public List<String> getColumnEntries(String column){
	    List<String> entryList= new ArrayList<>();
	    for (VisitData visit: allVisitsData){
	        entryList.add(visit.getColumnData(column));
        }
        return entryList;
    }


}
