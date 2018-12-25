package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateReportListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		new GenerateReports();
	}

}
