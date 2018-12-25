package com.tdat.query.handler;

import static org.junit.jupiter.api.Assertions.*;

import com.tdat.data.MasterData;
import com.tdat.query.InvalidQueryException;
import com.tdat.report.chart.ChartType;
import java.util.Arrays;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChartHandlerTest {
  private static String query = "distribution of children with children;num;value as bar";
  private static String[] splitInput;
  private static String[]args;
  private ChartHandler mock = new MockChartHandler();

  @BeforeAll
  public static void setUp(){
    splitInput = query.toLowerCase().split("\\s");
    args = Arrays.copyOfRange(splitInput, 1, splitInput.length);
  }

  @Test
  public void testGetKeyword() {
    assertEquals("mock", mock.getKeyword());
  }

  @Test
  void testCheckForExistingKey() {
    assertEquals(1, mock.checkForKey(args, "of"));
  }

  @Test
  void testCheckForNonExistingKey() {
    assertEquals(-1, mock.checkForKey(args, "ds"));
  }

  @Test
  void testGetChartType() throws InvalidQueryException {
    assertEquals(ChartType.BAR, mock.getChartType(args));
  }

  @Test
  void testGetTitles() throws InvalidQueryException {
    assertEquals("children", mock.getTitle(args));
    assertEquals("num", mock.getXTitle(args));
    assertEquals("value", mock.getYTitle(args));
  }

  @AfterAll
  static void cleanUp(){
    MasterData.clear();
  }
}