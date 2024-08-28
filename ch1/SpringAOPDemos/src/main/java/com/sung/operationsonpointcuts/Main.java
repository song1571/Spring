package com.sung.operationsonpointcuts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = 
				new AnnotationConfigApplicationContext(Appconfig.class);
		
		SimpleService service = context.getBean(SimpleService.class);
		
		service.mothodA();
		service.mothodB();
	}

}
