package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.MemoEntity;

import java.util.List;

public interface MemoRepository {
    public void save(MemoEntity memoEntity);
    public int delete(String uuid);
    public int deleteAll(String userId);
    public int update(MemoEntity memoEntity);
    public List<MemoEntity> findAll(String userId);
}
