package com.sung.dynamicpointcuts;

public class TaskCaller {

    private final SimpleService simpleService;

    public TaskCaller(SimpleService simpleService) {
        this.simpleService = simpleService;
    }

    public void callTask() {
        System.out.println("Calling task from TaskCaller");
        simpleService.performTask();
    }
}