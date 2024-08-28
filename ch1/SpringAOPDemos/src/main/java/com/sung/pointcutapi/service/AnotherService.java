package com.sung.pointcutapi.service;

import static java.lang.System.out;

public class AnotherService {
	
	public void myMethod() {
		System.out.println("AnotherService:myMethod");
	}
	
	public void differentMethod (int number) {
		out.println("Executing differenMethod with number:" + number);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
