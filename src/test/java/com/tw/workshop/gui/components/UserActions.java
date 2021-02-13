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
	
	public HotelService shouldBeInTheHotelPage() {		
		return new HotelService();
	}
	
	public ViewCartService shouldBeInViewCartPage() {		
		return new ViewCartService();
	}
	
	public CartService shouldBeInCartPage() {		
		return new CartService();
	}
	
	public OrderHistoryService shouldBeInOrderHistoryPage() {		
		return new OrderHistoryService();
	}	

}