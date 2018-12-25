package com.tdat.data;

import com.tdat.app.App;
import java.time.Year;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A class to hold all the raw data uploaded to the system.
 */
public class MasterData {

  public static Map<Year, TableData> serviceProvidedData = new HashMap<Year, TableData>();
  public static AtomicInteger reportId = new AtomicInteger();
  public static AtomicInteger publicDataId = new AtomicInteger();

  public static TableData initialVisitData = new TableData();

  public static boolean yearExists(Year year) {
    return serviceProvidedData.containsKey(year);
  }

  public static Map<Year, TableData> getServiceProvidedData() {
    return serviceProvidedData;
  }

  public static TableData getYearData(Year year) {
    return serviceProvidedData.get(year);
  }

  public static void clear() {
    serviceProvidedData.clear();
    initialVisitData = new TableData();
  }

  public static void printServiceProvidedData(Year year) {
    System.out.println("\n[Data for year of " + year + "]");
    if (!serviceProvidedData.containsKey(year)) {
      System.out.println(App.EMPTY);
      return;
    }
    TableData yearData = getYearData(year);
    List<String> columns = yearData.getColumnList();
    for (String column : columns) {
      System.out.println(" '" + column + "':\t" + yearData.getColumnEntries(column));
    }
  }

  public static void printInitialVisitsData() {
    System.out.println("\n[Initial Visits]");
    if (initialVisitData.getVisitsData().size() == 0) {
      System.out.println(App.EMPTY);
      return;
    }
    List<String> columns = initialVisitData.getColumnList();
    for (String column : columns) {
      System.out.println(" '" + column + "':\t" + initialVisitData.getColumnEntries(column));
    }
  }

  public static void setServiceProvidedData(Year year, TableData yearData) {
    serviceProvidedData.put(year, yearData);
  }

  /**
   * Given a List of rows, and it's corresponding year, adds that on to the
   * existing table under that year in the serviceProvidedData
   *
   * @param year      year of data
   * @param allVisits the list of rows
   */
  public static void setServiceProvidedData(Year year, List<Map<String, String>> allVisits) {
    TableData existingYearData;
    if (yearExists(year)) {
      existingYearData = serviceProvidedData.get(year);
    } else {
      existingYearData = new TableData();
    }
    for (Map<String, String> visitKeyValue : allVisits) {
      VisitData visitToAdd = new VisitData();
      visitToAdd.setData(visitKeyValue);
      existingYearData.addVisitData(visitToAdd);
    }

    serviceProvidedData.put(year, existingYearData);
  }

  /**
   * Given a list of rows, adds that on to the existing table of initialVisitData
   *
   * @param allVisits the list of rows
   */
  public static void setInitialVisitData(List<Map<String, String>> allVisits) {
    for (Map<String, String> visitKeyValue : allVisits) {
      VisitData visitToAdd = new VisitData();
      visitToAdd.setData(visitKeyValue);
      initialVisitData.addVisitData(visitToAdd);
    }

  }

  /**
   * Returns all the columns in serviceProvidedData without any duplicates
   *
   * @return a list of all columns
   */
  public static List<String> getAllColumns() {
    List<String> allColumns = new ArrayList<String>();
    for (TableData table : serviceProvidedData.values()) {
      List<String> columnsInTable = table.getColumnList();
      for (int eachColumn = 0; eachColumn < columnsInTable.size(); eachColumn++) {
        // If the current column is not in the list of all columns, then add it
        if (!allColumns.contains(columnsInTable.get(eachColumn))) {
          allColumns.add(columnsInTable.get(eachColumn));
        }
      }
    }
    return allColumns;
  }
}