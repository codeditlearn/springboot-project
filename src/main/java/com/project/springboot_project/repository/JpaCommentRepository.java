package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.CommentEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JpaCommentRepository implements CommentRepository{

    private final EntityManager em;
    public JpaCommentRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Optional<CommentEntity> save(CommentEntity commentEntity) {
        CommentEntity result;
        try {
            em.persist(commentEntity);
            result = commentEntity;
        }catch(Exception e){
            e.printStackTrace();
            result = null;
        }

        return Optional.ofNullable(result);
    }

    @Override
    public List<CommentEntity> findAll(Long bid) {

        List<CommentEntity> result = null;
        String sql = "SELECT c FROM CommentEntity c WHERE c.bid = :bid AND c.step = 1 ORDER BY regdate DESC LIMIT 10";
        result = em.createQuery(sql,CommentEntity.class)
                .setParameter("bid",bid)
                .getResultList();

        return result;
    }

    @Override
    public Optional<CommentEntity> findById(Long id){
        CommentEntity result = em.find(CommentEntity.class,id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<CommentEntity> findByRefAndBid(Long ref, Long bid){
        String sql = "SELECT c FROM CommentEntity c WHERE step = 2 AND ref = :ref AND bid = :bid";
        List<CommentEntity> result = em.createQuery(sql,CommentEntity.class)
                .setParameter("ref",ref)
                .setParameter("bid",bid)
                .getResultList();
        return result;
    }

    @Override
    public int findReplyCount(Long ref, Long bid){
        String sql = "SELECT count(c) FROM CommentEntity c WHERE step = 2 AND ref = :ref AND bid = :bid";
        int result = Integer.valueOf(
                em.createQuery(sql)
                        .setParameter("ref",ref)
                        .setParameter("bid",bid)
                        .getSingleResult().toString()
        );
        return result;

    }

    @Override
    public int update(CommentEntity commentEntity) {
        String sql = "UPDATE CommentEntity c SET content = :content WHERE id = :id";
        int result = em.createQuery(sql)
                .setParameter("content",commentEntity.getContent())
                .setParameter("id", commentEntity.getId())
                .executeUpdate();
        return result;
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM CommentEntity c WHERE c.id = :id";
        int result = em.createQuery(sql)
                .setParameter("id",id)
                .executeUpdate();
        return result;
    }
}
