package com.sung.springadvices.service.advices.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyTargetClass myObject = 
				context.getBean(MyTargetClass.class);
		
		Lockable lockable = (Lockable) myObject;
		
		if (!lockable.locked()) {
			myObject.setName("hello");
			lockable.lock();
			String name = myObject.getName();
		} else {
			try {
				myObject.setName("world");
			} catch (LockedException e) {
				System.out.println(e.getMessage());
			}
		}
		
		try {
			myObject.setName("world");
		} catch (LockedException e) {
			System.out.println(e.getMessage());
		}
		
	}

}
