package com.tw.workshop.api.testsuite;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import com.tw.workshop.api.base.RestAssuredBase;
import com.tw.workshop.utils.JavaHelper;
import com.tw.workshop.utils.JsonHandler;
import com.tw.workshop.utils.ReadProperties;

import io.restassured.response.Response;

public class RetailStoreApiTest extends RestAssuredBase {
	
	@Test
	public void test_RetailStoreApiTest() {
		
		String emailId = JavaHelper.generateRandomEmailId();
		
		setBaseUri(ReadProperties.readConfig("api.base.uri"));
		
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("accept", "application/json");
		
		//User signup			
		JsonHandler.wirteJsonObject("SignUp", "email", emailId);
		Response register = post("/user/signup", headers, JsonHandler.readJsonObject("SignUp").toString());
		verifyResponseStatusCode(register, 201);
		verifyContentWithKey(register, "message", "user created");
		verifyResponseTime(register, 4000);
		
		//User login
		Response login = post("/user/login", headers, JsonHandler.readJsonObject("SignUp").toString());
		verifyResponseStatusCode(login, 200);
		verifyContentWithKey(login, "message", "Auth Successful");
		String token = login.getBody().jsonPath().get("token").toString();
		
		//Create product
		headers.put("Authorization", "Bearer "+token);
		Response createProduct = post("/products/", headers, JsonHandler.readJsonObject("Products").toString());
		verifyResponseStatusCode(createProduct, 201);
		verifyContentWithKey(createProduct, "message", "Created product successfully");
		String productId = createProduct.getBody().jsonPath().get("createdProduct.productId").toString();
		
		//Get all products
		Map<String, String> headers1 = new HashMap<String, String>();
		headers1.put("accept", "application/json");
		Response getAllProducts = get("/products/", headers1);
		verifyResponseStatusCode(getAllProducts, 200);
		
		//Get product details using product id
		Response getAProduct = get("/products", headers, productId);
		verifyResponseStatusCode(getAProduct, 200);
		verifyContentWithKey(getAProduct, "productId[0]", productId);
		verifyContentWithKey(getAProduct, "name[0]", "Tv_Karthikeyan");
		verifyContentWithKey(getAProduct, "price[0]", 35000);
		verifyContentWithKey(getAProduct, "soldBy[0]", "Samsung");
		verifyContentWithKey(getAProduct, "stock[0]", 20);
		
		//Create Order
		Response createOrder = post("/orders/", headers, "{ \"products\": [ { \"productId\": \""+productId+"\", \"quantity\": 1 } ]}");
		verifyResponseStatusCode(createOrder, 201);
		verifyContentWithKey(createOrder, "message", "Order stored");
		verifyContentWithKey(createOrder, "createdOrder.products[0].productId", productId);
		String orderId = createOrder.getBody().jsonPath().get("createdOrder.orderId").toString();
		
		//Get order lists
		Response getAllOrder = get("/orders/", headers);
		verifyResponseStatusCode(getAllOrder, 200);
		
		//Get one order details
		Response getOrder = get("/orders", headers, orderId);
		verifyResponseStatusCode(getOrder, 200);
		verifyContentWithKey(getOrder, "orderId", orderId);
		verifyContentWithKey(getOrder, "products[0].productId", productId);		
		
		//Update an order
		Response updateOrder = put("/orders", headers, orderId, "{ \"products\": [ { \"productId\": \""+productId+"\", \"quantity\": 6 } ]}");
		verifyResponseStatusCode(updateOrder, 200);
		verifyContentWithKey(updateOrder, "order.orderId", orderId);
		verifyContentWithKey(updateOrder, "order.products[0].productId", productId);
		verifyContentWithKey(updateOrder, "order.products[0].quantity", 6);
		
		//Delete an order
		Response deleteorderId = delete("/orders", headers, orderId);
		verifyResponseStatusCode(deleteorderId, 200);
		verifyContentWithKey(deleteorderId, "message", "order deleted");
		
		//Delete a product from the list
		Response deleteProductId = delete("/products", headers, productId);
		verifyResponseStatusCode(deleteProductId, 200);
		verifyContentWithKey(deleteProductId, "message", "Product deleted");
		
	}

}