package com.tdat.report.chart.templates;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.data.analysis.ServiceReceivedVerifier;
import com.tdat.gui.reports.ReportsPanel;
import com.tdat.report.chart.ChartScheme;
import com.tdat.report.chart.ChartType;
import com.tdat.report.chart.DistributionChartScheme;
import com.tdat.report.chart.ServiceReceivedChartScheme;
import com.tdat.report.chart.TrendChartScheme;

public class ServiceReceivedTemplate implements Template {

	private static final String title = "Services Received Template";
	private static final String description = "Add a chart to display the amount of people who receive the services " + 
	"they were referred to.";
	private static final String mainTitle = "Services Referred vs. Services Received";
	private static final String yAxis = "Number of Referred/Received";
	private static final String xAxis = "Service";
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}

	public void addWindow() {
		JFrame frame = new JFrame(App.appTitle + ": Use " + title);;
		frame.setMinimumSize(new Dimension(400, 450));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        mainPanel.setBackground(Color.white);
        GridBagConstraints layoutConstraints = new GridBagConstraints();
        
        // Section title
        JLabel sectionTitleLabel = new JLabel(
            "<html><h2 style='margin:0'>" + title + "</h2></html>");
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weightx = 1;
        layoutConstraints.weighty = 1;
        layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(sectionTitleLabel, layoutConstraints);

        // Style of graph Label and Selection
        JLabel styleOfGraphLabel = new JLabel("Style of Graph");
        layoutConstraints.gridy = 2;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(styleOfGraphLabel, layoutConstraints);
        ChartType[] styleOfGraph = {ChartType.BAR, ChartType.LINE};
        JComboBox<ChartType[]> styleOfGraphsDropdown = new JComboBox(styleOfGraph);
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = new Insets(5, 0, 0, 0);
        mainPanel.add(styleOfGraphsDropdown, layoutConstraints);
        
        JButton addButton = new JButton("Add Graphs by Template");
        layoutConstraints.gridy = 4;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(addButton, layoutConstraints);
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ChartType type = ChartType.getChartTypeFromString(
            			styleOfGraphsDropdown.getSelectedItem().toString());
            	addReports(type);
            	frame.setVisible(false);
            }
        });
        
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
	}
	
	private void addReports(ChartType graphType) {
		// Important chart scheme for this template, main purpose
		ChartScheme scheme1 = new ServiceReceivedChartScheme(graphType);
		scheme1.setMainTitle(mainTitle);
		scheme1.setXTitle(xAxis);
		scheme1.setYTitle(yAxis);
		App.reportsList.add(scheme1);
    	String[] row1 = {
    			Integer.toString(MasterData.reportId.incrementAndGet()),
    			mainTitle,
    			scheme1.getGraphType().getPrettyJsonCode(),
    			"Services Received (Custom)"
    	};
        ReportsPanel.tableModel.addRow(row1);
    	
        // Added this to demonstrate that templates can involve multiple chart schemes
		ChartScheme scheme2 = new DistributionChartScheme("Template", ChartType.BAR);
		scheme2.setMainTitle("Distribution of Types of Services");
		scheme2.setXTitle("Services");
		scheme2.setYTitle("Number of Services");
		App.reportsList.add(scheme2);
    	String[] row2 = {
    			Integer.toString(MasterData.reportId.incrementAndGet()),
    			"Distribution of Types of Services",
    			scheme2.getGraphType().getPrettyJsonCode(),
    			"Distribution"
    	};
        ReportsPanel.tableModel.addRow(row2);
        
		ChartScheme scheme3 = new TrendChartScheme("Template", ChartType.LINE);
		scheme3.setMainTitle("Trend of Types of Services");
		scheme3.setXTitle("Years");
		scheme3.setYTitle("Number of Services");
		App.reportsList.add(scheme3);
    	String[] row3 = {
    			Integer.toString(MasterData.reportId.incrementAndGet()),
    			"Trend of Types of Services",
    			scheme3.getGraphType().getPrettyJsonCode(),
    			"Trend"
    	};
        ReportsPanel.tableModel.addRow(row3);
        
	}

	public boolean usable() {
		Boolean usable = true;
		
		if(ServiceReceivedVerifier.getReferralsCount().size() == 0) {
			usable = false;
		}
		
		return usable;
	}
}
