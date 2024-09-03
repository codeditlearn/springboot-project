package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.service.ApiMemberService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.List;


public class ApiController {
    public List<Object> result = null;
    public StatusMessage message;
    public HttpHeaders headers;


    public void jsonHeader(){
        message = new StatusMessage();
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));


    }
}
