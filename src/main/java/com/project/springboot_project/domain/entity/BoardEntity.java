package com.project.springboot_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Long ref;
    @Column(name="USERID")
    private String userId;
    private String thumbnail;
    private String category;
    private int step;
    private int viewcount;
    private int likecount;
    private Timestamp regdate;
    private Timestamp updatedate;
}
