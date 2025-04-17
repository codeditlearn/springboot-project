package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusEnum;
import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.domain.dto.CreateEmoticonDto;
import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.domain.entity.EmoticonEntity;
import com.project.springboot_project.service.ApiEmoticonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/emoticon")
public class ApiEmoticonController extends ApiController{
    private final ApiEmoticonService emoticonService;

    @Autowired
    public ApiEmoticonController(ApiEmoticonService emoticonService){
        this.emoticonService = emoticonService;
    }

    @GetMapping("/list/{page}")
    public ResponseEntity<StatusMessage> list(@PathVariable("page") int page){
        jsonHeader();
        //List<EmoticonEntity> result = emoticonService.getListAll();
        List<EmoticonEntity> result = emoticonService.getListPaging(10,page);
        if(!result.isEmpty()){
            message.setData(result);
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<StatusMessage> write(CreateEmoticonDto createEmoticonDto){
        jsonHeader();
        System.out.println(createEmoticonDto);

        emoticonService.write(createEmoticonDto);
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<StatusMessage> info(@PathVariable("id") Long id){
        jsonHeader();
        Optional<EmoticonEntity> result = emoticonService.getOne(id);
        if(result.isPresent()){
            message.setData(result);
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


}
