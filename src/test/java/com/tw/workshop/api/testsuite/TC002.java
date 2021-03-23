package com.tw.workshop.api.testsuite;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tw.workshop.api.base.RestAssuredBase;

import io.restassured.response.Response;

public class TC002 extends RestAssuredBase {
	
	@DataProvider(name="aut")
	public Object[][] getAutUrl(){
		return new Object[][] {
			{"https://www.enfamil.ca/"},
			{"https://www.enfamil.ca/user/login"},
			{"https://www.enfamil.ca/user/enrollment"},
			{"https://www.enfamil.ca/help-centre"},
			{"https://www.enfamil.ca/fr"},
			{"https://shop.enfamil.ca/"},
			{"https://www.enfamil.ca/products"},
			{"https://www.enfamil.ca/products/newborn"}
		};
	}
	
	@Test(dataProvider="aut")
	public void test_TC002(String url) {
		Response response = get(url);
		verifyResponseStatusCode(response, 200);		
		verifyResponseStatusLine(response, "HTTP/1.1 200 OK");		
		verifyResponseTime(response, 3000);
	}

}