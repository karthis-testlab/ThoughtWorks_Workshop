package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.gui.base.SynchronizationWait;
import com.tw.workshop.utils.ReadProperties;

public class OrderHistoryService extends SeleniumBase {	
	
	public OrderHistoryService() {
		
	}	
	
	
	public OrderHistoryService shouldBeAbleToSeeOrderIdInHistory() {		
		SynchronizationWait.waitUntilvisibilityOfElement(getDriver(), getWebElement("xpath=//div[@class='card']//button/span"));
		Assert.assertEquals(getText(getWebElement("xpath=//div[@class='card']//button/span")).contains(ReadProperties.readProperties("cart.orderId")), true, "[FAILED]: Generated order id was incorrect");
		System.out.println("[PASSED]: Generated order id was correct one.");
		return this;
	}

}
