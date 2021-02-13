package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;

public class OrderHistoryService extends SeleniumBase {
	
	public String orderId;
	
	public OrderHistoryService() {
		
	}
	
	public OrderHistoryService(String orderId) {
		this.orderId = orderId;
	}
	
	public OrderHistoryService shouldBeAbleToSeeOrderIdInHistory() {
		waitFor();
		Assert.assertEquals(getText(getWebElement("xpath=//div[@class='card']//button/span")).contains(orderId), true, "[FAILED]: Generated order id was incorrect");
		System.out.println("[PASSED]: Generated order id was correct one.");
		return this;
	}

}
