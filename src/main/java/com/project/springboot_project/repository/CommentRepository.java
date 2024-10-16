package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.CommentEntity;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    public Optional<CommentEntity> save(CommentEntity commentDto);
    public List<CommentEntity> findAll(Long bid);
    public Optional<CommentEntity> findById(Long id);
    public List<CommentEntity> findByRefAndBid(Long ref,Long bid);
    public int findReplyCount(Long ref, Long bid);
    public int update(CommentEntity commentDto);
    public int delete(Long id);

}
