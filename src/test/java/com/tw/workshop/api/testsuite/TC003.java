package com.tw.workshop.api.testsuite;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003 {

	public static void main(String[] args) {
		RequestSpecification rspec = new RequestSpecBuilder().setBaseUri("https://reqres.in/").build();
		Response response = RestAssured.given().spec(rspec).get("api/users?page=1");
		System.out.println(response.asPrettyString());
	}

}