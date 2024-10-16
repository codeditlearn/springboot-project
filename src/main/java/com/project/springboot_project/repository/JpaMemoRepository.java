package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.MemoEntity;
import jakarta.persistence.EntityManager;

import java.util.List;

public class JpaMemoRepository implements MemoRepository{
    private final EntityManager em;
    public JpaMemoRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public void save(MemoEntity memoEntity) {
        em.persist(memoEntity);
    }

    @Override
    public int delete(String uuid) {
        String sql = "DELETE FROM MemoEntity m WHERE uuid = :uuid";
        int result = em.createQuery(sql)
                .setParameter("uuid",uuid)
                .executeUpdate();
        return result;
    }

    @Override
    public int deleteAll(String userId) {
        String sql = "DELETE FROM MemoEntity m WHERE userId = :userId";
        int result = em.createQuery(sql)
                .setParameter("userId",userId)
                .executeUpdate();
        return result;
    }

    @Override
    public int update(MemoEntity memoEntity) {
        String sql = "UPDATE MemoEntity m SET background = :background, content = :content, updatedate = :updatedate WHERE uuid =: uuid";
        int result = em.createQuery(sql)
                .setParameter("background" ,memoEntity.getBackground())
                .setParameter("content",memoEntity.getContent())
                .setParameter("updatedate",memoEntity.getUpdatedate())
                .setParameter("uuid",memoEntity.getUuid())
                .executeUpdate();
        return result;
    }

    @Override
    public List<MemoEntity> findAll(String userId) {
        String sql = "SELECT m FROM MemoEntity m WHERE userId = :userId";
        List<MemoEntity> result = em.createQuery(sql,MemoEntity.class)
                .setParameter("userId",userId)
                .getResultList();
        return result;
    }
}
