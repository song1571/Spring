package com.sung.springadvices.service.advices.introduction;

public class LockableImpl implements Lockable {

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

}
