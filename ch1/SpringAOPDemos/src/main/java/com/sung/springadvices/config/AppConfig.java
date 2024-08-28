package com.sung.springadvices.config;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sung.springadvices.service.SimpleService;
import com.sung.springadvices.service.advices.CountingAfterReturningAdvice;
import com.sung.springadvices.service.advices.CountingBeforeAdvice;
import com.sung.springadvices.service.advices.DebugInterceptor;
import com.sung.springadvices.service.advices.SimpleThrowsAdvice;

@Configuration
public class AppConfig {
	
	@Bean
	public SimpleService simpleService() {
		return new SimpleService();
	}
	
//	@Bean
//	public SimpleService proxyFactory() {
//		ProxyFactory proxy = new ProxyFactory(simpleService());
//		proxy.addAdvisor(new DefaultPointcutAdvisor(debugInterceptor()));
//		return (SimpleService)proxy.getProxy();
//	}

	@Bean
	public DebugInterceptor debugInterceptor() {
		return new DebugInterceptor();
	}
	
	@Bean
	public CountingBeforeAdvice countingBeforeAdvice() {
		return new CountingBeforeAdvice();
	}
	
	@Bean
	public CountingAfterReturningAdvice countingAfterReturningAdvice() {
		return new CountingAfterReturningAdvice();
	}
	
	@Bean
	public SimpleThrowsAdvice simpleThrowsAdvice() {
		return new SimpleThrowsAdvice();
	}
	
    @Bean(name = "proxyFactoryBean")
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());
        proxyFactoryBean.setProxyTargetClass(true);

        // 각 어드바이스를 프록시 팩토리 빈에 추가합니다.
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(debugInterceptor()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingAfterReturningAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingBeforeAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(simpleThrowsAdvice()));
    	
        return proxyFactoryBean;
    }
}

