package com.tdat.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ManualConflictResolvePanel extends JFrame{
	public static JFrame f;  
	private JPanel manualConflictPanel = new JPanel();
	private JButton submitCorrection;
	private JLabel topHeading;
	private JLabel topHeading2;
	private JLabel middleHeading1;
	private JLabel middleHeading2;
	public static JTextField correctValue;

	
	public ManualConflictResolvePanel(ArrayList<Object> currentConflictData, String currentConflict){
	    f=new JFrame("Resolve Selected Conflict");  
		Dimension windowMinSize = new Dimension(800, 300);
		f.setMinimumSize(windowMinSize);
		f.setSize(windowMinSize);
		f.setResizable(true);
		f.setLocationRelativeTo(null);
		manualConflictPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		manualConflictPanel.setBackground(Color.white);
		GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setHgap(0);
		gridLayout.setVgap(0);
		manualConflictPanel.setLayout(gridLayout);
		topHeading = new JLabel("The conflict we are currently handling is: " + currentConflict);
		topHeading2 = new JLabel("The value which caused this conflict is: " + currentConflictData.get(2).toString());
		middleHeading1 = new JLabel("It is located on row number " + (Integer.parseInt(currentConflictData.get(0).toString()) + 1) + " of the upload");
		middleHeading2 = new JLabel("To fix this conflict, please enter a correct value below and click the 'Submit Correction' button");
		correctValue = new JTextField();
		submitCorrection = new JButton("Submit Correction");
		manualConflictPanel.add(topHeading);
		manualConflictPanel.add(topHeading2);
		manualConflictPanel.add(middleHeading1);
		manualConflictPanel.add(middleHeading2);
		manualConflictPanel.add(correctValue);
		manualConflictPanel.add(submitCorrection);
	    f.add(manualConflictPanel);
	    f.setVisible(true);
	    submitCorrection.addActionListener(new SubmitCorrectionButtonListener(currentConflictData, currentConflict));
	}
}
