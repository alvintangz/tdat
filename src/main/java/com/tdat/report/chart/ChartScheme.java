package com.tdat.report.chart;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to store information about a graph to be generated
 */
public abstract class ChartScheme {
    private String mainTitle;
    private String xTitle;
    private String yTitle;
    private ChartType graphType;
    private List<String> xAxisLabels;
    private List<ChartDataSet> dataSet;

    public ChartScheme(ChartType graphType) {
        this.graphType = graphType;

        xAxisLabels = new ArrayList<String>();
        dataSet = new ArrayList<ChartDataSet>();
    }

    public void clear(){
        xAxisLabels.clear();
        dataSet.clear();
    }

    public String getMainTitle() {
        return mainTitle;
    }

    public ChartScheme setMainTitle(String title) {
        this.mainTitle = title;
        return this;
    }

    public String getXTitle() {
        return xTitle;
    }

    public ChartScheme setXTitle(String title) {
        this.xTitle = title;
        return this;
    }

    public String getYTitle() {
        return yTitle;
    }

    public ChartScheme setYTitle(String title) {
        this.yTitle = title;
        return this;
    }

    public ChartType getGraphType() {
        return graphType;
    }

    public List<String> getXAxisLabels() {
        return this.xAxisLabels;
    }

    public List<ChartDataSet> getDataSet() {
        return this.dataSet;
    }

    public abstract String toJson();
}
