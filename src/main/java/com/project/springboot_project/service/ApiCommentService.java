package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.entity.CommentEntity;
import com.project.springboot_project.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Transactional
@RequiredArgsConstructor
public class ApiCommentService {
    private final CommentRepository commentRepository;
    private final CommonMethod cm;


    public CommentEntity save(CommentEntity commentEntity){
        CommentEntity result = commentRepository.save(commentEntity).get();


        return result;

    }

    public int edit(CommentEntity commentEntity){
        int result = 0;
        try {
            result = commentRepository.update(commentEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<CommentEntity> list(Long bid){
        List<CommentEntity> result;
        try {
            result = commentRepository.findAll(bid);
            //System.out.println("이거 뭐여 result : "+result.get());
            AtomicInteger indexHolder = new AtomicInteger();
            result.stream().forEach(i -> {
                //System.out.printf("Index: %d, Element: %d\n", indexHolder.getAndIncrement(), i.getId());
                i.setReplycount(commentRepository.findReplyCount(i.getId(),i.getBid()));
                i.setReplyList(commentRepository.findByRefAndBid(i.getId(),i.getBid()));
                System.out.println("댓글 리스트 오브젝트 : {댓글 id : " + i.getId() + ", 댓글 bid : "+ i.getBid() + ", 댓글 참조 :" + i.getRef());
            });


//            result.stream()
//                    .map(item -> commentRepository.findReplyCount(item.getId(),item.getBid())).collect(Collectors.toList());
        }catch(Exception e){
            result = null;
        }
        return result;
    }

    public int remove(Long id,String userId){
        int result = 0;

        try {
            //작성자와 삭제하는 사람의 유저정보가 일치할 때
            if(commentRepository.findById(id).get().getUserId().equals(userId)){
                result = commentRepository.delete(id);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<CommentEntity> replyList(Long ref, Long bid){
        List<CommentEntity> result = null;
        try {
            result = commentRepository.findByRefAndBid(ref,bid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
