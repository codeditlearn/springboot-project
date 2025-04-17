package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusEnum;
import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.domain.dto.CreateBoardDto;
import com.project.springboot_project.domain.dto.PageableBoardDto;
import com.project.springboot_project.domain.dto.UpdateBoardDto;
import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.service.ApiBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
public class ApiBoardController  extends ApiController{

    private final ApiBoardService apiBoardService;
    @Autowired
    public ApiBoardController(ApiBoardService apiBoardService){
        this.apiBoardService = apiBoardService;
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<StatusMessage> info(@PathVariable("id") Long id){
        jsonHeader();

        Optional<BoardEntity> result = apiBoardService.getBoard(id);
        if(result.isPresent()){
            message.setData(result.get());
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }
    @GetMapping("/list/{page}")
    public ResponseEntity<StatusMessage> list(@PathVariable("page") int page){
        jsonHeader();

        //List<BoardEntity> result = apiBoardService.getListAll();
        PageableBoardDto result = apiBoardService.getListPaging(10,page);
        if(!result.getBoard().isEmpty()){
            message.setData(result);
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @GetMapping("/listBySearch")
    public ResponseEntity<StatusMessage> listBySearch(@RequestParam("search") String search){
        jsonHeader();
        System.out.println("Search : "+ search);
        List<BoardEntity> result = apiBoardService.getListBySearch(search);
        if(!result.isEmpty()){
            message.setData(result);
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }


    @PostMapping("/write")
    public ResponseEntity<StatusMessage> write(CreateBoardDto createBoardDto){
        jsonHeader();
        Optional<BoardEntity> result = apiBoardService.write(createBoardDto);
        if(result.isPresent()){
            message.setData(result.get());
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<StatusMessage> delete(@PathVariable("id") Long id){
        jsonHeader();
        int result = apiBoardService.delete(id);
        if(result == 1){
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<StatusMessage> update(UpdateBoardDto updateBoardDto){
        jsonHeader();
        Optional<BoardEntity> result = apiBoardService.update(updateBoardDto);
        if(result.isPresent()){
            message.setData(result.get());
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
        }
        return new ResponseEntity<>(message, headers,HttpStatus.OK);
    }
}
