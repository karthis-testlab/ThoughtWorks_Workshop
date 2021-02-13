package com.tw.workshop.api.base;

import java.io.File;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;

public class RestAssuredBase {

	public void setBaseUri(String bUri) {
		RestAssured.baseURI = bUri;
		System.out.println("[PASS]: Successfully set base uri of the rest api");
	}

	public RequestSpecification sendRequest() {
		return RestAssured.given().log().all();
	}

	public Response get(String url) {
		return sendRequest()
				.get(url);
	}
	
	public Response get(String url, String username, String password) {
		return sendRequest()
				.auth()
				.preemptive()
				.basic(username, password)
				.get(url);
	}
	
	public Response get(String url, String accessToken) {
		return sendRequest()
				.auth()
				.oauth2(accessToken)
				.get(url);		
	}

	public Response get(String url, Map<String, String> headers) {
		return sendRequest()
				.headers(headers)
				.get(url);
	}

	public Response get(String url, Map<String, String> headers, Object pathParamValue) {
		return sendRequest()
				.headers(headers)
				.pathParam("pathParam", pathParamValue)
				.get(url+"/{pathParam}");
	}

	public Response post(String url, File bodyFile) {
		return sendRequest()
				.body(bodyFile)
				.post(url); 		
	}

	public Response post(String url, Object object) {
		return sendRequest()
				.body(object)
				.post(url); 		
	}

	public Response post(String url, Map<String, String> headers, File bodyFile) {
		return sendRequest()
				.headers(headers)
				.body(bodyFile)
				.post(url); 		
	}

	public Response post(String url, Map<String, String> headers, Object object) {
		return sendRequest()
				.headers(headers)
				.body(object)
				.post(url); 		
	}

	public Response put(String url, File bodyFile) {
		return sendRequest()
				.body(bodyFile)
				.put(url);
	}

	public Response put(String url, File bodyFile, Object value) {
		return sendRequest()
				.pathParam("pathParam", value)
				.body(bodyFile)
				.put(url+"/{pathParam}");
	}
	
	public Response put(String url, Map<String, String> headers, String pathParamValue, Object object) {
		return sendRequest()
				.headers(headers)
				.pathParam("pathParam", pathParamValue)
				.body(object)
				.put(url+"/{pathParam}"); 		
	}

	public Response patch(String url, File bodyFile) {
		return sendRequest()
				.body(bodyFile)
				.patch(url);
	}

	public Response delete(String url) {
		return sendRequest()
				.delete(url);
	}
	
	public Response delete(String url, Map<String, String> headers, Object pathParamValue) {
		return sendRequest()
				.headers(headers)
				.pathParam("pathParam", pathParamValue)
				.delete(url+"/{pathParam}");
	}

	public void verifyContentType(Response response, String type) {
		response.then().assertThat()
		.contentType(type);
	}

	public boolean verifyResponseStatusCode(Response response, int code) {
		boolean bReturn;
		try {
			response.then().assertThat()
			.statusCode(code);
			bReturn = true;
			System.out.println("[PASS]: The response status code "+response.getStatusCode()+" is same as expected one "+code);
		} catch (Exception e) {	
			bReturn = false;
			System.out.println("[FAIL]: The response status code "+response.getStatusCode()+" wasn't same as expected one "+code);			
		}
		return bReturn;
	}

	public boolean verifyResponseStatusLine(Response response, String expectedStatusLine) {
		boolean bReturn;		
		try {
			response.then().assertThat()
			.statusLine(expectedStatusLine);
			bReturn = true;
			System.out.println("[PASS]: The response status line "+response.getStatusLine()+" is same as expected one "+expectedStatusLine);
		} catch (Exception e) {	
			bReturn = false;
			System.out.println("[FAIL]: The response status line "+response.getStatusLine()+" wasn't same as expected one "+expectedStatusLine);			
		}
		return bReturn;
	}

	public boolean verifyResponseTime(Response response, long time) {
		boolean bReturn;
		if(response.time() <= time) {
			bReturn = true;
			System.out.println("[PASS]: The time taken "+response.time() +" with in the expected SLA time "+time);			
		}else {
			bReturn = false;
			throw new RuntimeException("[FAIL]: The time taken "+response.time() +" is greater than expected SLA time "+time);		
		}
		return bReturn;
	}

	public void verifyResponseCount(Response response, int count) {
		response.then().assertThat()
		.body("size()", is(count));
		System.out.println("[PASS]: Response count "+response.jsonPath().getList("$").size()+" matches the value as expected "+count);
	}

	public boolean verifyContentWithKey(Response response, String jsonPath, Object expVal) {
		boolean bReturn;
		try {
			response.then().assertThat()
			.body(jsonPath, is(expVal));
			bReturn = true;
			System.out.println("[PASS]: The given json path "+jsonPath+" has the expected value "+expVal);
		} catch (Exception e) {
			bReturn = false;
			System.out.println("[FAIL]: The given json path "+jsonPath+" doesn't have the expected value "+expVal);
		}
		return bReturn;		 
	}	

}