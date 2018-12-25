package com.tdat.query.handler;

import com.tdat.app.App;
import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.DistributionChartScheme;

/**
 * A method to create a DistributionChartScheme in json, based off user query
 */
public class DistributionHandler extends ChartHandler {

  public DistributionHandler() {
    super("distribution");
  }

  protected ChartScheme generateChartScheme(String[] arguments) throws InvalidQueryException {
    int columnIndex = checkForKey(arguments, "of");
    if (columnIndex == -1) {
      throw new InvalidQueryException();
    }

    DistributionChartScheme result = new DistributionChartScheme(
        arguments[columnIndex].replace("-", " "),
        getChartType(arguments));
    result.setMainTitle(getTitle(arguments)).setXTitle(getXTitle(arguments))
        .setYTitle(getYTitle(arguments));
    return result;
  }

  @Override
  public void handle(String[] arguments) throws InvalidQueryException {
    App.reportsList.add(generateChartScheme(arguments));
  }
}
