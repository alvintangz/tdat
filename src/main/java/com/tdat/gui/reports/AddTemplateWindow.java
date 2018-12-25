package com.tdat.gui.reports;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.report.chart.templates.Template;

public class AddTemplateWindow {

	private final JFrame frame;
    public static String typeOfChartData;
	
    public AddTemplateWindow(Template template) {
        frame = new JFrame(App.appTitle + ": Use a Predefined Template");
        frame.setMinimumSize(new Dimension(600, 400));
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(new EmptyBorder(16, 16, 16, 16));
        mainPanel.setBackground(Color.white);
        GridBagConstraints layoutConstraints = new GridBagConstraints();

        // Section title
        JLabel sectionTitleLabel = new JLabel(
            "<html><h2 style='margin:0'>Use a Predefined Template</h2>"
                + "<small>Look at what the selected template does below, and proceed if you want to use it.</small></html>");
        layoutConstraints.fill = GridBagConstraints.HORIZONTAL;
        layoutConstraints.gridx = 0;
        layoutConstraints.gridy = 0;
        layoutConstraints.weightx = 1;
        layoutConstraints.weighty = 1;
        layoutConstraints.anchor = GridBagConstraints.NORTHWEST;
        mainPanel.add(sectionTitleLabel, layoutConstraints);

        // Details of template
        JLabel templateDetailsLabel = new JLabel(
            "<html><h3 style='margin:0'>" + template.getTitle() + "</h3>"
                + "<p>" + template.getDescription() + "</p></html>");
        layoutConstraints.gridy = 2;
        mainPanel.add(templateDetailsLabel, layoutConstraints);
        
        JButton proceedButton = new JButton("Proceed with Template Usage");
        layoutConstraints.gridy = 3;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(proceedButton, layoutConstraints);
        proceedButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(template.usable()) {
            		template.addWindow();
            	} else {
            		JOptionPane.showMessageDialog(null, "The template you have selected is unusable. It may be because "
        					+ "you have not uploaded all the necessary information yet.",
        					"Alert", JOptionPane.WARNING_MESSAGE);  
            	}
            	frame.setVisible(false);
            }
        });
        
        JButton ignoreButton = new JButton("Go Back");
        layoutConstraints.gridy = 4;
        layoutConstraints.insets = new Insets(10, 0, 0, 0);
        mainPanel.add(ignoreButton, layoutConstraints);
        ignoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	frame.setVisible(false);
            }
        });

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane);
        frame.setVisible(true);
    }
    
}
