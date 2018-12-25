package com.tdat.report.chart;

import com.tdat.data.*;
import com.tdat.data.analysis.SingleTableReader;
import com.tdat.report.JsonConverter;

import java.time.Year;
import java.util.*;

/**
 * Graph to show the distribution of the entries of a single column
 */
public class DistributionChartScheme extends ChartScheme {
    private String column;

    public DistributionChartScheme(String column, ChartType graphType) {
        super(graphType);
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    public String toJson() {
        clear();

        // Iterate through the data for each year in MasterData
        for (Year year : MasterData.getServiceProvidedData().keySet()) {
            TableData dataForCurrentYear = MasterData.getYearData(year);
            SingleTableReader tableReader = new SingleTableReader(dataForCurrentYear);

            Map<String, Integer> entryCountForCurrentYear;
            try {
                entryCountForCurrentYear = tableReader.columnEntriesCount(this.column);
            } catch (ColumnNotFoundException e1) {
                entryCountForCurrentYear = new HashMap<>();
            }
            for (String newEntry : entryCountForCurrentYear.keySet()) {
                if (!this.getXAxisLabels().contains(newEntry)) {
                    this.getXAxisLabels().add(newEntry);
                }
            }
            Collections.sort(this.getXAxisLabels());
            List<Integer> listOfCounts = new ArrayList<>();
            for (String entry : this.getXAxisLabels()) {
                listOfCounts.add(entryCountForCurrentYear.getOrDefault(entry, 0));
            }
            this.getDataSet().add(new ChartDataSet(year.toString(), listOfCounts));
        }
        return JsonConverter.serializeObject(this);

    }
}
