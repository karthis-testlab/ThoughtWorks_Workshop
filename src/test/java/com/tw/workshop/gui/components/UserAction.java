package com.tw.workshop.gui.components;

public interface UserAction {
	
	public BrowseThat browseTheApplication();
	
	public LandingService shouldBeInTheLandingPage();
	
	public RegisterService shouldBeInTheRegisterPage();
	
	public LoginService shouldBeInTheLoginPage();
	
	public ProfileService shouldBeInTheProfilePage();
	
	public HotelService shouldBeInTheHotelPage();
	
	public ViewCartService shouldBeInViewCartPage();
	
	public CartService shouldBeInCartPage();
	
	public OrderHistoryService shouldBeInOrderHistoryPage();

}