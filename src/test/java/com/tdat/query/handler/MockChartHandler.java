package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.ChartScheme;

public class MockChartHandler extends ChartHandler {

  public MockChartHandler() {
    super("mock");
  }

  @Override
  public void handle(String[] arguments) throws InvalidQueryException {
  }

  @Override
  protected ChartScheme generateChartScheme(String[] arguments) throws InvalidQueryException {
    return null;
  }
}
