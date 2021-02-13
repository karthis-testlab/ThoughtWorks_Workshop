package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;

public class CartService extends SeleniumBase {
	
	public String orderId;
	
	public CartService shouldBeFoodItemsAddIntoCart(String selectFood, int foodCount) {
		waitFor();
		Assert.assertEquals(getText(getWebElement("xpath=//td[@data-th='Product']//h6")).equals(selectFood), true, "[FAILED]: Select food item was wrong");
		System.out.println("[PASSED]: Correct food item got selected");		
		Assert.assertEquals(getText(getWebElement("xpath=//td[@data-th='Quantity']")).equals(Integer.toString(foodCount)), true, "[FAILED]: Select food count was wrong");
		System.out.println("[PASSED]: Number of food count was correct");
		return this;
	}
	
	public CartService clickOnTheCheckOutButton() {
		waitFor();
		click(getWebElement("linktext=Checkout"));
		return this;
	}
	
	public CartService shouldBeAbleToPlaceTheOrder() {
		waitFor();
		String[] split = getText(getWebElement("xpath=//h2")).split("");
		orderId = split[1];
		return this;
	}
	
	public OrderHistoryService clickOnTheOrderHistory() {
		click(getWebElement("id=navbarDropdown"));
		click(getWebElement("linktext=Order History"));
		return new OrderHistoryService(orderId);
	}

}