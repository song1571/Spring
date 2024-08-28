package com.sung.springframe;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);

		// now both beans A and B will be available...
		Aaa aaa = ctx.getBean("aaa",Aaa.class);
		Bbb bbb = ctx.getBean("bbb",Bbb.class);
		
		System.out.println("hello world");
	}

}
