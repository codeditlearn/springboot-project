package com.project.springboot_project.repository;

import com.project.springboot_project.domain.entity.BoardEntity;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JpaBoardRepository implements BoardRepository{

    private final EntityManager em;
    public JpaBoardRepository(EntityManager em) {
        this.em = em;
    } //@RequiredArgsConstructor 쓰면 생략가능

    @Override
    public BoardEntity save(BoardEntity boardEntity) {
        try {
            if(boardEntity.getId() != null){
                em.merge(boardEntity);
            }else{
                em.persist(boardEntity);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return boardEntity;
    }

    @Override
    public int delete(Long id) {
        String sql = "DELETE FROM BoardEntity b WHERE b.id = :id";
        int deleteResult = em.createQuery(sql)
                .setParameter("id",id)
                .executeUpdate();
        return deleteResult;
    }

    @Override
    public int updateAll(BoardEntity boardEntity) {
        System.out.println("update repo");

        String sql = "UPDATE BoardEntity b SET b.title = :title, b.content = :content,b.category = :category, b.thumbnail = :thumbnail, b.updatedate = :updatedate WHERE b.id = :id";
        int updateResult = em.createQuery(sql)
                .setParameter("title",boardEntity.getTitle())
                .setParameter("content",boardEntity.getContent())
                .setParameter("category",boardEntity.getCategory())
                .setParameter("thumbnail",boardEntity.getThumbnail())
                .setParameter("updatedate",boardEntity.getUpdatedate())
                .setParameter("id",boardEntity.getId())
                .executeUpdate();
        return updateResult;
    }

    @Override
    public Optional<BoardEntity> findByid(Long id) {
        BoardEntity result = em.find(BoardEntity.class,id);
        return Optional.ofNullable(result);
    }

    @Override
    public List<BoardEntity> findByPaging(int count, int page) {
        int startPage = count * (page - 1);
        System.out.println("count : "+ count + ", page : " + page + ", startPage : " + startPage);
        String sql = "SELECT b FROM BoardEntity b ORDER BY b.id DESC";

        List<BoardEntity> result;
        try {
            result = em.createQuery(sql,BoardEntity.class)
                    .setFirstResult(startPage) //시작할 위치
                    .setMaxResults(count) //쿼리할 개수
                    .getResultList();
            System.out.println("페이징 성공이오" + result);
        }catch(Exception e){
            result = new ArrayList<>();
        }
        return result;
    }


    @Override
    public List<BoardEntity> findAll(){
        String sql = "SELECT b FROM BoardEntity b ORDER BY b.id DESC";
        List<BoardEntity> result;
        try {
            result = em.createQuery(sql,BoardEntity.class).getResultList();
        }catch(Exception e){
            result = new ArrayList<>();
        }
        return result;
    }

//    @Override
//    public List<BoardEntity> findByPage(BoardPagingForm boardPagingForm){
//        List<BoardEntity> result = null;
//        int total = boardPagingForm.total;
//        int currentPage = boardPagingForm.currentPage;
//        int pagingCount = boardPagingForm.pagingCount;
//        String sql = "SELECT b FROM BoardEntity b ORDER BY b.type DESC, b.id DESC";
//
//
//        return result;
//    }

    @Override
    public List<BoardEntity> findBySearch(String search){
        System.out.println("검색어 : " + search);
        String sql = "SELECT b FROM BoardEntity b WHERE b.title LIKE concat('%', :title,'%') ORDER BY b.id DESC";
        List<BoardEntity> result = em.createQuery(sql,BoardEntity.class).setParameter("title",search).getResultList();

        return result;
    }

    @Override
    public void updateOne(Long id, String type){
        String column = null;
        if(type.equals("like")){
            column = "likecount";
        }else if(type.equals("view")){
            column = "viewcount";
        }

        String sql = "UPDATE BoardEntity b SET b."+column+" = b."+column+"+1 WHERE id = :id";
        em.createQuery(sql)
                .setParameter("id",id)
                .executeUpdate();
    }

    @Override
    public int countAll(){
        String sql = "SELECT COUNT(b) FROM BoardEntity b";
        int result = 0;
        result = Integer.valueOf(em.createQuery(sql)
                .getSingleResult().toString());

        System.out.println("count all :" + result);
        return result;

    }

    @Override
    public int[] count(String keyword){
        int[] result = null;
        int resultAll = 0;
        int resultNotice = 0;
        int resultDefault = 0;

        try {
            if(keyword == null || keyword.equals("")){
                String sqlAll = "SELECT COUNT(b) FROM BoardEntity b";
                String sqlType = "SELECT COUNT(b) FROM BoardEntity b WHERE b.category = :type";
                resultAll = Integer.valueOf(em.createQuery(sqlAll,BoardEntity.class)
                        .getSingleResult().toString());
                resultNotice = Integer.valueOf(em.createQuery(sqlType,BoardEntity.class)
                        .setParameter("type","notice")
                        .getSingleResult().toString());
                resultDefault = Integer.valueOf(em.createQuery(sqlType,BoardEntity.class)
                        .setParameter("type","default")
                        .getSingleResult().toString());
            }else{
                String sqlAll = "SELECT COUNT(b) FROM BoardEntity b WHERE b.title LIKE CONCAT('%',:title,'%')";
                String sqlType = "SELECT COUNT(b) FROM BoardEntity b WHERE b.category = :type AND b.title LIKE CONCAT('%',:title,'%')";
                resultAll = Integer.valueOf(em.createQuery(sqlAll,BoardEntity.class)
                        .setParameter("title",keyword)
                        .getSingleResult().toString());
                resultNotice = Integer.valueOf(em.createQuery(sqlType,BoardEntity.class)
                        .setParameter("type","notice")
                        .setParameter("title",keyword)
                        .getSingleResult().toString());
                resultDefault = Integer.valueOf(em.createQuery(sqlType,BoardEntity.class)
                        .setParameter("type","default")
                        .setParameter("title",keyword)
                        .getSingleResult().toString());
            }
        }catch(Exception e){
            resultAll = 0;
            resultNotice = 0;
            resultDefault = 0;
        }



        result = new int[]{resultAll,resultNotice,resultDefault};
        return result;
    }
}
