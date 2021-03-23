package com.tw.workshop.listeners;

public interface TestInterface2 {
	
	default void show() {
		System.out.println("Default method in TestInterface2");
	}

}