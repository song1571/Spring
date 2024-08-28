package com.sung.manipulatingadvicsedobjects;

import org.springframework.aop.framework.Advised;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sung.manipulatingadvicsedobjects.service.MyService;
import com.sung.manipulatingadvicsedobjects.config.AppConfig;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        MyService myServiceProxy = (MyService) context.getBean("myServiceProxy");
        Advised advised = (Advised) myServiceProxy;

        // 클래스 로더 확인을 위한 로그 추가
        System.out.println("MyService Interface Loader: " + MyService.class.getClassLoader());
        System.out.println("MyServiceImpl Class Loader: " + myServiceProxy.getClass().getClassLoader());

        System.out.println("Advisors count: " + advised.getAdvisorCount());
    }
}
