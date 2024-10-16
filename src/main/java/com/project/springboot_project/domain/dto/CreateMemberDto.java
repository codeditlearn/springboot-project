package com.project.springboot_project.domain.dto;
import lombok.Data;
import lombok.NonNull;

@Data
@NonNull
public class CreateMemberDto {
    private String userId;
    private String userPw;
    private String name;
    private String nickname;
}
