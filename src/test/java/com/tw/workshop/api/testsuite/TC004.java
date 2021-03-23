package com.tw.workshop.api.testsuite;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.tw.workshop.api.deserialization.pojo.Location;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TC004 {

	@Test
	public void test_zipcode_value_for_us() {
		
		ResponseSpecification rSpec = new ResponseSpecBuilder().expectStatusCode(200).build();

		given()
		.when()
		.get("http://api.zippopotam.us/us/90210")
		.then()
		.spec(rSpec)
		.and()
		.assertThat()
		.statusCode(200)
		.body("places[0].'place name'", equalTo("Beverly Hills"));
		
		Location location = given()
		.when()
		.get("http://api.zippopotam.us/us/90210")
		.as(Location.class);
		
		Assert.assertEquals(location.getPlaces().get(0).getPlaceName(), "Beverly Hills");

	}

}