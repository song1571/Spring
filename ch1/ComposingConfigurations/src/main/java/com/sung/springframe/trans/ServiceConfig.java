package com.sung.springframe.trans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	
	@Bean
    public TransferService transferService(AccountRepository accountRepository) {
		System.out.println("+ServiceConfig::transferService");
		TransferServiceImpl tsil = new TransferServiceImpl(accountRepository);
		System.out.println("-ServiceConfig::transferService");

		return tsil;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
