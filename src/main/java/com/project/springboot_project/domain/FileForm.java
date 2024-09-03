package com.project.springboot_project.domain;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileForm {
    private MultipartFile file;
    private String userId;
    private String fileUrl;
    private String filePath;
    private String fileExt;

    //파일 업로드 참고 사이트
    //https://leleluv1122.github.io/springboot/spring-boot-jpa-mysql-jsp-%ED%8C%8C%EC%9D%BC%EC%97%85%EB%A1%9C%EB%93%9C/
}
