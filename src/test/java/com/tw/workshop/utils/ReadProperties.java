package com.tw.workshop.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	public static String readConfig(String key) {		
		Properties config = new Properties();	
		String data;
		try {
			config.load(new FileInputStream(new File("src/test/resources/Config.properties")));
			data = config.getProperty(key);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("'Config.properties' file not found in the project src/test/resources/ path.");
		} catch (IOException e) {
			throw new RuntimeException("Unable to read the config.properties file.");
		}
		return data;		 
	}
	
	public static String writeConfig(String key, String data) {		
		Properties config = new Properties();		
		try {
			config.put(key, data);
			FileOutputStream out = new FileOutputStream(new File("src/test/resources/Config.properties"));
			config.store(out, "Properties file created......");
		} catch (FileNotFoundException e) {
			throw new RuntimeException("'Config.properties' file not found in the project src/test/resources/ path.");
		} catch (IOException e) {
			throw new RuntimeException("Unable to write the config.properties file.");
		}
		return data;		 
	}

	public static String readObjectRepositories(String fileName, String key) {		
		Properties prop = new Properties();
		String value = null;
		try {			
			prop.load(new FileInputStream(new File("src/test/resources/ObjectRepositories/"+fileName+".properties")));
			value = prop.getProperty(key);
		} catch (FileNotFoundException e) {
			throw new RuntimeException("'"+fileName+".properties' file not found in the project src/test/resources/ObjectRepositories/ path.");
		} catch (IOException e) {			
			throw new RuntimeException("Unable to read the "+fileName+".properties file.");
		}
		return value;
	}

}