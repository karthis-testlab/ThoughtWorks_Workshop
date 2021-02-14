package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.gui.base.SynchronizationWait;

public class ProfileService extends SeleniumBase {
	
	public ProfileService shouldBeLoginIntoFoodSite(String firstName) {
		SynchronizationWait.waitUntilInVisibilityOfElement(getDriver(), getWebElement("xpath=//div[@class='overlay']"));
		Assert.assertEquals(getText(getWebElement("id=navbarDropdown")).contains(firstName), true, "[FALIED]: Profile name was wrong the given name is "+firstName);
		System.out.println("[PASSED]: Proflie name is correct name of the profiler is "+firstName);
		return this;
	}
	
	public HotelService clickOnTheHotelName(String hotelName) {
		click(getWebElement("xpath=//h5[@class='card-title' and contains(text(), '"+hotelName+"')]"));
		return new HotelService();
	}

}