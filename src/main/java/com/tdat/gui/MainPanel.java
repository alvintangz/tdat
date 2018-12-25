package com.tdat.gui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;

import com.tdat.app.App;

@SuppressWarnings("serial")
public class MainPanel extends GenericPanel {
	
	public MainPanel() {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		panelTitle = "Main";
		
		String mainHTML = "<html>"
				+ "<h1>" + App.appTitle + "</h1>"
				+ "<hr/>"
				+ "<br/>"
				+ "<p><b>Greetings!</b> Here is some basic information on how to get started! </p><br/>" +
				"<p><b>Step 1.</b> Go to the 'Upload' tab to upload files, while specifying the file type and the file's related fiscal year for each file.</p><br/>" +
				"<p><b>Step 2.</b> Go to the 'Reports' tab to generate a report with visual charts.</p>\r\n" +
				"<p>Add charts, and modify the report using the TDATQL (TDAT Query Language).</p>" +
				"<p>Add distribution, and trend charts of a specific column.</p>" +
				"<p>The templates dropdown below the 'Reports to be generated' table gives you the option to add charts that are frequently used.</p>" +
				"<p>Finally, generate a report in your browser using the 'Generate Report' button.</p><br/>" +
				"<p><b>Optional:</b> Go to the 'Public data' tab to add charts with public data in the report for comparison.</p>" +
				"<p>Make sure you do this before generating the report.</p>" +
				"<br/>\r\n" + 
				"<small>\r\n" + 
				"<b>Acknowledgements:</b><br/>\r\n" + 
				"Thierry Sans - Professor<br/>\r\n" + 
				"Bekzod Tursunov - Developer<br/>\r\n" + 
				"Radu Laudat - Developer<br/>\r\n" + 
				"Alvin Tang - Developer<br/>\r\n" + 
				"Wesley Ma - Developer\r\n" + 
				"</ul>\r\n" + 
				"</b>\r\n";
		
		JLabel mainLabel = new JLabel(mainHTML);
		this.add(mainLabel);
	}
	
}
