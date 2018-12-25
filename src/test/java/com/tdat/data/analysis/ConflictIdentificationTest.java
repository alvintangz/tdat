package com.tdat.data.analysis;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.tdat.gui.*;
import com.tdat.data.*;



public class ConflictIdentificationTest {
	public static List<Map<String,String>> upload;
	public static Map<String,String> testMap1;
	public static Map<String,String> testMap2;
	
	@BeforeAll
	static void setup() {
		upload = new ArrayList<Map<String,String>>();
		testMap1 = new HashMap<String,String>();
		testMap2 = new HashMap<String,String>();
	}

	@BeforeEach
	void clearData() {
		ConflictIdentifier.manualConflictData.clear();
		ConflictIdentifier.numAutomaticallyResolvedConflicts = 0;

		upload.clear();
		testMap1.clear();
		testMap2.clear();
	}

	@Test
	@DisplayName("Test creating trailing white space entries in the array of automatically resolved conflicts")
	void automaticConflictTrailingWhiteSpaceTest(){
		ConflictIdentifier automaticConflictCheck = new ConflictIdentifier();
		testMap1.put("AA", " 1 ");
		testMap1.put("AB", "bob");
		testMap2.put("AA", "Mistake");
		testMap2.put("AB", "2");

		upload.add(testMap1);
		upload.add(testMap2);

		automaticConflictCheck.checkForAutomaticConflicts(upload);

	    assertEquals(4, ConflictIdentifier.numAutomaticallyResolvedConflicts);
	}
	
	
	@Test
	@DisplayName("Test checking for number in a text column")
	void manualConflictNumInTextTest(){
	    ConflictIdentifier manualConflictCheck1 = new ConflictIdentifier();
		testMap1.put("AC", " 1 ");
		testMap1.put("ASDA", "bobby");
		testMap2.put("SSAA", "Mistake2");
		testMap2.put("ABBB", "2");

		upload.add(testMap1);
		upload.add(testMap2);

		manualConflictCheck1.checkForManualConflicts(upload);
		assertEquals(1, ConflictIdentifier.manualConflictData.size());
		
		List<Object> actual = ConflictIdentifier.manualConflictData.get("Conflict Type: TypeConflict, Conflicting Value: 2");
		List<Object> expected = Arrays.asList("1", "ABBB", 2);
		assertEquals(expected.toString(), actual.toString());
	}
	
	@Test
	@DisplayName("Test checking for text in a number column")
	void manualConflictTextInNumTest(){
	    ConflictIdentifier manualConflictCheck2 = new ConflictIdentifier();
		testMap1.put("JHHJKJH", " 1 ");
		testMap1.put("ASDASD", "bobby");
		testMap2.put("MBNB", "Mistake3");
		testMap2.put("QWEQWE", "8");

		upload.add(testMap1);
		upload.add(testMap2);
		
		manualConflictCheck2.checkForManualConflicts(upload);
		assertEquals(1, ConflictIdentifier.manualConflictData.size());

		List<Object> actual = ConflictIdentifier.manualConflictData.get("Conflict Type: TypeConflict, Conflicting Value: 8");
		List<Object> expected = Arrays.asList("1", "QWEQWE", 8);
		assertEquals(expected.toString(), actual.toString());
	}
	
}
