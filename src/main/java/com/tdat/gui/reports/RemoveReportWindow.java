package com.tdat.gui.reports;

import java.awt.HeadlessException;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.tdat.app.App;

public class RemoveReportWindow {

	public RemoveReportWindow(JTable tableModel) {
		if (tableModel.getSelectedRowCount() != 1) {
			return;
		}

		try {
			int confirmed = JOptionPane.showConfirmDialog(null,
					"Are you sure you want to delete report #" + (tableModel.getValueAt(tableModel.getSelectedRow(), 0)) + "?",
					"Confirmation Dialog", JOptionPane.YES_NO_OPTION);

			if (confirmed == JOptionPane.YES_OPTION) {
				App.reportsList.remove(tableModel.getSelectedRow());

				DefaultTableModel model = (DefaultTableModel) tableModel.getModel();
				model.removeRow(tableModel.getSelectedRow());
			}
		} catch (HeadlessException e) {
			e.printStackTrace();
		}
	}

}
