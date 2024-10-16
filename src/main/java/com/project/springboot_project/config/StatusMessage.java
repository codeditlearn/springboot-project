package com.project.springboot_project.config;

import lombok.Data;

@Data
public class StatusMessage {
    private String message;
    private int status;
    private Object data;

    public StatusMessage(){
        this.message = StatusEnum.FAIL.getStatusMessage();
        this.status = StatusEnum.FAIL.getStatusCode();
        this.data = null;
    }

}
