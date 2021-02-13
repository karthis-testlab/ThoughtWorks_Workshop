package com.tw.workshop.gui.components;

import org.testng.Assert;

import com.tw.workshop.gui.base.SeleniumBase;

public class ProfileService extends SeleniumBase {
	
	public ProfileService shouldBeLoginIntoFoodSite(String firstName) {
		waitFor();
		Assert.assertEquals(getText(getWebElement("id=navbarDropdown")).contains(firstName), true, "[FALIED]: Profile name was wrong the given name is "+firstName);
		System.out.println("[PASSED]: Proflie name is correct name of the profiler is "+firstName);
		return this;
	}

}