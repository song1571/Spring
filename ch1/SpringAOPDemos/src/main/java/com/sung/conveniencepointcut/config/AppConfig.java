package com.sung.conveniencepointcut.config;

import java.util.Properties;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;
import com.sung.conveniencepointcut.service.MyService;
import com.sung.conveniencepointcut.service.MyServiceImpl;
import com.sung.conveniencepointcut.service.MySpecialServiceImpl;
import com.sung.conveniencepointcut.transaction.SimpleTransactionManager;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    // 간단한 트랜잭션 관리자 빈 정의
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new SimpleTransactionManager();
    }

    // MyService 빈 정의 추가
    @Bean
    public MyService myService() {
        return new MyServiceImpl(); // MyServiceImpl의 인스턴스를 반환하여 MyService 빈을 생성합니다.
    }

    // 트랜잭션 프록시 빈을 생성하는 메서드
    private TransactionProxyFactoryBean createProxy(
            PlatformTransactionManager transactionManager,
            Object target,
            Properties transactionAttributes) {
        TransactionProxyFactoryBean proxyFactoryBean = new TransactionProxyFactoryBean();
        proxyFactoryBean.setTarget(target);
        proxyFactoryBean.setTransactionManager(transactionManager);
        proxyFactoryBean.setTransactionAttributes(transactionAttributes);
        proxyFactoryBean.setProxyTargetClass(true);

        return proxyFactoryBean;
    }

    // MySpecialService 빈 정의
    @Bean
    public TransactionProxyFactoryBean mySpecialService(PlatformTransactionManager transactionManager) {
        Properties properties = new Properties();
        properties.setProperty("get", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("find", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("load", "PROPAGATION_REQUIRED,readOnly");
        properties.setProperty("store", "PROPAGATION_REQUIRED,readOnly");

        return createProxy(transactionManager, new MySpecialServiceImpl(), properties);
    }
}

