package com.sung.springadvices.service.advices.introduction;

public interface Lockable {  
    void lock();    
    void unlock();  
    boolean locked();
}
