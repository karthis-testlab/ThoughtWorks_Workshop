package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;

public class LandingService extends SeleniumBase {
	
	public RegisterService clickOnTheRegisterLink() {
		click(getWebElement("linktext=Register"));
		return new RegisterService();
	}

}