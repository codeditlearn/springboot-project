package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.BoardEntity;

import java.util.List;
import java.util.Optional;

public interface BoardRepository {
    public BoardEntity save(BoardEntity board);
    public int delete(Long id);
    public int updateAll(BoardEntity board);
    public Optional<BoardEntity> findByid(Long id);
    public List<BoardEntity> findByPaging(int count, int page);
    public List<BoardEntity> findAll();
    public List<BoardEntity> findBySearch(String search);
    //public List<BoardDto> findByPage(BoardPagingForm boardPagingForm);
    public void updateOne(Long id, String type);
    public int[] count(String keyword);
    public int countAll();
}
