package com.sung.springadvices.service.advices.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class LockMixinAdvisor extends DefaultIntroductionAdvisor {

	  public LockMixinAdvisor() {
		  // 인스턴스별 어드바이스 만든다...
	      super(new LockMixin(/*new LockableImpl()*/), Lockable.class);
	  }

	}