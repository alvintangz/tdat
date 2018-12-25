package com.tdat.feeder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;

import com.tdat.app.App;
import com.tdat.data.MasterData;
import com.tdat.data.TableData;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UploaderTest {
    File testICare1 = new File("./src/test/resources/Test 1.xlsx");
    File testICare2 = new File("./src/test/resources/Test 2.xlsx");

    @BeforeAll
    public static void setUp() {
        MasterData.clear();
    }

    @Test
    @DisplayName("Test uploading single iCare file")
    void uploadOneFile() {
        App.selectedFileType = ".xlsx";
        if (!Uploader.upload(Year.of(2018), testICare1)) {
            fail("File not found!");
        }

        TableData data = MasterData.getYearData(Year.of(2018));
        assertEquals(new HashSet<>(Arrays.asList("children", "AA", "AB", "AC", "AD", "Template")),
                new HashSet<>(data.getColumnList()));
    }

    @Test
    @DisplayName("Test uploading two iCare files")
    void uploadTwoFiles() {
        App.selectedFileType = ".xlsx";
        if (!Uploader.upload(Year.of(2018), testICare1) || !Uploader.upload(Year.of(2018), testICare2)) {
            fail("File not found!");
        }

        TableData data = MasterData.getYearData(Year.of(2018));
        assertEquals(
                new HashSet<>(Arrays.asList("AA", "AB", "AC", "AD", "BA", "BB", "BD", "BC", "children", "Template")),
                new HashSet<>(data.getColumnList()));
    }

    @AfterAll
    public static void cleanUp() {
        MasterData.clear();
    }
}