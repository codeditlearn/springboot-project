package com.project.springboot_project.domain.dto;

import com.project.springboot_project.domain.entity.BoardEntity;
import lombok.Data;

import java.util.List;

@Data
public class PageableBoardDto {
    private int count;
    private List<BoardEntity> board;
}
