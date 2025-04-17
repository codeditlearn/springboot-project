package com.project.springboot_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name="emoticon")
public class EmoticonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @Column(name="USERID")
    private String userId;
    private String category;
    private int viewcount;
    private int likecount;
    private Timestamp regdate;
    private Timestamp updatedate;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String img6;
    private String img7;
    private String img8;
    private String img9;
    private String img10;
    private String img11;
    private String img12;
    private String img13;
    private String img14;
    private String img15;
    private String img16;
    private String img17;
    private String img18;
    private String img19;
    private String img20;
    private String img21;
    private String img22;
    private String img23;
    private String img24;
    private String img25;
    private String img26;
    private String img27;
    private String img28;
    private String img29;
    private String img30;
    private String img31;
    private String img32;
}
