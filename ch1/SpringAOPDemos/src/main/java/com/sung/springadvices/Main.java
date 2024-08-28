package com.sung.springadvices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.springadvices.config.AppConfig;  // 여기서 패키지와 클래스를 정확히 가져오는지 확인하세요
import com.sung.springadvices.service.SimpleService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);  // AppConfig 클래스를 정확히 참조
        
        SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");
        
        service.performTask();
        
        String greeting = service.returnGreeting("Me");
        System.out.println("Greeting: " + greeting);   
        
        try {
            service.throwException();
        } catch(Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
        }
    }
}
