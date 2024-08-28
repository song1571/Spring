package com.sung.javabeanproperties;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sung.javabeanproperties.config.AppConfig;
import com.sung.javabeanproperties.service.SimpleService;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);
        
        ProxyFactoryBean pfb = 
        		(ProxyFactoryBean) context.getBean("&proxyFactoryBean");
        
        ProxyFactoryBean pfb2 = 
        		(ProxyFactoryBean) context.getBean("&proxyFactoryBean");
        
        SimpleService proxy = 
        		(SimpleService) context.getBean("proxySimpleService");

 
        System.out.println("=== 1. 프록시를 통한 메서드 호출 ===");
        proxy.doSomething();


        System.out.println("=== 2. AopContext.currentProxy()를 통한 메서드 호출 ===");
        try {
            SimpleService currentProxy = (SimpleService) AopContext.currentProxy();
            currentProxy.doSomething();
        } catch (IllegalStateException e) {
            System.out.println("AopContext.currentProxy() 호출 실패: " + e.getMessage());
        }
        
        System.out.println("Proxy Class: " + proxy.getClass().getName());
        
        
        if (AopUtils.isCglibProxy(proxy)) {
        	System.out.println("CGLIB 프록시가 적용되었음");
        } else {
        	System.out.println("JDK 프록시가 적용되었음");
        }
        
        SimpleService anotherProxyInstance = 
        		(SimpleService) context.getBean("proxySimpleService");
        
        if (proxy == anotherProxyInstance) {
        	System.out.println("도일한 프로시 인스턴스");
        } else {
        	System.out.println("동일하지 프록시 인스턴스");
        }
        try {
            ProxyFactoryBean proxyFactoryBean =
            		(ProxyFactoryBean) context.getBean("&proxyFactoryBean");
            
            Advised advised = (Advised) proxyFactoryBean.getObject();
            advised.addAdvice(new MethodBeforeAdvice() {

				@Override
				public void before(Method method, Object[] args, Object target) throws Throwable {
					System.out.println("동적으로 추가될 어드바이스");
					
				}
            	
            });
            System.out.println();
        } catch(UnsupportedOperationException e) {
        	System.out.println("Frozen 설정으로 인해 어드바이스 추가가 차단되었습니다: " + 
        						e.getMessage());
        }
    }
}
