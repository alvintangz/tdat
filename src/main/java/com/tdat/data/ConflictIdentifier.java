package com.tdat.data;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tdat.app.App;
import com.tdat.gui.ConflictWindow;

public class ConflictIdentifier {
	public static int numAutomaticallyResolvedConflicts = 0;
	public static Map<String, ArrayList<Object>> manualConflictData = new HashMap<String, ArrayList<Object>>();
	
	public void checkForAutomaticConflicts(List<Map<String, String>> potentialUpload){
		
		//Check for trailing whitespace
		for(Map<String,String> currentMap: potentialUpload){
			List<String> listOfValues = new ArrayList<String>(currentMap.values());
			for(String currentString : listOfValues){
				trailingWhitespaceCheck(currentMap, currentString);
			}
		}
	}
	
	public void trailingWhitespaceCheck(Map<String,String> currentMap, String currentString){
		if(currentString.trim() != null && currentString != App.EMPTY){
			numAutomaticallyResolvedConflicts++;
			//add to list of automatically resolved conflict messages
			ConflictWindow.automaticConflictsArrayList.add(
					this.generateAutomaticallyResolvedConflictMessage(App.selectedFile, "TrailingWhitespaceConflict", currentString, currentString.trim()));
			currentMap.replace(currentMap.get(currentString), currentString.trim());
		}
	}
	
	public String generateAutomaticallyResolvedConflictMessage(File selectedFile, String conflictType, String oldValue, String newValue) {
		if (selectedFile == null) {
			return "Conflict Type: " +  conflictType + ", Old Value: " + oldValue + ", New Value: " + newValue;
		}
		
		return "Upload: " + selectedFile.getName() + ", Conflict Type: " +  conflictType + ", Old Value: " + oldValue + ", New Value: " + newValue;
	}
	
	public void checkForManualConflicts(List<Map<String, String>> potentialUpload){
		checkForTypeDifferences(potentialUpload);
	}
	
	public void checkForTypeDifferences(List<Map<String, String>> potentialUpload){
		HashMap<String, Object> typeReferenceDict = new HashMap<String, Object>();
		for(String columnName: potentialUpload.get(0).keySet()){
			//populate the type reference dictionary declared above
			if(isNumeric(potentialUpload.get(0).get(columnName))){
				typeReferenceDict.put(columnName, Integer.class.getName());
			} else {
				typeReferenceDict.put(columnName, potentialUpload.get(0).get(columnName).getClass().getName());
			}
		}
		for(Map<String, String> rowEntry: potentialUpload){
			int rowMapIndex = potentialUpload.indexOf(rowEntry);
			for(String key: rowEntry.keySet()){
				boolean checkForNumericInText = isNumeric(rowEntry.get(key)) && typeReferenceDict.get(key) != Integer.class.getName();
				boolean checkForTextInNumeric = !(isNumeric(rowEntry.get(key))) && (typeReferenceDict.get(key) == Integer.class.getName()) && (rowEntry.get(key) != App.EMPTY);
				if(checkForNumericInText){
						handleNewTypeConflict(rowMapIndex, key, rowEntry);
				} else if(checkForTextInNumeric){
						handleNewTypeConflict(rowMapIndex, key, rowEntry);
				}
			}
		}

	}
	
	public static boolean isNumeric(String columnName){  
	  try {  
	    int d = Integer.parseInt(columnName);  
	  }  
	  catch(NumberFormatException nfe){  
	    return false;  
	  }  
	  return true;  
	}
	
	public String generateManualResolveConflictMessage(File selectedFile, String conflictType, String oldValue) {
		if (selectedFile == null) {
			return "Conflict Type: " +  conflictType + ", Conflicting Value: " + oldValue;
		}
		
		return "Upload: " + selectedFile.getName() + ", Conflict Type: " +  conflictType + ", Conflicting Value: " + oldValue;
	}
	
	public void handleNewTypeConflict(int rowMapIndex, String key, Map<String, String> rowEntry){
		String conflictMessage = generateManualResolveConflictMessage(App.selectedFile, "TypeConflict", rowEntry.get(key));
		ConflictWindow.manualConflictsArrayList.add(conflictMessage);
		
		ArrayList<Object> conflictData = new ArrayList<Object>();
		conflictData.add(rowMapIndex);
		conflictData.add(key);
		conflictData.add(rowEntry.get(key));
		manualConflictData.put(conflictMessage, conflictData);
	}
	
}
