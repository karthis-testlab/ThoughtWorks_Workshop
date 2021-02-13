package com.tw.workshop.utils;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

public class ReadExcel {

	Connection connection;
	Recordset recordset;

	public ReadExcel(String fileName) {
		Fillo filo = new Fillo();
		try {
			filo.getConnection("src/test/resources/Fixtures/"+fileName);
		} catch (FilloException e) {
			throw new RuntimeException("Exception throwed from fillo library: "+e.getMessage());
		}
	}

	public Object[][] readExcelData(String sheetName){
		Object[][] data = null;
		try {
			recordset = connection.executeQuery("SELECT * FROM "+sheetName);
			data = new Object[recordset.getCount()][recordset.getFieldNames().size()];
			int rowCount = 0;
			while (recordset.next()) {
				for (int columnCount = 0; columnCount < recordset.getFieldNames().size(); columnCount++) {
					data[rowCount][columnCount] = recordset.getField(recordset.getFieldNames().get(columnCount));					
				}	
				rowCount++;
			}
		} catch (FilloException e) {
			throw new RuntimeException("Exception throwed from fillo library: "+e.getMessage());
		}
		return data;
	}

}