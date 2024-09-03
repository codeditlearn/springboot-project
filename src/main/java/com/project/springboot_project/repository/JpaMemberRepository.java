package com.project.springboot_project.repository;

import com.project.springboot_project.domain.FileForm;
import com.project.springboot_project.domain.entity.MemberEntity;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;


public class JpaMemberRepository implements MemberRepository {
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public int save(MemberEntity memberEntity) {
        int result = 0;
        try {
            em.persist(memberEntity);
            result = 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Optional<MemberEntity> findByUserId(String userId) {
        List<MemberEntity> result = em.createQuery("SELECT m FROM MemberEntity m WHERE m.userId = :userId", MemberEntity.class)
                .setParameter("userId", userId)
                .getResultList();
        em.clear();
        return result.stream().findAny();
    }
    public Optional<MemberEntity> findByUser(String userId, String userPw){
        List<MemberEntity> result = em.createQuery("SELECT m FROM MemberEntity m WHERE m.userId = :userId AND m.userPw = :userPw",MemberEntity.class)
                .setParameter("userId",userId)
                .setParameter("userPw",userPw)
                .getResultList();
        em.clear();
        return result.stream().findAny();
    }


    @Override
    public List<MemberEntity> findAll() {
        List<MemberEntity> result = em.createQuery("SELECT m FROM MemberEntity m ORDER BY m.regdate DESC",MemberEntity.class)
                .getResultList();
        em.clear();
        return result;
    }

    @Override
    public int delete(String userId) {
        int result = em.createQuery("DELETE FROM MemberEntity m WHERE m.userId = :userId")
                .setParameter("userId",userId)
                .executeUpdate();
        em.clear();
        return result;
    }
    /*파일업로드*/
    @Override
    public void update(FileForm fileForm){
        String img = fileForm.getFileUrl();
        String userId = fileForm.getUserId();
        em.createQuery("UPDATE MemberEntity m SET m.img = :img WHERE m.userId = :userId",MemberEntity.class)
                .setParameter("img",img)
                .setParameter("userId",userId)
                .executeUpdate();

        em.clear();
    }
}
