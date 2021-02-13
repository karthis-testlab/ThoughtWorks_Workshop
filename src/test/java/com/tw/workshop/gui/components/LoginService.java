package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;

public class LoginService extends SeleniumBase {
	
	public LoginService enterTheEmail(String email) {
		waitForPageLoaded();
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
