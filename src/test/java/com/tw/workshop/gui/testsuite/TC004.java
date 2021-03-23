package com.tw.workshop.gui.testsuite;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC004 {

	public static void main(String[] args) {
		int amount = 0;
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		List<WebElement> eles = driver.findElementsByXPath("(//table[@id='product'])[last()]/tbody/tr/td[last()]");
		for (WebElement ele : eles) {
			amount += Integer.parseInt(ele.getText());			
		}
		String actual = String.valueOf(amount);
		String expect = driver.findElementByClassName("totalAmount").getText().replaceAll("\\D", "");
		if(actual.equals(expect)) {
			System.out.println("Test cases - PASSED");
		} else {
			throw new RuntimeException("Test cases - FAILED");
		}
	}

}