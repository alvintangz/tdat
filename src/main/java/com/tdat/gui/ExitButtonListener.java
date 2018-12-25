package com.tdat.gui;

import com.tdat.data.ColumnNotFoundException;
import com.tdat.data.analysis.ServiceReceivedVerifier;
import com.tdat.data.template.InitialVisitTemplates;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.tdat.app.App;
import com.tdat.data.ConflictIdentifier;
import com.tdat.data.MasterData;

public class ExitButtonListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    if (ConflictIdentifier.manualConflictData.size() != 0) {
      JOptionPane.showMessageDialog(null, "Please resolve all manual conflicts before exiting", "Notice",
          JOptionPane.OK_OPTION);
      return;
    }

    this.uploadEditedFile();
    String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
    String historyEntry = "Date: " + timeStamp + "   Filename: " + App.selectedFile.getName() + "   Filetype: "
        + App.selectedFileType + "   Fiscal year: " + App.selectedYear;
    UploadPanel.DLM.addElement(historyEntry);
    App.fileUploadDict.put(historyEntry, App.selectedFile);
    ConflictWindow.f.dispose();
  }

  public void uploadEditedFile() {
    if (InitialVisitTemplates.contains(UploadButtonListener.newUpload.get(0).get("Template"))) {
      MasterData.setInitialVisitData(UploadButtonListener.newUpload);
    } else {
      MasterData.setServiceProvidedData(App.selectedYear, UploadButtonListener.newUpload);
    }

    // MasterData.printInitialVisitsData();
    // MasterData.printServiceProvidedData(App.selectedYear);
  }

}
