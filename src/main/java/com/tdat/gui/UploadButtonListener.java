package com.tdat.gui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;

import com.tdat.app.App;
import com.tdat.data.*;
import com.tdat.feeder.Uploader;
import com.tdat.feeder.XLSXDataFileReader;

/**
 * When upload is clicked, performs sends info back to main.
 */
public class UploadButtonListener implements ActionListener {

  private JFileChooser jfc = new JFileChooser();
  private String selectedYear;
  private String selectedFileType;
  private File selectedFile;
  public static List<Map<String, String>> newUpload;

  public UploadButtonListener() {
    jfc.setCurrentDirectory(new File(System.getProperty("user.dir")));
    jfc.setPreferredSize(new Dimension(800, 500));
  }

  public void actionPerformed(ActionEvent e) {
    int returnValue = jfc.showOpenDialog(null);
    if (returnValue == JFileChooser.APPROVE_OPTION) {
      this.selectedFile = jfc.getSelectedFile();
      App.selectedFile = selectedFile;
      XLSXDataFileReader readNewUpload = new XLSXDataFileReader();
      try {
        newUpload = readNewUpload.converter(selectedFile);
      } catch (FileNotFoundException e1) {
        e1.printStackTrace();
      } catch (IOException e1) {
        e1.printStackTrace();
      }

      ConflictIdentifier conflictCheck = new ConflictIdentifier();
      //pass in the newUpload list here
      conflictCheck.checkForAutomaticConflicts(newUpload);
      conflictCheck.checkForManualConflicts(newUpload);
      int numManualConflicts = ConflictIdentifier.manualConflictData.size();
      int numAutomaticConflicts = conflictCheck.numAutomaticallyResolvedConflicts;

      if ((numManualConflicts > 0) || (numAutomaticConflicts > 0)) {
        new ConflictWindow();
      } else if ((numManualConflicts == 0) && (numAutomaticConflicts == 0)) {
        String timeStamp = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
            .format(Calendar.getInstance().getTime());
        String historyEntry =
            "Date: " + timeStamp + "   Filename: " + selectedFile.getName() + "   Filetype: "
                + App.selectedFileType +
                "   Fiscal year: " + App.selectedYear;
        UploadPanel.DLM.addElement(historyEntry);
        App.fileUploadDict.put(historyEntry, this.selectedFile);

        if (!Uploader.upload(App.selectedYear, App.selectedFile)) {
          System.out.println("File Not Found!");
        }
        MasterData.printServiceProvidedData(App.selectedYear);

      }
    }
  }

}
