package com.tdat.feeder;

import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tdat.app.App;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
// import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLSXDataFileReader implements DataFileReader {

    // https://stackoverflow.com/questions/12217047/how-to-determine-empty-row
    public static boolean isRowEmpty(Row row) {
        for (int c = row.getFirstCellNum(); c < row.getLastCellNum(); c++) {
            Cell cell = row.getCell(c);
            if (cell != null && cell.getCellType() != CellType.BLANK)
                return false;
        }
        return true;
    }

    /**
     * A Class that reads an iCare file into a java object.
     *
     * @param file file to read
     * @return a list(rows) of maps<ColumnName: entry>
     * @throws FileNotFoundException
     * @throws IOException
     */
    public List<Map<String, String>> converter(File file) throws FileNotFoundException, IOException {

        // To be returned: a list of all visits with each visit having a HashMap of
        // information of that visit
        List<Map<String, String>> allVisits = new ArrayList<>();
        // Holds all the keys for the HashMap (first row data)
        ArrayList<String> keys = new ArrayList<String>();

        // Cited Sources:
        // http://java67.com/2014/09/how-to-read-write-xlsx-file-in-java-apache-poi-example.html
        // https://poi.apache.org/apidocs/
        // https://www.callicoder.com/java-read-excel-file-apache-poi/

        FileInputStream xlsxFile = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(xlsxFile);

        // Gets the first sheet of the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        // Gets iterator to all the rows of the sheet
        Iterator<Row> rowIterator = sheet.rowIterator();

        // Get first row to get template name
        String iCareTemplate;
        Row titleRow = rowIterator.next();
        try {
            iCareTemplate = titleRow.getCell(0).getStringCellValue().substring(110).trim();
        } catch (Exception e) {
            iCareTemplate = App.EMPTY;
        }

        // Gather third row information and put it in a list
        rowIterator.next();
        Row firstRow = rowIterator.next();
        // Loops through each cell in third row
        Iterator<Cell> cellIteratorForFirst = firstRow.cellIterator();
        while (cellIteratorForFirst.hasNext()) {
            // Gets the cell object for this cell
            Cell cell = cellIteratorForFirst.next();
            keys.add(cell.getStringCellValue());
        }

        // Traverse each row of the XLSX file
        while (rowIterator.hasNext()) {
            // New HashMap
            HashMap<String, String> currentVisit = new HashMap<String, String>();

            // Put the template name into the cell;
            currentVisit.put("Template", iCareTemplate);

            // New counter
            int counter = 0;

            // Gets the row object for this loop
            Row row = rowIterator.next();

            if (isRowEmpty(row)) {
                break;
            }

            // while (cellIterator.hasNext()) {
            for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++) {

                // Gets the cell object for this cell
                // Cell cell = cellIterator.next();;
                Cell cell = row.getCell(cellNum);

                // Puts the current cell information into its row HashMap
                try {
                    if (cell.getCellType() == CellType.STRING) {
                        currentVisit.put(keys.get(counter), cell.getStringCellValue());
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        // Assuming all numerical values are integers
                        currentVisit.put(keys.get(counter), Integer.toString((int) cell.getNumericCellValue()));
                    }
                } catch (NullPointerException e) {
                    currentVisit.put(keys.get(counter), App.EMPTY);
                }

                // Increase counter by 1
                counter++;
            }

            // Adds the current row into the list
            allVisits.add(currentVisit);
        }

        // Close the workbook
        workbook.close();

        return allVisits;
    }

}
