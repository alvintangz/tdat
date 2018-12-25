package com.tdat.query;

import com.tdat.query.handler.DistributionHandler;
import com.tdat.query.handler.Handler;
import com.tdat.report.chart.ChartScheme;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import jdk.nashorn.internal.runtime.ECMAException;

/**
 * A class to handle a given query from the user to create a graph
 */
public class CommandHandler {

  private static Map<String, Handler> handlers = new HashMap<>();

  /**
   * Add new handlers here
   */
  public static void setupHandlers() {
    registerHandler(new DistributionHandler());
  }


  /**
   * A method to add new handlers to the map of registered commands
   */
  private static void registerHandler(Handler newHandler) {
    handlers.put(newHandler.getKeyword(), newHandler);
  }

  public static boolean handle(String input) {
    String[] splitInput = input.trim().split("\\s");
    String command = splitInput[0];
    String[] args = Arrays.copyOfRange(splitInput, 1, splitInput.length);

    try {
      if (handlers.containsKey(command.toLowerCase())) {
        handlers.get(command).handle(args);
        return true;
      }
      throw new InvalidQueryException();
    } catch (InvalidQueryException e) {
      return false;
    }
  }

}
