package com.sung.manipulatingadvicsedobjects.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sung.manipulatingadvicsedobjects.advice.AnotherInterceptor;
import com.sung.manipulatingadvicsedobjects.advice.DebugInterceptor;
import com.sung.manipulatingadvicsedobjects.pointcut.MySpecialPointcut;
import com.sung.manipulatingadvicsedobjects.service.MyService;
import com.sung.manipulatingadvicsedobjects.service.MyServiceImpl;

@Configuration
public class AppConfig {

    // MyService 빈 정의
    @Bean
    public MyService myService() {
        // 여기서 MyServiceImpl을 MyService로 캐스팅하지 말고 그대로 반환합니다.
        return new MyServiceImpl();
    }

    @Bean
    public DebugInterceptor debugInterceptor() {
        return new DebugInterceptor();
    }

    @Bean
    public AnotherInterceptor anotherInterceptor() {
        return new AnotherInterceptor();
    }

    @Bean
    public ProxyFactoryBean myServiceProxy(MyService myService, DebugInterceptor debugInterceptor) {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myService);
        proxyFactoryBean.addAdvice(debugInterceptor);
        proxyFactoryBean.setProxyTargetClass(true);
        return proxyFactoryBean;
    }

    @Bean
    public MySpecialPointcut mySpecialPointcut() {
        return new MySpecialPointcut();
    }

    @Bean
    public DefaultPointcutAdvisor myAdvisor(MySpecialPointcut mySpecialPointcut, DebugInterceptor debugInterceptor) {
        return new DefaultPointcutAdvisor(mySpecialPointcut, debugInterceptor);
    }

    @Bean
    public DefaultPointcutAdvisor anotherAdvisor(MySpecialPointcut mySpecialPointcut, AnotherInterceptor anotherInterceptor) {
        return new DefaultPointcutAdvisor(mySpecialPointcut, anotherInterceptor);
    }
}
