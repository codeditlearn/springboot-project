package com.project.springboot_project.config;

public enum StatusEnum {
    OK(200,"SUCCESS"),
    FAIL(400,"FAIL");

    private String statusMessage;
    private int statusCode;
    StatusEnum(int statusCode,String statusMessage){
        this.statusMessage = statusMessage;
        this.statusCode = statusCode;
    }

    public String getStatusMessage(){
        return this.statusMessage;
    }
    public int getStatusCode(){
        return this.statusCode;
    }
}
