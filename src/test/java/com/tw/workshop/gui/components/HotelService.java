package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;

public class HotelService extends SeleniumBase {
	
	public HotelService selectTheWishFood(int foodCount, String number, String foodName) {
		waitFor();
		for (int i = 0; i < foodCount; i++) {
			click(getWebElement("xpath=(//div[@class='row']//table)["+number+"]/tr[1]/td/span[contains(text(),'"+foodName+"')]/following::tr[1]/td/button[text()=' + ']"));
			acceptAlert();
		}
		return this;
	}

}