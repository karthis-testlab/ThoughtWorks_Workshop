package com.tw.workshop.listeners;

public class A implements TestInterface1, TestInterface2 {
	
	public void square(int a) { 
        System.out.println(a*a); 
    }	
	
	public void show() {		
		TestInterface1.super.show();
		TestInterface2.super.show();
	}

	public static void main(String[] args) {
		A a = new A();
		a.show();
		a.square(5);
	}

}