package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.domain.entity.MemoEntity;
import com.project.springboot_project.service.ApiMemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memo")
public class ApiMemoController extends ApiController{
    private final ApiMemoService apiMemoService;
    public ApiMemoController(ApiMemoService apiMemoService){
        this.apiMemoService = apiMemoService;
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<StatusMessage> list(@PathVariable("userId") String userId){
        jsonHeader();
        List<MemoEntity> result = apiMemoService.list(userId);



        if(result != null){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }



        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<StatusMessage> write(MemoEntity memoEntity){
        jsonHeader();
        int result = apiMemoService.save(memoEntity);


        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @DeleteMapping("/remove/{uuid}")
    public ResponseEntity<StatusMessage> remove(@PathVariable("uuid") String uuid){
        jsonHeader();
        int result = apiMemoService.remove(uuid);
        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }
    @DeleteMapping("/removeAll/{userId}")
    public ResponseEntity<StatusMessage> removeAll(@PathVariable("userId") String userId){
        jsonHeader();
        int result = apiMemoService.removeAll(userId);
        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }
    @PostMapping("/edit")
    public ResponseEntity<StatusMessage> edit(MemoEntity memoEntity){
        jsonHeader();
        int result = apiMemoService.edit(memoEntity);
        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }
}
