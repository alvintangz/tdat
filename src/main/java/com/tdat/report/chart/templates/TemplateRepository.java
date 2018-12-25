package com.tdat.report.chart.templates;

import java.util.ArrayList;

public class TemplateRepository {

	public static ArrayList<Template> templates = new ArrayList<Template>();
	
	public static void add(Template template) {
		templates.add(template);
	}
	
	public static String[] listTemplateTitles() {
		ArrayList<String> listedTitles = new ArrayList<String>();
		for(Template template: templates) {
			listedTitles.add(template.getTitle());
		}
		String[] listedTitlesSA = new String[listedTitles.size()];
		return listedTitles.toArray(listedTitlesSA);
	}
	
}
