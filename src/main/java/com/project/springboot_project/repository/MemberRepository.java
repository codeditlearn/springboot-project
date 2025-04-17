package com.project.springboot_project.repository;

import com.project.springboot_project.domain.dto.UpdateMemberDto;
import com.project.springboot_project.domain.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    public int save(MemberEntity memberEntity);
    public Optional<MemberEntity> findByUserId(String userId);
    public List<MemberEntity> findAll();
    public int delete(String userId);
    public Optional<MemberEntity> findByUser(String userId, String userPw);



}
