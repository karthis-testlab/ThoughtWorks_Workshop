package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.gui.base.SynchronizationWait;

public class LoginService extends SeleniumBase {
	
	public LoginService enterTheEmail(String email) {
		SynchronizationWait.waitUntilInVisibilityOfElement(getDriver(), getWebElement("xpath=//div[@class='overlay']"));
		type(getWebElement("xpath=//input[@formcontrolname='userName']"), email);
		return this;
	}
	
	public LoginService enterThePassword(String password) {		
		type(getWebElement("xpath=//input[@formcontrolname='password']"), password);
		return this;
	}
	
	public ProfileService clickOnTheSignInButton() {		
		click(getWebElement("xpath=//button[text()='Sign in']"));
		return new ProfileService();
	}

}
