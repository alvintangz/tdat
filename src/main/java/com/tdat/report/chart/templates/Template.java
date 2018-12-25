package com.tdat.report.chart.templates;

public interface Template {
	
	/*
	 * Gets the title for a template.
	 */
	public String getTitle();
	
	/*
	 * Gets the description for a template.
	 */
	public String getDescription();
	
	/*
	 * A window that appends chart(s) to master list of chart schemes after user input.
	 */
	public void addWindow();
	
	/*
	 * Returns True if template is usable, depending on the uploaded data that is available.
	 */
	public boolean usable();
	
}
