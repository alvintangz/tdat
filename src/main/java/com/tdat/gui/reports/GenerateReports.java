package com.tdat.gui.reports;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.tdat.app.App;
import com.tdat.report.ChartJSRequest;
import com.tdat.data.analysis.MasterDataStats;

public class GenerateReports {

	public GenerateReports() {
		if(App.reportsList.size() > 0) {
			String json = "{\"generationTime\": \"" + ChartJSRequest.getDate() + "\",";
			json += "\"totalPeople\": \"" + MasterDataStats.getPeopleCount() + "\",";
			json += "\"totalVisits\": \"" + MasterDataStats.getTotalVisits() + "\",";
			json += "\"years\": \"" + String.join(", ", MasterDataStats.getAllYearsAsString()) + "\",";
			json += "\"generateThese\":[";
			
			for(int i = 0; i < App.reportsList.size(); i++) {
				json += App.reportsList.get(i).toJson();
				json += ",";
			}

			// Exclude the last comma
			json = json.substring(0, json.length()-1);
			json += "]}";
			String fileLocation = ChartJSRequest.create(json);
			int response = JOptionPane.showConfirmDialog(null, "<html><small>Report generated at " + fileLocation + ". "
					+ "</small><br/><b>Do you want to view it in your browser?</b></html>", 
					"Report Generated.", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (response == JOptionPane.YES_OPTION) {
				if (Desktop.isDesktopSupported()) {
					try {
						File htmlFile = new File(fileLocation);
						Desktop.getDesktop().browse(htmlFile.toURI());
					} catch(IOException e) {
						JFrame errorFrame = new JFrame();
						JOptionPane.showMessageDialog(errorFrame, "Something went wrong! "
								+ "Report could not be displayed on browser.");
					}
				}
			}
		} else {
			JFrame errorFrame = new JFrame();
			JOptionPane.showMessageDialog(errorFrame, "To generate a complete report, please add some reports.");
		}
	}
	
}
