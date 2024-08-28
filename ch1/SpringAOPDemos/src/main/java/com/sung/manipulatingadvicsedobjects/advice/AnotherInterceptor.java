package com.sung.manipulatingadvicsedobjects.advice;

import static java.lang.System.out;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import static java.lang.System.out;

public class AnotherInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        out.println("AnotherInterceptor: Before method " + 
        		invocation.getMethod().getName());
        
        Object retVal = invocation.proceed();
        out.println("AnotherInterceptor: After method " +
        		invocation.getMethod().getName());
        return retVal;
    }
}