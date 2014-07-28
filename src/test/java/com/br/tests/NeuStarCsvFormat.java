package com.br.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NeuStarCsvFormat {
	
	private static final String DEFAULT_PASSWORD = "Bravais000";

	public static void main(String[] args) throws IOException {
		String filename = "fake_users_TEST";
		String path = "D:/Xyleme/performance/products/bravais/jmx/stuff/";
		String pathname = path + filename + ".csv";
		File file = new File(pathname);
		String pathnameConverted = path + filename + "_NeuStar.csv";
		File fileConverted = new File(pathnameConverted);
		if (fileConverted.exists()) {
			fileConverted.delete();
		}
		FileWriter writer = new FileWriter(fileConverted);
		try {
			writer.write("\"username\",\"password\"\n");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			String[] data;
			while ((line = reader.readLine()) != null) {
				data = line.split("\\s");
				writer.write("\"" + data[1] + "\",\"" + DEFAULT_PASSWORD + "\"\n");
			}
			reader.close();
			writer.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname
					+ " file.\n" + e.getStackTrace().toString());
		}
	}

}
