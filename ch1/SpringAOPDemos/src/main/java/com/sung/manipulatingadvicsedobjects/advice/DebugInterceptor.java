package com.sung.manipulatingadvicsedobjects.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import static java.lang.System.out;

public class DebugInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        out.println("DebugInterceptor: Before method " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        out.println("DebugInterceptor: After method " + invocation.getMethod().getName());
        return retVal;
    }

}
