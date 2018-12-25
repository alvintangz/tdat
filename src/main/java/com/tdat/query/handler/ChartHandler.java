package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class meant to handle commands for generating graphs. Contains
 * helper methods to help with parsing the user query.
 */
public abstract class ChartHandler implements Handler {

  protected String keyword;

  public ChartHandler(String keyword) {
    this.keyword = keyword;
  }

  public String getKeyword() {
    return keyword;
  }

  /**
   * Abstract method to add a chartScheme abject to list of ones to be generated
   * based off user query
   *
   * @param arguments user query
   * @return json
   * @throws InvalidQueryException when query is invalid
   */
  public abstract void handle(String[] arguments) throws InvalidQueryException;

  /**
   * Abstract method to return a ChartScheme abject based off user query
   *
   * @param arguments user query
   * @return json
   * @throws InvalidQueryException when query is invalid
   */
  protected abstract ChartScheme generateChartScheme(String[] arguments) throws InvalidQueryException;

  /**
   * A method to check that a given query contains key(param name), as well as the
   * arguments that follow
   *
   * @param arguments user query
   * @param key       the keyword to check for
   * @return the index of the arguments, -1 if invalid query
   */
  protected int checkForKey(String[] arguments, String key) {
    List<String> argList = Arrays.asList(arguments);
    if (!argList.contains(key.toLowerCase())) {
      return -1;
    }
    int withIndex = argList.indexOf(key);
    if (withIndex == argList.size() - 1) {
      return -1;
    }
    return withIndex + 1;
  }

  /**
   * A method that returns a list of all of the users chosen titles Looks for "...
   * with [title;x-axis;y-axis] ..."
   *
   * @param arguments user query
   * @return list of titles
   * @throws InvalidQueryException when the query is invalid
   */
  private List<String> getTitlesList(String[] arguments) throws InvalidQueryException {
    int titlesIndex = checkForKey(arguments, "with");
    if (titlesIndex == -1) {
      throw new InvalidQueryException();
    }
    List<String> titlesList = Arrays.asList(arguments[titlesIndex].split(";"));
    for (int i = 0; i < titlesList.size(); i++) {
      titlesList.set(i, titlesList.get(i).replace("-", " "));
    }
    if (titlesList.size() != 3) {
      throw new InvalidQueryException();
    }
    return titlesList;
  }

  protected String getTitle(String[] arguments) throws InvalidQueryException {
    List<String> titlesList = getTitlesList(arguments);
    return titlesList.get(0);
  }

  protected String getXTitle(String[] arguments) throws InvalidQueryException {
    List<String> titlesList = getTitlesList(arguments);
    return titlesList.get(1);
  }

  protected String getYTitle(String[] arguments) throws InvalidQueryException {
    List<String> titlesList = getTitlesList(arguments);
    return titlesList.get(2);
  }

  /**
   * A method that returns the ChartType selected by the user's query Looks for
   * "... as [LINE/TABLE/BAR] ..."
   *
   * @param arguments user query
   * @return the selected ChartType
   * @throws InvalidQueryException when query is invalid
   */
  protected ChartType getChartType(String[] arguments) throws InvalidQueryException {
    int chartTypeIndex = checkForKey(arguments, "as");
    if (chartTypeIndex == -1) {
      throw new InvalidQueryException();
    }

    try {
      return ChartType.valueOf(arguments[chartTypeIndex].toUpperCase());
    } catch (Exception e) {
      throw new InvalidQueryException();
    }
  }
}
