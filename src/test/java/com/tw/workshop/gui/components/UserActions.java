package com.tw.workshop.gui.components;

public class UserActions implements UserAction {
	
	public BrowseThat browseTheApplication() {
		return new BrowseThat();
	}
	
	public LandingService shouldBeInTheLandingPage() {	
		return new LandingService();
	}
	
	public RegisterService shouldBeInTheRegisterPage() {		
		return new RegisterService();
	}
	
	public LoginService shouldBeInTheLoginPage() {		
		return new LoginService();
	}
	
	public ProfileService shouldBeInTheProfilePage() {		
		return new ProfileService();
	}	

}