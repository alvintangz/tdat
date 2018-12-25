package com.tdat.gui.reports.distribution;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.analysis.ServiceReceivedVerifier;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDistributionReportListener implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {
		new AddDistributionReportWindow();
	}

}
