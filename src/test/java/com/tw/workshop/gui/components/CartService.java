package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.gui.base.SynchronizationWait;
import com.tw.workshop.utils.ReadProperties;

public class CartService extends SeleniumBase {	
	
	public CartService shouldBeFoodItemsAddIntoCart(String selectFood, int foodCount) {
		SynchronizationWait.waitUntilInVisibilityOfElement(getDriver(), getWebElement("xpath=//div[@class='overlay']"));
		Assert.assertEquals(getText(getWebElement("xpath=//td[@data-th='Product']//h6")).equals(selectFood), true, "[FAILED]: Select food item was wrong");
		System.out.println("[PASSED]: Correct food item got selected");		
		Assert.assertEquals(getText(getWebElement("xpath=//td[@data-th='Quantity']")).equals(Integer.toString(foodCount)), true, "[FAILED]: Select food count was wrong");
		System.out.println("[PASSED]: Number of food count was correct");
		return this;
	}
	
	public CartService clickOnTheCheckOutButton() {
		click(getWebElement("linktext=Checkout"));
		return this;
	}
	
	public CartService shouldBeAbleToPlaceTheOrder() {		
		SynchronizationWait.waitUntilElementToBeClickable(getDriver(), getWebElement("xpath=//img[@alt='order placed']/following-sibling::h2"));
		String[] split = getText(getWebElement("xpath=//img[@alt='order placed']/following-sibling::h2")).split(" ");
		String orderId = split[1];
		ReadProperties.writeConfig("cart.orderId", orderId);
		return this;
	}
	
	public OrderHistoryService clickOnTheOrderHistory() {
		click(getWebElement("id=navbarDropdown"));
		click(getWebElement("linktext=Order History"));		
		return new OrderHistoryService();
	}

}