package com.project.springboot_project.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "member")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="USERID")
    private String userId;
    @Column(name="USERPW")
    private String userPw;
    private String name;
    private String nickname;
    private String img;
    private int grade;
    private int point;
    private Timestamp regdate;
}
/* SQL */
//CREATE OR REPLACE SEQUENCE `member_seq` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB
