package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;
import com.tw.workshop.utils.Logs;

public class RegisterService extends SeleniumBase {
	
	public RegisterService enterTheFirstName(String fName) {
		type(getWebElement("xpath=//input[@formcontrolname='firstName']"), fName);
		return this;		
	}
	
	public RegisterService enterTheLastName(String lName) {
		type(getWebElement("xpath=//input[@formcontrolname='lastName']"), lName);
		return this;		
	}
	
	public RegisterService enterTheEmailAddress(String email) {
		type(getWebElement("xpath=//input[@formcontrolname='email']"), email);
		return this;		
	}
	
	public RegisterService enterTheMobileNumber(String mNumber) {
		type(getWebElement("xpath=//input[@formcontrolname='mobileNumber']"), mNumber);
		return this;		
	}
	
	public RegisterService enterThePassword(String password) {
		type(getWebElement("xpath=//input[@formcontrolname='password']"), password);
		return this;		
	}
	
	public RegisterService enterTheConfrimPassword(String cPassword) {
		type(getWebElement("xpath=//input[@formcontrolname='confirmPassword']"), cPassword);
		return this;		
	}
	
	public RegisterService uploadingProfilePic(String filePath) {
		type(getWebElement("xpath=//input[@formcontrolname='customFile']"), filePath);
		return this;		
	}
	
	public RegisterService readandAcceptTheTermsOfService() {
		click(getWebElement("linktext=terms of service"));
		switchToWindow(1);
		click(getWebElement("xpath=//button[text()=' I Accept & Close ']"));
		switchToWindow(0);
		return this;		
	}
	
	public RegisterService clickOnTheRegisterButton() {
		click(getWebElement("xpath=//button[text()='Register']"));
		return this;
	}
	
	public RegisterService ableToRegisterIntoFoodOrder() {
		Assert.assertEquals(getText(getWebElement("xpath=//div[@class='registerForm']/h2")),
				"You are registered please Login!!", "[FAILED]: Unable to registered");
		Logs.consoleLog("PASS", "[PASSED]: Successfully registered into the site");
		return this;
	}
	
	public LoginService clickOnTheLoginLink() {
		click(getWebElement("linktext=login"));
		return new LoginService();
	}

}