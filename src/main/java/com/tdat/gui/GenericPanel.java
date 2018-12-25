package com.tdat.gui;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/*
 * GenericPanel to be used to extend panels in MainWindow
 */
@SuppressWarnings("serial")
public abstract class GenericPanel extends JPanel {

	public String panelTitle = "Generic";
	
	public GenericPanel() {
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.setLayout(new GridLayout(0, 1));
		this.setBackground(Color.white);
	}
	
	public String getPanelTitle() {
		return panelTitle;
	}
	
}
