package com.sung.springadvices.service;

public class SimpleService {
    
    public void performTask() {
        System.out.println("Performing a task");
    }

    public String returnGreeting(String name) {  // 메서드 이름 수정됨
        return "hello, " + name;
    }
    
    public void throwException() throws Exception {
        throw new Exception("This is a test exception");
    }
}

