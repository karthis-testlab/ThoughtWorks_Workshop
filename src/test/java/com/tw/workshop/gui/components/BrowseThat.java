package com.tw.workshop.gui.components;

import com.tw.workshop.gui.base.SeleniumBase;

public class BrowseThat extends SeleniumBase {

	public BrowseThat in(String browserName) {
		launchBrowser(browserName);
		return this;
	}

	public BrowseThat andHit(String url) {
		open(url);
		return this;
	}

	public BrowseThat andCloseThat() {
		closeBrowser();
		return this;
	}
	
	public BrowseThat chrome() {
		launchBrowser();
		return this;
	}

}