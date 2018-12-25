package com.tdat.report.chart;

import java.util.List;

/**
 * A class to hold the y values of a given data set, with the title of the data set
 */
public class ChartDataSet {
	private String header;
	private List<Integer> data;

	public ChartDataSet(String header, List<Integer> data) {
		this.header = header;
		this.data = data;
	}

	public String getHeader() {
		return this.header;
	}

	public List<Integer> getData() {
		return this.data;
	}

	public void addData(int num){
		data.add(num);
	}

	@Override
	public String toString() {
		return String.format("{header: %s, data: %s}", this.getHeader(), this.getData());
	}
}
