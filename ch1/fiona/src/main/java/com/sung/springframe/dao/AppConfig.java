package com.sung.springframe.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// Spring Configuration class
@Configuration
@ComponentScan("com.sung.springframe.dao")
class AppConfig {

    @Bean
    @Scope("prototype")
    public Command command() {
        return new ConcreteCommand();
    }

    @Bean
    public CommandManager commandManager() {
//		return new CommandManager();
    	CommandManager cm = new CommandManager();
//    	cm.setCommamd(command()); // Spring IoC DePendency Injection
    	return cm;
    }
}