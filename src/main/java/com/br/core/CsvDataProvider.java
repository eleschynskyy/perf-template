package com.br.core;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import au.com.bytecode.opencsv.CSVReader;

import com.br.data.objects.User;

public class CsvDataProvider {

	@DataProvider(name = "provideUser")
	public static Iterator<Object[]> provideUser(Method method) {
		List<Object[]> list = new ArrayList<Object[]>();
		String pathname = "test_data" + File.separator
				+ method.getDeclaringClass().getSimpleName() + "."
				+ method.getName() + ".csv";
		File file = new File(pathname);
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] keys = reader.readNext();
			if (keys != null) {
				String[] dataParts;
				while ((dataParts = reader.readNext()) != null) {
					Map<String, String> testData = new HashMap<String, String>();
					for (int i = 0; i < keys.length; i++) {
						testData.put(keys[i], dataParts[i]);
					}
					User user = new User()
						.setUsername(testData.get("username"))
						.setPassword(testData.get("password"))
						.setFirstName(testData.get("firstname"))
						.setLastName(testData.get("lastname"));
					list.add(new Object[] { user });
				}
			}
			reader.close();
		} catch (FileNotFoundException e) {
			throw new RuntimeException("File " + pathname + " was not found.\n"
					+ e.getStackTrace().toString());
		} catch (IOException e) {
			throw new RuntimeException("Could not read " + pathname
					+ " file.\n" + e.getStackTrace().toString());
		}
		return list.iterator();
	}
	
}
