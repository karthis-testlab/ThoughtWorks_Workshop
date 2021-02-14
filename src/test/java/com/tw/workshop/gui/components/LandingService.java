package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.gui.base.SynchronizationWait;

public class LandingService extends SeleniumBase {
	
	public RegisterService clickOnTheRegisterLink() {
		SynchronizationWait.waitUntilInVisibilityOfElement(getDriver(), getWebElement("xpath=//div[@class='overlay']"));
		click(getWebElement("linktext=Register"));
		return new RegisterService();
	}

}