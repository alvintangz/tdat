package com.tdat.analysis;

import java.util.List;

import com.tdat.report.JsonConverter;
import com.tdat.report.chart.ChartDataSet;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;

/**
 * Graph to show the distribution of the entries of a single column
 */
public class MockChartScheme extends ChartScheme {

    public MockChartScheme(List<String> xAxisLabels, List<ChartDataSet> dataSet) {
        super(ChartType.BAR);
        
        if (xAxisLabels != null) {
            this.getXAxisLabels().addAll(xAxisLabels);
        }
        if (dataSet != null) {
            this.getDataSet().addAll(dataSet);
        }
    }

    public String toJson() {
        return JsonConverter.serializeObject(this);
    }
}
