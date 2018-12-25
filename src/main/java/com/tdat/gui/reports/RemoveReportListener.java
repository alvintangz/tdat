package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RemoveReportListener implements ActionListener {

	private JTable tableModel;

	public RemoveReportListener(JTable currentReportsTable) {
		this.tableModel = currentReportsTable;
	}

	public void actionPerformed(ActionEvent arg0) {
		new RemoveReportWindow(this.tableModel);
	}

}
