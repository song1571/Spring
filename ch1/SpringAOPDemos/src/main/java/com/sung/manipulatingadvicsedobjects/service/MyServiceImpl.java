package com.sung.manipulatingadvicsedobjects.service;

import com.sung.conveniencepointcut.service.MyService;

public class MyServiceImpl implements MyService, com.sung.manipulatingadvicsedobjects.service.MyService {


	public void performOperation() {
		
		System.out.println("MyServiceImpl: Operation performed");

	}

}
