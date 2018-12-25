package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.tdat.gui.GenericPanel;
import com.tdat.gui.reports.distribution.AddDistributionReportListener;
import com.tdat.gui.reports.trend.AddTrendsReportListener;
import com.tdat.report.chart.templates.TemplateRepository;

/*
 * A view for the reports panel in MainWindow.
 */
@SuppressWarnings("serial")
public class ReportsPanel extends GenericPanel {

  public static JTextField tdatqlQuery = new JTextField();

  public static DefaultTableModel tableModel = new DefaultTableModel() {
    @Override
    public boolean isCellEditable(int row, int column) {
      return false;
    }
  };

  public static JTextField getTdatqlQuery() {
    return tdatqlQuery;
  }
  
  public static JComboBox<String[]> templatesDropdown = new JComboBox(TemplateRepository.listTemplateTitles());
  public ReportsPanel() {
    // Panel Title
    panelTitle = "Reports";

    this.setLayout(new GridBagLayout());
    this.setBackground(Color.white);
    GridBagConstraints layoutConstraints = new GridBagConstraints();

    // Header section of the panel
    JLabel headerHTML = new JLabel(
        "<html><h2 style='margin:0'>Reports</h2>" + "<small>Generate graphical reports through this "
            + "interface. Reports will be displayed in your default browser.</small></html>");
    layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
    layoutConstraints.gridx = 0;
    layoutConstraints.gridy = 0;
    layoutConstraints.weightx = 1;
    layoutConstraints.weighty = 1;
    layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
    this.add(headerHTML, layoutConstraints);

    // Query section of the panel
    JLabel tdatqlHTML = new JLabel("<html><br/><h3 style='margin:0'>TDATQL</h3><small>"
        + "Enter a TDATQL query below to perform specific actions.<br/> Try 'distribution of children with children-Over-Time;num-children;value as bar'</small></html>");
    layoutConstraints.gridy = 1;
    layoutConstraints.ipady = 20;
    this.add(tdatqlHTML, layoutConstraints);

    layoutConstraints.gridy = 2;
    layoutConstraints.ipady = 5;
    this.add(tdatqlQuery, layoutConstraints);
    JButton submitQuery = new JButton("Run Query");
    layoutConstraints.gridy = 3;
    this.add(submitQuery, layoutConstraints);
    submitQuery.addActionListener(new TDATQLListener());

    // Reports to be generated list section of the panel
    JLabel currentReportsHTML = new JLabel("<html><br/><h3 style='margin:0'>Charts to be added</h3><small>"
        + "Below, you can see a list of charts to be added in the report.</small></html>");
    layoutConstraints.gridy = 4;
    layoutConstraints.ipady = 20;
    this.add(currentReportsHTML, layoutConstraints);

    String th[] = { "Id", "Title", "Graph", "Type" };
    JTable currentReportsTable = new JTable();
    tableModel.setDataVector(null, th);
    currentReportsTable.setModel(tableModel);
    JScrollPane scrollPane = new JScrollPane(currentReportsTable);
    scrollPane.setPreferredSize(new Dimension(600, 100));
    layoutConstraints.gridy = 5;
    layoutConstraints.ipadx = 0;
    layoutConstraints.ipady = 0;
    this.add(scrollPane, layoutConstraints);
    
    // Drop down to add templates
    layoutConstraints.gridy = 6;
    this.add(templatesDropdown, layoutConstraints);
    templatesDropdown.addActionListener(new AddTemplateListener());

    // Add button
    JButton addTrendsButton = new JButton("Add Trends Chart");
    layoutConstraints.gridy = 7;
    layoutConstraints.insets = new Insets(10, 0, 0, 0);
    this.add(addTrendsButton, layoutConstraints);
    addTrendsButton.addActionListener(new AddTrendsReportListener());

    // Add button
    JButton addDistributionButton = new JButton("Add Distribution Chart");
    layoutConstraints.gridy = 8;
    layoutConstraints.insets = new Insets(0, 0, 0, 0);
    this.add(addDistributionButton, layoutConstraints);
    addDistributionButton.addActionListener(new AddDistributionReportListener());

    // Remove button
    JButton removeButton = new JButton("Remove Chart");
    layoutConstraints.gridy = 9;
    this.add(removeButton, layoutConstraints);
    removeButton.addActionListener(new RemoveReportListener(currentReportsTable));

    // Generate Button
    JButton generateButton = new JButton("Generate Report");
    layoutConstraints.insets = new Insets(20, 0, 0, 0);
    layoutConstraints.gridy = 10;
    this.add(generateButton, layoutConstraints);
    generateButton.addActionListener(new GenerateReportListener());

  }

}
