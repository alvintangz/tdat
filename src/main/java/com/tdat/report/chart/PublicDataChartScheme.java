package com.tdat.report.chart;

import java.util.ArrayList;
import java.util.List;

import com.tdat.report.JsonConverter;
import com.tdat.report.chart.ChartDataSet;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Graph to show the distribution of the entries of a single column, with years being datasets
 */
public class PublicDataChartScheme extends ChartScheme {

    private String chartScheme;

    public PublicDataChartScheme(JSONObject json) {
        super(ChartType.getChartTypeFromString(json.getString("graph")));
        clear();

        this.setChartScheme(json.getString("type"));
        this.setMainTitle(json.getString("mainTitle"));
        this.setXTitle(json.getString("xAxisTitle"));
        this.setYTitle(json.getString("yAxisTitle"));

        JSONArray arr = json.getJSONArray("xAxisLabels");
        for (int i = 0; i < arr.length(); i++) {
            this.getXAxisLabels().add(arr.getString(i));
        }

        arr = json.getJSONArray("dataSet");
        for (int i = 0; i < arr.length(); i++) {
            JSONArray jsonData = arr.getJSONObject(i).getJSONArray("data");
            List<Integer> data = new ArrayList<Integer>();

            for (int j = 0; j < jsonData.length(); j++) {
                data.add(jsonData.getInt(j));
            }
            this.getDataSet().add(new ChartDataSet(arr.getJSONObject(i).getString("header"), data));
        }
    }

    public String getChartScheme() {
        return this.chartScheme;
    }

    public void setChartScheme(String chartScheme) {
        String prefix = chartScheme.substring(0, 1).toUpperCase();
        this.chartScheme = prefix + chartScheme.substring(1);
    }

    /**
     * A method to return a json rep of the graph to be generated
     * 
     * @return
     */
    public String toJson(){
        return JsonConverter.serializeObject(this);
    }
}
