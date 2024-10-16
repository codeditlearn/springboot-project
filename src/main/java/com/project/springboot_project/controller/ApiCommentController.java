package com.project.springboot_project.controller;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.domain.dto.CreateCommentDto;
import com.project.springboot_project.domain.entity.CommentEntity;
import com.project.springboot_project.service.ApiCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class ApiCommentController extends ApiController{

    private final ApiCommentService apiCommentService;
    private final CommonMethod cm;

    @GetMapping("/")
    public ResponseEntity<StatusMessage> home(){
        jsonHeader();
        message.setMessage("SUCCESS");
        message.setStatus(200);
        message.setData(new HashMap<>());
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }


    @PostMapping("/write")
    public ResponseEntity<StatusMessage> write(
            CreateCommentDto createCommentDto
    ){
        jsonHeader();
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setUserId(createCommentDto.getUserId());
        commentEntity.setNickname(createCommentDto.getNickname());
        commentEntity.setBid(Long.valueOf(createCommentDto.getBid()));
        commentEntity.setRef(Long.valueOf(createCommentDto.getRef()));
        commentEntity.setStep(Integer.valueOf(createCommentDto.getStep()));
        commentEntity.setContent(createCommentDto.getContent());
        commentEntity.setRegdate(cm.nowDate(null));
        commentEntity.setReplyList(new ArrayList<>());
        CommentEntity result = apiCommentService.save(commentEntity);

        if(result != null){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }


        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @GetMapping("/list/{bid}")
    public ResponseEntity<StatusMessage> list(@PathVariable("bid") Long bid){

        jsonHeader();
        List<CommentEntity> result = apiCommentService.list(bid);
        if(result != null){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }


        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<StatusMessage> remove(
            @RequestParam("id") String id,
            @RequestParam("userId") String userId
    ){
        jsonHeader();
        int result = apiCommentService.remove(Long.valueOf(id),userId);

        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<StatusMessage> edit(
            @RequestParam("id") String id,
            @RequestParam("content") String content
    ){
        jsonHeader();
        CommentEntity commentDto = new CommentEntity();
        commentDto.setId(Long.valueOf(id));
        commentDto.setContent(content);
        int result = apiCommentService.edit(commentDto);

        if(result == 1){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


    @GetMapping("/replyList/{ref}/{bid}")
    public ResponseEntity<StatusMessage> replyList(@PathVariable("ref") Long ref, @PathVariable("bid") Long bid){
        jsonHeader();

        List<CommentEntity> result = apiCommentService.replyList(ref,bid);

        if(result != null){
            message.setMessage("SUCCESS");
            message.setStatus(200);
            message.setData(result);
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


}
