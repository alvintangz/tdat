package com.tdat.report;

import com.tdat.report.chart.ChartScheme;

import org.json.JSONObject;

/**
 * A class to serialize table data into JSON format for printing
 */
public class JsonConverter {
    
    // Current Payload Structure (Keep Updated)
    // {
    //     "type": "line",
    //     "mainTitle": "Some title",
    //     "xAxisTitle": "# Of Children",
    //     "xAxisLabels": ["1", "2", "3", "4", "5+"],
    //     "yAxisTitle": "Value",
    //     "dataSet": [
    //         { "header": "2018", "data": [3, 6, 1, 7, 8] },
    //         { "header": "2017", "data": [2, 8, 3, 12, 6] }
    //     ]
    // }

    public static String serializeObject(ChartScheme chart) {
        if (chart.getGraphType() == null) {
            throw new NullPointerException("type is null");
        }

        if (chart.getMainTitle() == null) {
            throw new NullPointerException("mainTitle is null");
        }
        
        if (chart.getXTitle() == null) {
            throw new NullPointerException("xAxisTitle is null");
        }

        if (chart.getYTitle() == null) {
            throw new NullPointerException("yAxisTitle is null");
        }

        if (chart.getXAxisLabels().size() == 0) {
            throw new IllegalStateException("xAxisLabels is empty");
        }

        if (chart.getDataSet() == null) {
            throw new NullPointerException("data is null");
        }

    	JSONObject json = new JSONObject();
        json.put("type", chart.getGraphType().getJsonCode());
        json.put("mainTitle", chart.getMainTitle());
        json.put("xAxisTitle", chart.getXTitle());
        json.put("yAxisTitle", chart.getYTitle());
        json.put("xAxisLabels", chart.getXAxisLabels());
        json.put("dataSet", chart.getDataSet());

        return json.toString();
    }
}
