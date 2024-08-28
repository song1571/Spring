package com.sung.pointcutapi.service;

import static java.lang.System.out;

public class MyService {
	
	@CustomAnnotation
	public void myMethod () {
		out.println("Executing myMethod");
	}

//	@CustomAnnotation
	public void anotherMethod(String arg) {
		out.println("Executing anotherMethod with arg:" + arg);
	}
	
	public void methodWithException() throws Exception {
		throw new Exception("Exception in methodWithException");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
