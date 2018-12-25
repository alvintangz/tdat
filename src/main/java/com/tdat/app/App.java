package com.tdat.app;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.tdat.data.ConflictIdentifier;
import com.tdat.data.PublicDataCache;
import com.tdat.gui.MainWindow;
import com.tdat.query.CommandHandler;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.templates.ServiceReceivedTemplate;
import com.tdat.report.chart.templates.TemplateRepository;

public class App {
	public static final String EMPTY = "N/A";
	public static File selectedFile;
	public static Year selectedYear = Year.of(2018);
	public static String selectedFileType = ".xlsx";
	public static HashMap<String, File> fileUploadDict = new HashMap<String, File>();
	public static ArrayList<ChartScheme> reportsList = new ArrayList<ChartScheme>();
	public static String appTitle = "TEQ data Aggregation Tool (TDAT)";

	public static void main(String[] args) {
		CommandHandler.setupHandlers();

		try {
			// Set cross-platform Java L&F (also called "Metal")
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}

		PublicDataCache.init();
		
		TemplateRepository.add(new ServiceReceivedTemplate());

		new MainWindow();
	}

}
