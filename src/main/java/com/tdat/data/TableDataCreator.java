package com.tdat.data;

import java.time.Year;
import java.util.ArrayList;

public class TableDataCreator {

	/*
	 * Creates a custom TableData object from the MasterData object using a list of
	 * column names and years.
	 * 
	 * @param master The master data object.
	 * 
	 * @param columns The list of columns to customize the returned TableData
	 * object.
	 * 
	 * @param years The list of years to customize the returned TableData object.
	 */
	public static TableData customize(MasterData master, ArrayList<String> columns, ArrayList<Year> years)
			throws YearNotFoundException {

		// Customized TableData object
		TableData custom = new TableData();

		// Loops through all the years that wanted to be constrained
		for (int indexYearList = 0; indexYearList < years.size(); indexYearList++) {
			// Throw a YearNotFoundException if year constraint does not exist in master
			if (!MasterData.yearExists(years.get(indexYearList))) {
				throw new YearNotFoundException(years.get(indexYearList).toString());
			} else {

				// Gets the current year data from the master
				TableData currentYearData = MasterData.getYearData(years.get(indexYearList));

				// Gets the current year data but in ArrayList form with each item being a visit
				// (VisitData)
				ArrayList<VisitData> allCurrentVisitData = (ArrayList<VisitData>) currentYearData.getVisitsData();

				// Loops through each visit the current year
				for (int indexVisitDataList = 0; indexVisitDataList < allCurrentVisitData
						.size(); indexVisitDataList++) {

					// Getting the visit data
					VisitData currentVisitData = allCurrentVisitData.get(indexVisitDataList);

					// New visit data to be inserted into customizable TableData object
					VisitData newVisitData = new VisitData();

					// Loop through all the columns in the visit data
					for (int indexColumnsList = 0; indexColumnsList < columns.size(); indexColumnsList++) {

						// If the column specified in the parameters is found, add its value to the new
						// visit data
						if (currentVisitData.columnDataExists(columns.get(indexColumnsList))) {
							newVisitData.addColumnData(columns.get(indexColumnsList),
									currentVisitData.getColumnData(columns.get(indexColumnsList)));
						}

					}

					// Add the visit data to the table
					custom.addVisitData(newVisitData);
				}
			}
		}

		// Return customized TableData object
		return custom;

	}

}
