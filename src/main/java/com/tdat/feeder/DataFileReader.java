package com.tdat.feeder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DataFileReader {

	/*
	 * Converts the file at fileLocation to a list with each item being a delivered service
	 * and each delivered service having specific details in hash maps.
	 * @param fileLocation The location of a file in the hard drive.
	 * @returns ArrayList<HashMap<String, String>> A list of delivered services.
	 */
	public List<Map<String, String>> converter(File file) throws FileNotFoundException, IOException;

}
