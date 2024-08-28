package com.sung.pointcutapi.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sung.pointcutapi.advice.ExeceptionHandlingAdvice;
import com.sung.pointcutapi.advice.ExecutionTimeAdvice;
import com.sung.pointcutapi.advice.LoggingAdvice;
import com.sung.pointcutapi.pointcut.CustomPointCut;
import com.sung.pointcutapi.service.AnotherService;
import com.sung.pointcutapi.service.MyService;

@Configuration
public class AppConfig {
	
	@Bean
	public MyService myService() {
		return new MyService();
	}
	
	@Bean
	public AnotherService anotherService() {
		return new AnotherService();
	}
	
	@Lazy
	@Bean
	public Pointcut customPointCut() {
		return new CustomPointCut();
	}  
	
	@Lazy
	@Bean
	public Pointcut aspectJPointcut() {
	    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	    pointcut.setExpression(
	    		"execution(* com.sung.pointcutapi.service.AnotherService.myMethod(..))"
	    		);
	    return pointcut;
	}

	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor aspectJloggingAdvisor(
			@Qualifier("aspectJPointcut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor excutionTimeAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor exceptionHandlingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExeceptionHandlingAdvice());
	}
	
	@Lazy
	@Bean
	public ProxyFactoryBean myServiceProxy(
			MyService myService,
			@Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			@Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			@Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor
			) {
		
		ProxyFactoryBean proxyFactoryBean = 
				new ProxyFactoryBean();
		proxyFactoryBean.setTarget(myService);
		proxyFactoryBean.setInterceptorNames(
				"loggingAdvisor",
				"excutionTimeAdvisor",
				"exceptionHandlingAdvisor");
		
		return proxyFactoryBean;
	}
	
	@Lazy
	@Bean
	public ProxyFactoryBean anotherServiceProxy(
			AnotherService anotherService,
			@Qualifier("aspectJloggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			@Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			@Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor
			) {
		
		ProxyFactoryBean proxyFactoryBean = 
				new ProxyFactoryBean();
		proxyFactoryBean.setTarget(anotherService);
		proxyFactoryBean.setInterceptorNames(
				"aspectJloggingAdvisor",
				"excutionTimeAdvisor",
				"exceptionHandlingAdvisor");
		
		return proxyFactoryBean;
	}
}
