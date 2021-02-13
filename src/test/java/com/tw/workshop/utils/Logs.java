package com.tw.workshop.utils;

public class Logs {

	public static void consoleLog(String status, String content) {
		if(status.equalsIgnoreCase("PASS")) {
			System.out.println("[PASSED]: "+ content);
		} else if(status.equalsIgnoreCase("FAIL")) {
			throw new RuntimeException("[FAILED]: "+ content);
		} else if(status.equalsIgnoreCase("WARN")) {
			throw new RuntimeException("[WARNING]: "+ content);
		}
	}

}