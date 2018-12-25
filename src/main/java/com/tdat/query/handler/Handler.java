package com.tdat.query.handler;

import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.ChartScheme;

public interface Handler {

  public String getKeyword();

  public void handle(String[] args) throws InvalidQueryException;
}
