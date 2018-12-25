package com.tdat.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import com.tdat.data.ConflictIdentifier;

public class SubmitCorrectionButtonListener implements ActionListener{
	
	private String correctValue;
	private ArrayList<Object> currentConflictData;
	private String currentConflict;
	
	public SubmitCorrectionButtonListener(ArrayList<Object> conflictData, String currentConflict){
		currentConflictData = conflictData;
		this.currentConflict = currentConflict;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.correctValue = ManualConflictResolvePanel.correctValue.getText();
		UploadButtonListener.newUpload.get((int) currentConflictData.get(0)).put((String) currentConflictData.get(1), correctValue);
		ResolveConflictListener.conflictWindowInstance.manualConflictsArrayList.remove(currentConflict);
		ResolveConflictListener.conflictWindowInstance.model.removeElement(currentConflict);
		ConflictIdentifier.manualConflictData.remove(currentConflict);

		ManualConflictResolvePanel.f.dispose();
	}

}
