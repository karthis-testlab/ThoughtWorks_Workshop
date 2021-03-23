package com.tw.workshop.api.testsuite;

import org.testng.annotations.Test;

import com.tw.workshop.api.base.RestAssuredBase;

import io.restassured.response.Response;

public class TC001 extends RestAssuredBase {

	@Test
	public void test_TC001() {
		setBaseUri("https://www.enfamil.ca");
		Response homePage = get("/");		
		verifyResponseStatusCode(homePage, 200);		
		verifyResponseStatusLine(homePage, "HTTP/1.1 200 OK");		
		verifyResponseTime(homePage, 3000);
		Response loginPage = get("/user/login");
		verifyResponseStatusCode(loginPage, 200);		
		verifyResponseStatusLine(loginPage, "HTTP/1.1 200 OK");		
		verifyResponseTime(loginPage, 3000);
		Response enrollmentPage = get("/user/enrollment");
		verifyResponseStatusCode(enrollmentPage, 200);		
		verifyResponseStatusLine(enrollmentPage, "HTTP/1.1 200 OK");		
		verifyResponseTime(enrollmentPage, 3000);	
	}

}