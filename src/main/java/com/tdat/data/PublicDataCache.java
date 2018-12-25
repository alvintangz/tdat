package com.tdat.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;

import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.PublicDataChartScheme;

import org.json.JSONObject;

/**
 * A class to serialize table data into JSON format for printing
 */
public class PublicDataCache {

    public static HashMap<String, ChartScheme> CachedPublicData = new HashMap<String, ChartScheme>();

    public static void init() {

        Path p = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "publicData");

        if (!Files.exists(p)) {
            return;
        }
        File[] listOfFiles = p.toFile().listFiles();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                BufferedReader reader = null;

                try {
                    reader = new BufferedReader(new FileReader(file));
                    String line =  reader.lines().collect(Collectors.joining());

                    JSONObject json = new JSONObject(line);
                    ChartScheme chartScheme = new PublicDataChartScheme(json);

                    CachedPublicData.put(chartScheme.getMainTitle(), chartScheme);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
