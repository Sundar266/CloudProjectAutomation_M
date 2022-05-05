package com.DocReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop;

	public ConfigReader() {
		String configPath = "C:\\Users\\2126765\\eclipse-workspace\\CloudProjectAutomation\\src\\test\\java\\com\\DataDocs\\Config.properties";
		prop = new Properties();
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(configPath));
			prop.load(bReader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("File not found");
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}

	public String getKeyProperty(String Key) {
		String Value = prop.getProperty(Key);
		return Value;
	}
}
