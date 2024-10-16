package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.entity.MemberEntity;
import com.project.springboot_project.domain.entity.MemoEntity;
import com.project.springboot_project.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional
public class ApiMemoService {

    private final MemoRepository memoRepository;
    private final CommonMethod cm;

    public int save(MemoEntity memoEntity){
        int result = 1;
        try {
            memoEntity.setRegdate(cm.nowDate(null));
            memoRepository.save(memoEntity);
        }catch (Exception e){
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    public int edit(MemoEntity memoEntity){
        int result = 0;
        try {
            memoEntity.setUpdatedate(cm.nowDate(null));
            result = memoRepository.update(memoEntity);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int remove(String uuid){
        int result = 0;
        try {
            result = memoRepository.delete(uuid);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public int removeAll(String userId){
        int result = 0;
        try {
            result = memoRepository.deleteAll(userId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public List<MemoEntity> list(String userId){
        List<MemoEntity> result;
        try {
            result = memoRepository.findAll(userId);
        }catch (Exception e){
            e.printStackTrace();
            result = new ArrayList<>();
        }
        return result;
    }



}
