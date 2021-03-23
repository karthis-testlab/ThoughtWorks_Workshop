package com.tw.workshop.listeners;

public interface TestInterface1 {
	
	public void square(int a); 
	
	default void show() {
		System.out.println("Default method in TestInterface1");
	}

}