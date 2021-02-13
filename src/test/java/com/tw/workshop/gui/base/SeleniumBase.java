package com.tw.workshop.gui.base;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.tw.workshop.gui.constants.Browsers;
import com.tw.workshop.gui.constants.LocatorTypes;
import com.tw.workshop.utils.Logs;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumBase implements ISeleniumBaseDesign {	

	/**
	 * A thread is a unit of execution. We can possible to execute the same code by multiple threads at the same time.
	 * If the multiple threads execute at the same time they'll share the instance variable. For running multiple thread, each thread
	 * should have its own local variables to achieve this we need to pass variable as the argument but this is difficult one. For better approach
	 * we'll create the ThreadLocal variable or instance.
	 * 
	 * ThreadLocal is a simple, flexible way to have per-thread data that cannot be accessed concurrently by other threads.
	 * 
	 * How to create and access the ThreadLocal instance?
	 * 1. Creating a ThreadLocal
	 * 2. Set ThreadLocal Value
	 * 3. Get ThreadLocal Value
	 * 
	 * 1. Creating a ThreadLocal:
	 * ThreadLocal instance can be created just like any other Java object - via the new operator
	 * 
	 * This only needs to be done once per thread. Multiple threads can now get and set values inside this ThreadLocal, 
	 * and each thread will only see the value it set itself.
	 */
	private static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();	

	//3. Get ThreadLocal Value - To get value from the ThreadLocal you just need to use its get() method.
	public RemoteWebDriver getDriver() {
		return driver.get();
	}

	public void waitUntilvisibilityOfElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitUntilElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(getDriver(), 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public void waitForPageLoaded() {
		if(!getDriver().executeScript("return document.readyState").toString().equals("complete")) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
	}
	
	public void waitFor() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {				
			e.printStackTrace();
		}
	}

	public RemoteWebDriver launchBrowser() {
		RemoteWebDriver driver;
		try {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			//2. Set ThreadLocal Value - Once a ThreadLocal has been created you can set the value to be stored in it using its set() method.
			SeleniumBase.driver.set(driver);
			Logs.consoleLog("PASS", "Successfully launched and maximized chrome browser");			
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		return getDriver();	
	}

	public RemoteWebDriver launchBrowser(String browser) {
		RemoteWebDriver driver = null;
		Browsers browsers = Browsers.valueOf(browser.toUpperCase());
		try {
			switch (browsers) {
			case CHROME:
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				driver.manage().window().maximize();
				Logs.consoleLog("PASS", "Successfully launched and maximized chrome browser");
				break;
			case FIREFOX:
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				driver.manage().window().maximize();
				Logs.consoleLog("PASS", "Successfully launched and maximized firefox browser");
			default:
				Logs.consoleLog("WARN", "Currently we're supporting chrome, and firefox browsers");
			}
			//2. Set ThreadLocal Value - Once a ThreadLocal has been created you can set the value to be stored in it using its set() method.
			SeleniumBase.driver.set(driver);
		} catch (Exception e) {			
			e.printStackTrace();
		}		
		return getDriver();		
	}

	public void closeBrowser() {
		try {
			getDriver().close();
			Logs.consoleLog("PASS", "Successfully closed "+getDriver()+" opened browser");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to close the "+getDriver()+" opened browser. Because of "+e.toString());
		}		
	}

	public void quitBrowser() {
		try {
			getDriver().quit();
			Logs.consoleLog("PASS", "Successfully quit "+getDriver()+" opened browser");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to quit the "+getDriver()+" opened browser. Because of "+e.toString());
		}		
	}

	public void open(String aut) {
		try {
			getDriver().get(aut);	
			getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			Logs.consoleLog("PASS", "Opened given aut url in the "+getDriver()+" opened browser with 30sec implicit wait time");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to load given aut url in the "+getDriver()+" opened browser within 30sec implicit wait time. Because of "+e.toString());
		}
	}

	public WebElement getWebElement(String locator) {		
		WebElement element = null;
		String[] ele = locator.split("=", 2);		
		String key = ele[0];		
		String value = ele[1];		
		LocatorTypes loc = LocatorTypes.valueOf(key.toUpperCase());
		try {
			switch (loc) {
			case ID:
				element = getDriver().findElementById(value);	
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us ID");
				break;
			case NAME:
				element = getDriver().findElementByName(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us NAME");
				break;
			case CLASSNAME:
				element = getDriver().findElementByClassName(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us CLASSNAME");
				break;
			case LINKTEXT:
				element = getDriver().findElementByLinkText(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us LINKTEXT");
				break;
			case PARTIALLINKTEXT: 	
				element = getDriver().findElementByPartialLinkText(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us PARTIALLINKTEXT");
				break;
			case TAGNAME:
				element = getDriver().findElementByTagName(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us TAGNAME");
				break;
			case CSSSELECTOR:
				element = getDriver().findElementByCssSelector(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us CSSSELECTOR");
				break;
			case XPATH:
				element = getDriver().findElementByXPath(value);
				Logs.consoleLog("PASS", "Successfully get webelement "+element+" from the DOM using locator type us XPATH");
				break;
			default:
				Logs.consoleLog("WARN", "Selenium supports only following locators ID, NAME, CLASSNAME, LINKTEXT, PARTIALLINKTEXT, TAGNAME, CSSSELECTOR and XPATH. Kindly check your locator type in object repository.");
			}
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to get webelement "+element+" from the DOM. Because of "+e.toString());
		}		
		return element;
	}

	public List<WebElement> getWebElements(String locator) {
		List<WebElement> elements = null;
		String[] eles = locator.split("=", 2);
		String key = eles[0];
		String value = eles[1];	
		LocatorTypes loc = LocatorTypes.valueOf(key.toUpperCase());
		try {
			switch (loc) {
			case ID:
				elements = getDriver().findElementsById(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us ID");
				break;
			case NAME:
				elements = getDriver().findElementsByName(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us NAME");
				break;
			case CLASSNAME:
				elements = getDriver().findElementsByClassName(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us CLASSNAME");
				break;
			case LINKTEXT:
				elements = getDriver().findElementsByLinkText(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us LINKTEXT");
				break;
			case PARTIALLINKTEXT: 	
				elements = getDriver().findElementsByPartialLinkText(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us PARTIALLINKTEXT");
				break;
			case TAGNAME:
				elements = getDriver().findElementsByTagName(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us TAGNAME");
				break;
			case CSSSELECTOR:
				elements = getDriver().findElementsByCssSelector(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us CSSSELECTOR");
				break;
			case XPATH:
				elements = getDriver().findElementsByXPath(value);
				Logs.consoleLog("PASS", "Successfully get list of webelements "+elements+" from the DOM using locator type us XPATH");
				break;
			default:
				Logs.consoleLog("WARN", "Selenium supports only following locators ID, NAME, CLASSNAME, LINKTEXT, PARTIALLINKTEXT, TAGNAME, CSSSELECTOR and XPATH. Kindly check your locator type in object repository.");	
			}
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to get list of webelements "+elements+" from the DOM. Because of "+e.toString());
		}		
		return elements;
	}

	public void type(WebElement ele, String data) {	
		try {			
			ele.sendKeys(data);
			Logs.consoleLog("PASS", "Successfully type the given data "+data+" in the given element "+ele+" in the DOM");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to type the given data "+data+" in the given element "+ele+" in the DOM. Beacuse of "+e.toString());
		}		
	}

	public void click(WebElement ele) {
		try {
			waitUntilvisibilityOfElement(ele);
			waitUntilElementToBeClickable(ele);
			getDriver().executeScript("arguments[0].click()", ele);
			Logs.consoleLog("PASS", "Successfully click on the given element "+ele+" in the DOM");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to click on the given element "+ele+" in the DOM. Beacuse of "+e.toString());
		}		
	}

	public String getText(WebElement ele) {
		String text = null;
		try {
			waitUntilvisibilityOfElement(ele);
			text = ele.getText().trim();
			Logs.consoleLog("PASS", "Successfully fetch the following text "+text+" from the given element "+ele+" in the DOM");
		} catch (Exception e) {			
			Logs.consoleLog("FAIL", "Unable to fetch the following text "+text+" from the given element "+ele+" in the DOM. Because of "+e.toString());
		}
		return text;
	}

	public void selectValueInDropdown(WebElement ele, String text) {
		try {
			waitUntilvisibilityOfElement(ele);
			Select select = new Select(ele);
			select.selectByVisibleText(text);
		} catch (Exception e) {
			
		}
	}
	
	public void takeSnap(String fileName) {
		TakesScreenshot takesScreenshot = (TakesScreenshot)getDriver();	
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("src/test/resources/screenshots/"+fileName+".png"));
		} catch (IOException e) {
			throw new RuntimeException("Exception while taking screenshot "+e.getMessage());
		}
	}
	
	public void switchToWindow(int index) {		
		Set<String> windows = getDriver().getWindowHandles();
		List<String> handles = new ArrayList<String>(windows);
		String exWindow = handles.get(index);
		getDriver().switchTo().window(exWindow);
	}
	
	public void acceptAlert() {		
		getDriver().switchTo().alert().accept();
	}

}