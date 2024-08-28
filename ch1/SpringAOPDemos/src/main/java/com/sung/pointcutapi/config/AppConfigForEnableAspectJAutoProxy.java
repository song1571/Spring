package com.sung.pointcutapi.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


import com.sung.pointcutapi.advice.ExeceptionHandlingAdvice;
import com.sung.pointcutapi.advice.ExecutionTimeAdvice;
import com.sung.pointcutapi.advice.LoggingAdvice;
import com.sung.pointcutapi.pointcut.CustomPointCut;
import com.sung.pointcutapi.service.AnotherService;
import com.sung.pointcutapi.service.MyService;

@Configuration
@EnableAspectJAutoProxy
public class AppConfigForEnableAspectJAutoProxy {

	@Bean
	public MyService myService() {
		return new MyService();
	}
	
	@Bean
	public AnotherService anotherService() {
		return new AnotherService();
	}

	@Bean
	public Pointcut customPointCut() {
		return new CustomPointCut();
	}  

	@Bean
	public Pointcut aspectJPointcut() {
	    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
	    pointcut.setExpression(
	    		"execution(* com.sung.pointcutapi.service.AnotherService.myMethod(..))"
	    		);
	    return pointcut;
	}

	@Bean
	public DefaultPointcutAdvisor aspectJloggingAdvisor(
			@Qualifier("aspectJPointcut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor excutionTimeAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
	}
	
	@Bean
	public DefaultPointcutAdvisor exceptionHandlingAdvisor(
			@Qualifier("customPointCut") Pointcut pointcut) {
		return new DefaultPointcutAdvisor(pointcut, new ExeceptionHandlingAdvice());
	}
	
}
