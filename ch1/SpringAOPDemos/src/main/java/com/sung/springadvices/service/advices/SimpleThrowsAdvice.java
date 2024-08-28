package com.sung.springadvices.service.advices;

import java.lang.reflect.Method;

import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.ThrowsAdvice;

public class SimpleThrowsAdvice implements ThrowsAdvice {
    
    public void afterThrowing(Method method, 
    		Object[] args, 
    		Object target, 
    		Exception e) {
        System.out.println("Exception thrown in method: " + 
    		method.getName() +
            ", exception: " + 
    		e.getMessage());
        
        IntroductionAdvisor ia;
    }
}