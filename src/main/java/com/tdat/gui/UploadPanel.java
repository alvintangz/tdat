package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Year;
import java.util.Calendar;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;

import com.tdat.app.App;

/*
 * A view for the upload panel in MainWindow.
 */
@SuppressWarnings("serial")
public class UploadPanel extends GenericPanel {
	
	static DefaultListModel DLM = new DefaultListModel();
	JList historyList = new JList(DLM);
	
	public UploadPanel() {
		// Panel Title
		panelTitle = "Upload";
		
		JLabel instructionsHTML = new JLabel("<html><h2>Upload</h2><b>To upload a file, please select its related fiscal year, and the type of file.</b><br/></html>");
		this.add(instructionsHTML);
		
		// Fiscal year drop down only holds 21 fiscal years
		String[] fiscalYears = new String[21];
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int counter = 0;
		for (int i = currentYear; i > currentYear - 21; i--) {
			fiscalYears[counter] = (Integer.toString(i) + "-" + Integer.toString(i + 1));
			counter++;
		}
		JComboBox fiscalYearDropdown = new JComboBox(fiscalYears);
		this.add(fiscalYearDropdown);
		fiscalYearDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.selectedYear = (Year.of(Integer.parseInt((fiscalYearDropdown.getSelectedItem().toString()).substring(0,4))));
			}
		});
		
		
		String[] fileTypes = {"Excel (.xlsx)"};
		JComboBox fileTypeDropdown = new JComboBox(fileTypes);
		this.add(fileTypeDropdown);
		fileTypeDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				App.selectedFileType = fileTypeDropdown.getSelectedItem().toString();
			}
		});
		
		this.add(historyList);
		
		JButton uploadButton = new JButton("Upload");
		this.add(uploadButton);
		uploadButton.addActionListener(new UploadButtonListener());
	}
	
}
