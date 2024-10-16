package com.project.springboot_project.controller;

import com.project.springboot_project.config.StatusEnum;
import com.project.springboot_project.config.StatusMessage;
import com.project.springboot_project.domain.dto.CreateMemberDto;
import com.project.springboot_project.domain.dto.UpdateMemberDto;
import com.project.springboot_project.domain.dto.ValidMemberDto;
import com.project.springboot_project.domain.entity.MemberEntity;
import com.project.springboot_project.service.ApiMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:3000")

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class ApiMemberController extends ApiController{

    private final ApiMemberService apiMemberService;
        /* @RequiredArgsConstructor를 사용해서 따로 생성자 주입이 필요없음  */
//    public ApiMemberController(ApiMemberService apiMemberService){
//        this.apiMemberService = apiMemberService;
//    }

    @PostMapping("/join")
    public ResponseEntity<StatusMessage> join(@Validated CreateMemberDto createMemberDto){
        jsonHeader();
        System.out.println("회원가입 : "+createMemberDto);
        Optional<MemberEntity> result = apiMemberService.join(createMemberDto);
        message.setMessage("회원가입 실패");
        if(result.isPresent()){
            result.get().setUserPw(null);
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setData(result);
        }

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<StatusMessage> login(ValidMemberDto validMemberDto){
        jsonHeader();
        Optional<MemberEntity> result = apiMemberService.login(validMemberDto);
        message.setMessage("로그인 실패");
        if(result.isPresent() && !result.get().getUserId().isEmpty()){
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setData(result.get());
        }

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @DeleteMapping("/leave")
    public ResponseEntity<StatusMessage> leave(ValidMemberDto validMemberDto){
        jsonHeader();
        boolean result = apiMemberService.leave(validMemberDto);
        message.setMessage("회원탈퇴 실패");
        if(result){
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setData(null);
        }

        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @GetMapping("/info/{userId}")
    public ResponseEntity<StatusMessage> info(@PathVariable("userId") String userId){
        jsonHeader();
        Optional<MemberEntity> result = apiMemberService.info(userId);
        message.setMessage("정보 조회 실패");
        if(result.isPresent()){
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setData(result.get());
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }

    @PostMapping("/edit")
    public ResponseEntity<StatusMessage> edit(UpdateMemberDto updateMemberDto){
        jsonHeader();
        System.out.println("UpdateMemberDto : " + updateMemberDto);
        Optional<MemberEntity> result = apiMemberService.update(updateMemberDto);
        message.setMessage("수정 실패");
        if(result.isPresent()){
            message.setMessage(StatusEnum.OK.getStatusMessage());
            message.setStatus(StatusEnum.OK.getStatusCode());
            message.setData(result.get());
        }
        return new ResponseEntity<>(message,headers,HttpStatus.OK);
    }


}
