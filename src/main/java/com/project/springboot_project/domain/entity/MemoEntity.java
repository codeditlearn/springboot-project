package com.project.springboot_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name="memo")
public class MemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="uuid", unique=true)
    private String uuid;
    private String background;
    private String content;
    @Column(name="USERID")
    private String userId;
    private Timestamp regdate;
    private Timestamp updatedate;
}
