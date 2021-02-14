package com.tw.workshop.gui.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SynchronizationWait {
	
	public static void waitUntilvisibilityOfElement(RemoteWebDriver dirver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(dirver, 30);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitUntilElementToBeClickable(RemoteWebDriver dirver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(dirver, 15);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void waitUntilInVisibilityOfElement(RemoteWebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.invisibilityOf(element));
	}

}