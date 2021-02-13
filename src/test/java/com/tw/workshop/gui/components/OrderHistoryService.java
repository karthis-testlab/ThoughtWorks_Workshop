package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.utils.ReadProperties;

public class OrderHistoryService extends SeleniumBase {	
	
	public OrderHistoryService() {
		
	}	
	
	
	public OrderHistoryService shouldBeAbleToSeeOrderIdInHistory() {
		waitFor();
		System.out.println(ReadProperties.readConfig("cart.orderId"));
		Assert.assertEquals(getText(getWebElement("xpath=//div[@class='card']//button/span")).contains(ReadProperties.readConfig("cart.orderId")), true, "[FAILED]: Generated order id was incorrect");
		System.out.println("[PASSED]: Generated order id was correct one.");
		return this;
	}

}
