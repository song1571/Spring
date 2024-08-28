package com.sung.pointcutapi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.pointcutapi.config.AppConfig;
import com.sung.pointcutapi.config.AppConfigForEnableAspectJAutoProxy;
import com.sung.pointcutapi.service.AnotherService;
import com.sung.pointcutapi.service.MyService;

import net.bytebuddy.jar.asm.Handle;

public class Main {
	
	public static void execAppConfig() {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);
		
		MyService myService = (MyService) context.getBean("myServiceProxy");
		AnotherService anotherService =
				(AnotherService) context.getBean("anotherServiceProxy");
		
		myService.myMethod();
		myService.anotherMethod("test");
		
		anotherService.myMethod();
		anotherService.differentMethod(123);
		
		try {
			myService.methodWithException();
		} catch (Exception e) { 
			System.out.println("Exception handled in main");
		}
		context.close();
	}

	public static void execAppConfigForEnableAspectJAutoProxy() {
		
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfigForEnableAspectJAutoProxy.class);
		
		MyService myService = (MyService) context.getBean("myService");
		AnotherService anotherService =
				(AnotherService) context.getBean("anotherService");
		
		myService.myMethod();
		myService.anotherMethod("test");
		
		anotherService.myMethod();
		anotherService.differentMethod(123);
		
		try {
			myService.methodWithException();
		} catch (Exception e) { 
			System.out.println("Exception handled in main");
		}
		context.close();
		
	}
	
	public static void main(String[] args) {
		execAppConfigForEnableAspectJAutoProxy();
	}
}
