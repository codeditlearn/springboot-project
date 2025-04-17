package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.domain.entity.EmoticonEntity;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaEmoticonRepositoryImpl implements EmoticonRepository{

    private final EntityManager em;
    public JpaEmoticonRepositoryImpl(EntityManager em){
        this.em = em;
    }


    @Override
    public EmoticonEntity save(EmoticonEntity emoticonEntity) {
        try{
            if(emoticonEntity.getId() != null){
                em.merge(emoticonEntity);
            }else{
                em.persist(emoticonEntity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return emoticonEntity;
    }

    @Override
    public List<EmoticonEntity> findAll() {
        List<EmoticonEntity> result = null;

        return result;
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM EmoticonEntity e WHERE e.id = :id";
        int result = em.createQuery(sql)
                .setParameter("id",id)
                .executeUpdate();
        return result;
    }

    @Override
    public Optional<EmoticonEntity> findById(Long id) {
        EmoticonEntity result = em.find(EmoticonEntity.class,id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<EmoticonEntity> findByPaging(int count,int page) {
        int startPage = count * (page - 1);
        String sql = "SELECT e FROM EmoticonEntity e ORDER BY e.id DESC";

        List<EmoticonEntity> result;
        try {
            result = em.createQuery(sql,EmoticonEntity.class)
                    .setFirstResult(startPage) //시작할 위치
                    .setMaxResults(count) //쿼리할 개수
                    .getResultList();
            System.out.println("페이징 성공이오" + result);
        }catch(Exception e){
            result = new ArrayList<>();
        }
        return result;
    }
}
