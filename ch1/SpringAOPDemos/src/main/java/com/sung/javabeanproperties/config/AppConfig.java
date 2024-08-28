package com.sung.javabeanproperties.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sung.javabeanproperties.advice.LoggingBeforeAdvice;
import com.sung.javabeanproperties.service.SimpleService;
import com.sung.javabeanproperties.service.SimpleServiceImpl;

@Configuration
public class AppConfig {
	
	@Bean
	public SimpleService simpleService() {
		return new SimpleServiceImpl();
	}
	
	@Bean
	public LoggingBeforeAdvice loggingBeforeAdvice() {
		return new LoggingBeforeAdvice();
	}
	
	@Bean
	public NameMatchMethodPointcut nameMatchMethodPointcut() {
		NameMatchMethodPointcut pointcut = 
				new NameMatchMethodPointcut();
		
		pointcut.setMappedName("doSomething");
		return pointcut;
	}
	
	
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor() {
		DefaultPointcutAdvisor advisor = 
				new DefaultPointcutAdvisor();
		advisor.setPointcut(nameMatchMethodPointcut());
		advisor.setAdvice(loggingBeforeAdvice());
		return advisor;
	}
	
	@Bean
	public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
		ProxyFactoryBean proxyFactoryBean =
				new ProxyFactoryBean();
		
		proxyFactoryBean.setTargetName("hellow");
		proxyFactoryBean.setTarget(simpleService());
		
		proxyFactoryBean.setProxyTargetClass(false);
		
		// setProxyTargetClass을 false로 설정하더라도,
		// setOptimizefmf true로 설정하면, setProxyTargetClass가 true로 설정되나봄
		proxyFactoryBean.setOptimize(false); // 프락시 cglib일 경우, cglib 최적화 수행
		
		proxyFactoryBean.setFrozen(false);
		
		proxyFactoryBean.setExposeProxy(true);
		
		proxyFactoryBean.setProxyInterfaces(new Class<?>[]{SimpleService.class});
		
		proxyFactoryBean.setInterceptorNames("loggingAdvisor");
		
		proxyFactoryBean.setSingleton(true);
		
		return proxyFactoryBean;
	}
	
	@Bean
	public SimpleService proxySimpleService() throws BeansException, ClassNotFoundException {
		return (SimpleService) proxyFactoryBean().getObject();
		
	}
	
}