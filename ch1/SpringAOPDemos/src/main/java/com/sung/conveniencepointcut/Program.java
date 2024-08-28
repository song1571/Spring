package com.sung.conveniencepointcut;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.conveniencepointcut.config.AppConfig;
import com.sung.conveniencepointcut.service.SimpleService;


public class Program {
    public static void main(String... args) {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(AppConfig.class);

        // 'proxyFactoryBean'이라는 이름으로 빈을 가져와 SimpleService 타입으로 캐스팅합니다.
        SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");

        // 서비스의 메서드를 호출합니다.
        service.setName("John");
        service.getName();
        service.absquatulate();
        service.performTask();
	}

}
