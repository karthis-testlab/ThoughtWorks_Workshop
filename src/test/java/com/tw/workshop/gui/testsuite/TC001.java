package com.tw.workshop.gui.testsuite;

import java.io.File;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.tw.workshop.gui.components.UserActions;
import com.tw.workshop.utils.JavaHelper;

public class TC001 {
	
	public String emailId;
	public String mobileNumber;
	public String password;
	public String firstName;
	public String hotelName;
	public String selectFood;
	public int foodCount;
	public String orderId;
	public UserActions karthi = new UserActions();
	
	@BeforeSuite
	public void beforeSuite() {
		emailId = JavaHelper.generateRandomEmailId();
		mobileNumber = JavaHelper.generateRandomNumbers(10);
		password = "Test@2021";
		firstName = "FirstName1";
		hotelName = "Punjab Grill";
		foodCount = 2;
	}
	
	@BeforeClass
	public void beforeClass() {
		karthi.browseTheApplication().chrome().andHit("https://young-reef-96450.herokuapp.com/");
	}
	
	@Test
	public void test_TC001() {
		
		//1. Register new user
		karthi.shouldBeInTheLandingPage().clickOnTheRegisterLink()
		.enterTheFirstName(firstName).enterTheLastName("LastName1")
		.enterTheEmailAddress(emailId).enterTheMobileNumber(mobileNumber)
		.enterThePassword(password).enterTheConfrimPassword(password)
		.uploadingProfilePic(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Screenshots" + File.separator+ "download.jpg")
		.readandAcceptTheTermsOfService().clickOnTheRegisterButton().ableToRegisterIntoFoodOrder()
		.clickOnTheLoginLink();
		
		//2. Login with user credentials
		karthi.shouldBeInTheLoginPage().enterTheEmail(emailId).enterThePassword(password).clickOnTheSignInButton()
		.shouldBeLoginIntoFoodSite(firstName);
		
	}

}