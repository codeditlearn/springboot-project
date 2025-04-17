package com.project.springboot_project.domain.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@NonNull
public class CreateCommentDto {
    private String bid;
    private String content;
    private String userId;
    private String ref;
    private String step;
    private String nickname;
}
