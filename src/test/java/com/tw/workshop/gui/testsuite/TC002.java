package com.tw.workshop.gui.testsuite;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.tw.workshop.utils.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC002 {

	RemoteWebDriver driver;

	public String emailId;
	public String password;
	public String firstName;
	public String hotelName;
	public String selectFood;
	public int foodCount;
	public String orderId;	

	public static String generateRandomEmailId() {
		String emailName = RandomStringUtils.randomAlphabetic(6);
		return emailName + "@" + "gmail.com";
	}
	
	public void waitUntilvisibilityOfElement(RemoteWebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementToBeClickable(RemoteWebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitUntilInVisibilityOfElement(RemoteWebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}		

	@BeforeSuite
	public void beforeSuite() {
		emailId = generateRandomEmailId();
		password = "Test@2021";
		firstName = "FirstName1";
		hotelName = "Punjab Grill";
		foodCount = 2;
		
		Reporter.createInstance("result");		
	}

	@BeforeClass
	public void beforeClass() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		Reporter.createTestCases(getClass().getName());
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {
		Reporter.createTest(method.getName());
	}

	@Test(priority = 0)
	public void register_new_user() {
		driver.get("https://young-reef-96450.herokuapp.com/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
		WebElement register = driver.findElementByLinkText("Register");
		driver.executeScript("arguments[0].click()", register);
		waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
		driver.findElementByXPath("//input[@formcontrolname='firstName']").sendKeys(firstName);
		driver.findElementByXPath("//input[@formcontrolname='lastName']").sendKeys("LastName1");
		driver.findElementByXPath("//input[@formcontrolname='email']").sendKeys(emailId);
		driver.findElementByXPath("//input[@formcontrolname='mobileNumber']").sendKeys("9876543210");
		driver.findElementByXPath("//input[@formcontrolname='password']").sendKeys(password);
		driver.findElementByXPath("//input[@formcontrolname='confirmPassword']").sendKeys("Test@2021");
		driver.findElementByXPath("//input[@formcontrolname='customFile']")
		          .sendKeys(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "Screenshots" + File.separator+ "download.jpg");
		WebElement terms = driver.findElementByLinkText("terms of service");
		waitUntilvisibilityOfElement(driver, terms);
		driver.executeScript("arguments[0].click()", terms);
		Set<String> windows = driver.getWindowHandles();
		ArrayList<String> list = new ArrayList<String>(windows);
		driver.switchTo().window(list.get(1));		
		WebElement accept = driver.findElementByXPath("//button[text()=' I Accept & Close ']");
		waitUntilvisibilityOfElement(driver, accept);
		driver.executeScript("arguments[0].click()", accept);
		driver.switchTo().window(list.get(0));
		WebElement registerButton = driver.findElementByXPath("//button[text()='Register']");
		waitUntilvisibilityOfElement(driver, registerButton);
		driver.executeScript("arguments[0].click()", registerButton);
		Assert.assertEquals(driver.findElementByXPath("//div[@class='registerForm']/h2").getText().trim(),
				"You are registered please Login!!", "[FAILED]: Unable to registered");
		Reporter.reportLog("PASS", "[PASSED]: Successfully registered into the site");		
	}
	
	@Test(priority = 1)
	public void login_with_user_credentials() {		
		try {
			WebElement login = driver.findElementByLinkText("login");
			waitUntilvisibilityOfElement(driver, login);
			driver.executeScript("arguments[0].click()", login);
			waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
			driver.findElementByXPath("//input[@formcontrolname='userName']").sendKeys(emailId);
			driver.findElementByXPath("//input[@formcontrolname='password']").sendKeys(password);
			WebElement signIn = driver.findElementByXPath("//button[text()='Sign in']");
			driver.executeScript("arguments[0].click()", signIn);
			waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
			waitUntilvisibilityOfElement(driver, driver.findElementById("navbarDropdown"));
			Assert.assertEquals(driver.findElementById("navbarDropdown").getText().contains(firstName), true, "[FALIED]: Profile name was wrong the given name is "+firstName);
			Reporter.reportLog("PASS", "[PASSED]: Proflie name is correct name of the profiler is "+firstName);
		} catch (NoSuchElementException e) {
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}
	}
	
	@Test(priority = 2)
	public void select_a_hotel_from_the_list() {
		try {
			WebElement ele = driver.findElementByXPath("//h5[@class='card-title' and contains(text(), '"+hotelName+"')]");	
			waitUntilvisibilityOfElement(driver, ele);
			driver.executeScript("arguments[0].click()", ele);
			waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
		} catch (NoSuchElementException e) {
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}
	}	
	
	@Test(priority = 3)
	public void select_food() {
		try {
			selectFood = driver.findElementByXPath("(//div[@class='row']//table)[1]/tr[1]/td/span").getText();
			for (int i = 0; i < foodCount; i++) {
				WebElement ele = driver.findElementByXPath("(//div[@class='row']//table)[1]/tr[2]/td/button[text()=' + ']");
				driver.executeScript("arguments[0].click()", ele);
				driver.switchTo().alert().accept();			
			}
		} catch (NoSuchElementException e) {
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}
	}
	
	@Test(priority = 4)
	public void view_cart() {
		try {
			WebElement dropDown = driver.findElementById("navbarDropdown");
			driver.executeScript("arguments[0].click()", dropDown);
			WebElement cart = driver.findElementByLinkText("Cart");
			driver.executeScript("arguments[0].click()", cart);
			waitUntilInVisibilityOfElement(driver, driver.findElementByXPath("//div[@class='overlay']"));
			Assert.assertEquals(driver.findElementByXPath("//td[@data-th='Product']//h6").getText().trim().equals(selectFood), true, "[FAILED]: Select food item was wrong");
			Reporter.reportLog("PASS", "[PASSED]: Correct food item got selected");		
			Assert.assertEquals(driver.findElementByXPath("//td[@data-th='Quantity']").getText().trim().equals(Integer.toString(foodCount)), true, "[FAILED]: Select food count was wrong");
			Reporter.reportLog("PASS", "[PASSED]: Number of food count was correct");
		} catch (NoSuchElementException e) {
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}
	}
	
	@Test(priority = 5)
	public void place_order() {		
		try {
			WebElement ele = driver.findElementByLinkText("Checkout");
			driver.executeScript("arguments[0].click()", ele);		
			waitUntilvisibilityOfElement(driver, driver.findElementByXPath("//img[@alt='order placed']/following-sibling::h2"));
			String[] split = driver.findElementByXPath("//img[@alt='order placed']/following-sibling::h2").getText().split(" ");
			orderId = split[1];
		} catch (NoSuchElementException e) {
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}			
	}
	
	@Test(priority = 6)
	public void view_order_summary() {
		try {
			WebElement dropDown = driver.findElementById("navbarDropdown");
			driver.executeScript("arguments[0].click()", dropDown);
			WebElement orderHistory = driver.findElementByLinkText("Order History");
			driver.executeScript("arguments[0].click()", orderHistory);			
			waitUntilvisibilityOfElement(driver, driver.findElementByXPath("//div[@class='card']//button/span"));
			Assert.assertEquals(driver.findElementByXPath("//div[@class='card']//button/span").getText().contains(orderId), true, "[FAILED]: Generated order id was incorrect");
			Reporter.reportLog("PASS", "[PASSED]: Generated order id was correct one.");
		} catch (NoSuchElementException e) {			
			throw new RuntimeException("[FAILED]: Unable to locate element "+e.getMessage());
		}
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) {
		if(result.getStatus() == ITestResult.FAILURE) {
			Reporter.reportLog("FAIL", result.getThrowable().getMessage());
		} else if(result.getStatus() == ITestResult.SKIP) {
			Reporter.reportLog("SKIP", result.getThrowable().getMessage());
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			Reporter.reportLog("PASS", "Test Passed");
		}
	}
	
	@AfterClass
	public void afterClass() {
		driver.close();
	}
	
	@AfterSuite
	public void afterSuite() {
		Reporter.tearDownInstance();
	}

}