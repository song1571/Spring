package com.sung.springframe.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Autowired
    private RepositoryConfig repositoryConfig;

    @Bean
    public TransferService transferService() {
        // repositoryConfig가 null이 아닌지 확인
        if (repositoryConfig == null) {
            throw new IllegalStateException("RepositoryConfig가 주입되지 않았습니다.");
        }
        return new TransferServiceImpl(repositoryConfig.accountRepository());
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
