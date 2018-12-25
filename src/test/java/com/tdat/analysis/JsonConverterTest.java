package com.tdat.analysis;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tdat.report.chart.ChartDataSet;
import com.tdat.report.chart.DistributionChartScheme;
import com.tdat.data.ColumnNotFoundException;
import com.tdat.report.JsonConverter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class JsonConverterTest {

    private MockChartScheme mock;

    @Test
    @DisplayName("Test creating json object using entries")
    void jsonColumnEntriesCount() throws ColumnNotFoundException {
        List<String> labels = Arrays.asList("label1", "label2", "label3");

        String expected = "{\"xAxisLabels\":[\"label1\",\"label2\",\"label3\"],\"mainTitle\":\"main title\",\"xAxisTitle\":\"x axis title\",\"type\":\"bar\",\"dataSet\":[{\"data\":[1,2,3,4],\"header\":\"a header\"}],\"yAxisTitle\":\"y axis title\"}";

        List<ChartDataSet> data = new ArrayList<>(
                Arrays.asList(new ChartDataSet("a header", Arrays.asList(1, 2, 3, 4))));

        MockChartScheme actual = new MockChartScheme(labels, data);
        actual.setMainTitle("main title");
        actual.setXTitle("x axis title");
        actual.setYTitle("y axis title");

        assertEquals(expected, actual.toJson());
    }

    @Test
    @DisplayName("Test creating json object with null input")
    void dneColumnEntries() throws NullPointerException {
        mock = new MockChartScheme(null, null);

        assertThrows(NullPointerException.class, () -> {
            JsonConverter.serializeObject(mock);
        });

        mock.setMainTitle("main title");
        assertThrows(NullPointerException.class, () -> {
            JsonConverter.serializeObject(mock);
        });

        mock.setXTitle("x axis title");

        assertThrows(NullPointerException.class, () -> {
            JsonConverter.serializeObject(mock);
        });

        mock = new MockChartScheme(Arrays.asList("x axis label"), null);
        mock.setMainTitle("main title");
        mock.setXTitle("x axis title");

        assertThrows(NullPointerException.class, () -> {
            JsonConverter.serializeObject(mock);
        });

        mock.setYTitle("y axis title");

        String expected = "{\"xAxisLabels\":[\"x axis label\"],\"mainTitle\":\"main title\",\"xAxisTitle\":\"x axis title\",\"type\":\"bar\",\"dataSet\":[],\"yAxisTitle\":\"y axis title\"}";

        assertEquals(expected, mock.toJson());
    }
}