package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiHomeController {
    List<Object> result = null;
    private StatusMessage message;
    private HttpHeaders headers;

    public void jsonHeader(){
        message = new StatusMessage();
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    }



    @GetMapping("/")
    public ResponseEntity<StatusMessage> home(){
        System.out.println("API 홈!!");
        jsonHeader();
        message.setMessage("홈!!!!");
        message.setData(new HashMap<String,String>());

        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
}
