package com.tdat.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tdat.app.App;
import com.tdat.data.ConflictIdentifier;

public class ConflictWindow {

	public static JFrame f;
	private JLabel topHeading = new JLabel(
			"<html>Our system has identified a few points in your upload data that need to be cleaned.<br/>In order to preserve data integrity, please review/resolve	conflicts listed below prior to continuing.</html>");

	private JPanel mainConflictPanel = new JPanel();
	private static final String frameTitle = "Conflicts Identified";

	private JLabel middleHeading = new JLabel("Automatically resolved conflicts - click to review all");
	private JLabel bottomHeading = new JLabel("Manual conflicts - select one conflict to resolve at a time");

	public static ArrayList<String> automaticConflictsArrayList = new ArrayList<String>();
	private String[] automaticConflictsArray = automaticConflictsArrayList
			.toArray(new String[automaticConflictsArrayList.size()]);
	private JComboBox automaticConflictsDropdown = new JComboBox(automaticConflictsArray);

	public static ArrayList<String> manualConflictsArrayList = new ArrayList<String>();
	public JComboBox manualConflictsDropdown = new JComboBox();
	public DefaultComboBoxModel model = new DefaultComboBoxModel(
			manualConflictsArrayList.toArray(new String[manualConflictsArrayList.size()]));
	private JButton resolveConflict = new JButton("Resolve Conflict");
	private JButton exitButton = new JButton("Exit Conflict Review Session");

	public ConflictWindow() {
		f = new JFrame(frameTitle);
		Dimension windowMinSize = new Dimension(1000, 300);
		f.setMinimumSize(windowMinSize);
		f.setSize(windowMinSize);
		f.setResizable(true);
		f.setLocationRelativeTo(null);

		f.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (JOptionPane.showConfirmDialog(f,
						"Are you sure you want to close this window without resolving the manual conflicts first? If yes, this file will not be uploaded. \nIf there are no manual conflicts left to resolve, please use the onscreen 'Exit' button to close this menu.",
						"Close window?", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
					App.selectedFile = null;
					ConflictWindow.manualConflictsArrayList.clear();
					ConflictWindow.automaticConflictsArrayList.clear();
					ConflictIdentifier.manualConflictData.clear();
					
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				}
			}
		});
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		mainConflictPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		mainConflictPanel.setBackground(Color.white);
		GridLayout gridLayout = new GridLayout(0, 1);
		gridLayout.setHgap(0);
		gridLayout.setVgap(0);
		mainConflictPanel.setLayout(gridLayout);
		mainConflictPanel.add(topHeading);
		mainConflictPanel.add(middleHeading);
		mainConflictPanel.add(automaticConflictsDropdown);
		mainConflictPanel.add(bottomHeading);
		manualConflictsDropdown.setModel(model);
		mainConflictPanel.add(manualConflictsDropdown);
		mainConflictPanel.add(resolveConflict);
		mainConflictPanel.add(exitButton);
		f.add(mainConflictPanel);
		f.setVisible(true);

		exitButton.addActionListener(new ExitButtonListener());
		resolveConflict.addActionListener(new ResolveConflictListener(this));
	}

	public static void main(String[] args) {
		new ConflictWindow();
	}

}
