package com.tdat.gui.reports;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.tdat.report.chart.templates.Template;
import com.tdat.report.chart.templates.TemplateRepository;

public class AddTemplateListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {

		Template chosenTemplate = TemplateRepository.templates.get(ReportsPanel.templatesDropdown.getSelectedIndex());
		new AddTemplateWindow(chosenTemplate);
	}

}
