package com.sung.springadvices.service.advices.introduction;

import org.springframework.aop.IntroductionInterceptor;
import org.springframework.aop.support.AopUtils;
import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.aopalliance.intercept.MethodInvocation;

// Delegate : delegate는 도입된 인터페이스[Lockable]의 메서드를 실제로 구현
public class LockMixin extends DelegatingIntroductionInterceptor implements Lockable {

//	public LockMixin(Object delegate) {
//		super(delegate);
//	}
	private boolean locked;
    
	@Override
	public void lock() {
		this.locked = true;
	}
        
	@Override
	public void unlock() {
		this.locked = false;
	}
   
	@Override
	public boolean locked() {
		return this.locked;
	}

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
//    	Class<?>[] lockable = getInterfaces();
//    	Lockable loc = Lockable.getClass();
//    	
//    	Object retVal = AopUtils.invokeJoinpointUsingReflection(
//    			this.delegate, 
//    			invocation.getMethod(), 
//    			invocation.getArguments());
    	
        if (locked() && invocation.getMethod().getName().startsWith("set")) {
            throw new LockedException();
        }
        // invication.proceed() -> target class의 method에게 위임한다...
        return super.invoke(invocation);
    }
}
