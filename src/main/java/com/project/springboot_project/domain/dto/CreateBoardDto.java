package com.project.springboot_project.domain.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateBoardDto {

    private String userId;
    private String title;
    private String content;
    private String category;
    private MultipartFile file;

}
