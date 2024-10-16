package com.project.springboot_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Data
@Table(name="comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bid;
    private Long ref;
    @Column(name="USERID")
    private String userId;
    private String nickname;
    private int step;
    private String content;
    private Timestamp regdate;
    private Timestamp updatedate;
    @Transient
    private int replycount;
    @Transient
    private List<CommentEntity> replyList;
}
