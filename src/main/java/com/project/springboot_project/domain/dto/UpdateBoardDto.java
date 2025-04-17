package com.project.springboot_project.domain.dto;

import lombok.Data;
import lombok.NonNull;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateBoardDto {
    private String id;
    private String userId;
    private String title;
    private String content;
    private String category;
    private MultipartFile file;
}
