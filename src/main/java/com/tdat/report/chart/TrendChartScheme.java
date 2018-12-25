package com.tdat.report.chart;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;
import com.tdat.data.analysis.MasterDataStats;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.JsonConverter;

import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Graph to show the trend of all entries of a given column over the years
 */
public class TrendChartScheme extends ChartScheme {

    private String column;

    public String getColumn() {
        return column;
    }

    public TrendChartScheme(String column, ChartType graphType) {
        super(graphType);

        this.column = column;
    }

    /**
     * A helper method to check to see if a given entry has already been seen in the
     * given list of ChartDataSets
     */
    private int containsEntry(List<ChartDataSet> dataSet, String entry) {
        int i;
        for (i = 0; i < dataSet.size(); i++) {
            if (dataSet.get(i).getHeader().equals(entry)) {
                return i;
            }
        }
        return -1;
    }

    public String toJson() {
        List<String> allYears = MasterDataStats.getAllYearsAsString();
        clear();

        for (String year : allYears) {
            this.getXAxisLabels().add(year);

            TableData dataForCurrentYear = MasterData.getYearData(Year.of(Integer.parseInt(year)));
            SingleTableReader tableReader = new SingleTableReader(dataForCurrentYear);
            Map<String, Integer> entryCountForCurrentYear;
            try {
                entryCountForCurrentYear = tableReader.columnEntriesCount(this.column);
            } catch (ColumnNotFoundException e1) {
                entryCountForCurrentYear = new HashMap<>();
            }

            for (String newEntry : entryCountForCurrentYear.keySet()) {

                int valueForCurrYear = entryCountForCurrentYear.getOrDefault(newEntry, 0);
                int indexOfExistingChartData = containsEntry(this.getDataSet(), newEntry);
                List<Integer> newListOfData;

                if (indexOfExistingChartData == -1) {
                    newListOfData = new ArrayList<>();
                    newListOfData.add(valueForCurrYear);
                    this.getDataSet().add(new ChartDataSet(newEntry, newListOfData));
                } else {
                    ChartDataSet newDataSet = this.getDataSet().get(indexOfExistingChartData);
                    newDataSet.addData(valueForCurrYear);
                    this.getDataSet().set(indexOfExistingChartData, newDataSet);
                }
            }
        }

        return JsonConverter.serializeObject(this);
    }
}
