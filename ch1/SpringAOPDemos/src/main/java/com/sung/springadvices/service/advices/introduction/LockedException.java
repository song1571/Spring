package com.sung.springadvices.service.advices.introduction;

public class LockedException extends RuntimeException {

	 public LockedException() {
	      super("Other is locked. No modification are allowed");
	  }
}
