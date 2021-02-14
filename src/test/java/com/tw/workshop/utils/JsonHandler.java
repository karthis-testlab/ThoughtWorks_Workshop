package com.tw.workshop.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonHandler {	
	
	@SuppressWarnings({ "unchecked", "resource" })
	public static void wirteJsonObject(String fileName, String key, String value) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			FileReader reader = new FileReader("src/test/resources/Fixtures/"+fileName+".json");
			Object obj = jsonParser.parse(reader);
			jsonObject = (JSONObject) obj;			
			jsonObject.put(key, value);	
			FileWriter writer = new FileWriter("src/test/resources/Fixtures/"+fileName+".json");
			writer.write(jsonObject.toJSONString());
			writer.flush();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParseException e) {			
			e.printStackTrace();
		}	
	}	
	
	public static JSONObject readJsonObject(String fileName) {
		JSONParser jsonParser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			FileReader reader = new FileReader("src/test/resources/Fixtures/"+fileName+".json");
			Object obj = jsonParser.parse(reader);
			jsonObject = (JSONObject) obj;					
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ParseException e) {			
			e.printStackTrace();
		}
		return jsonObject;		
	}	

}