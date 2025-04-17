package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.EmoticonEntity;

import java.util.List;
import java.util.Optional;

public interface EmoticonRepository {
    public EmoticonEntity save(EmoticonEntity emoticonEntity);
    public List<EmoticonEntity> findAll();
    public int delete(Long id);
    public Optional<EmoticonEntity> findById(Long id);
    public List<EmoticonEntity> findByPaging(int count,int page);

}
