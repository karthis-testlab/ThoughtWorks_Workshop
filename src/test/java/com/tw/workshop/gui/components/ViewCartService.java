package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;

public class ViewCartService extends SeleniumBase {
	
	public ViewCartService clickOnTheNavigationBar() {
		waitFor();
		click(getWebElement("id=navbarDropdown"));
		return this;
	}
	
	public CartService clickOnTheCartMenu() {
		click(getWebElement("linktext=Cart"));
		return new CartService();
	}

}