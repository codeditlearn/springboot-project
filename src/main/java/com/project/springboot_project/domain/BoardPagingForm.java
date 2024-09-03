package com.project.springboot_project.domain;

import java.util.List;

public class BoardPagingForm{
    // 전체 글의 행의 수
    private int total;
    // 현재 페이지 번호
    private int currentPage;
    // 전체 페이지 개수
    private int totalPages;
    // 시작 페이지 번호
    private int startPage;
    // 종료 페이지 번호
    private int endPage;
    // 페이징의 개수
    private int pagingCount;
}
