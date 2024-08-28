package com.sung.springframe.context;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRepositoryConfig implements RepositoryConfig {

    @Bean
    public AccountRepository accountRepository() {
        // 필요한 매개변수를 제공하여 JdbcAccountRepository의 인스턴스를 생성합니다.
        return new JdbcAccountRepository(dataSource());  // dataSource()는 예시로 추가된 메서드입니다.
    }

    // 예시로 추가한 DataSource 메서드 (실제 구현에 따라 수정 필요)
    @Bean
    public DataSource dataSource() {
        // 데이터 소스 설정을 반환하는 로직 추가
        return new DataSource(...);
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
