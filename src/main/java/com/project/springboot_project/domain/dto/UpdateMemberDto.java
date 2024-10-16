package com.project.springboot_project.domain.dto;

import lombok.Data;

@Data
public class UpdateMemberDto {
    private String userId;
    private String userPw;
    private String nickname;
}
