package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.FileForm;
import com.project.springboot_project.domain.dto.CreateBoardDto;
import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional
public class ApiBoardService {
    private final BoardRepository boardRepository;
    private final CommonMethod cm;
    public ApiBoardService(BoardRepository boardRepository,CommonMethod cm){
        this.boardRepository = boardRepository;
        this.cm = cm;
    }

    public Optional<BoardEntity> write(CreateBoardDto createBoardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setUserId(createBoardDto.getUserId());
        boardEntity.setTitle(createBoardDto.getTitle());
        boardEntity.setContent(createBoardDto.getContent());
        boardEntity.setRegdate(cm.nowDate(null)); //등록날짜
        boardEntity.setRef(0L); //등록날짜

        /* 파일업로드 */
        FileForm fileForm = new FileForm();
        fileForm.setFile(createBoardDto.getFile());
        String fileUrl = cm.fileUpload(createBoardDto.getFile()); //파일업로드 주소
        boardEntity.setThumbnail(fileUrl);

        BoardEntity result = boardRepository.insert(boardEntity);

        return Optional.ofNullable(result);
    }

    public Optional<BoardEntity> update(CreateBoardDto createBoardDto){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setTitle(createBoardDto.getTitle());
        boardEntity.setContent(createBoardDto.getContent());
        boardEntity.setUpdatedate(cm.nowDate(null));

        /* 파일업로드 */
        FileForm fileForm = new FileForm();
        fileForm.setFile(createBoardDto.getFile());
        String fileUrl = cm.fileUpload(createBoardDto.getFile()); //파일업로드 주소
        boardEntity.setThumbnail(fileUrl);


        int result = boardRepository.updateAll(boardEntity);
        if(result == 1){
            return Optional.ofNullable(boardEntity);
        }
        return Optional.empty();
    }

    public int delete(Long id){
        int result = boardRepository.delete(id);
        return result;
    }

    public List<BoardEntity> getListAll(){
        List<BoardEntity> result = boardRepository.findAll();
        return result;
    }

    public List<BoardEntity> getListBySearch(String search){
        List<BoardEntity> listResult = boardRepository.findBySearch(search);
        return listResult;
    }

    public Optional<BoardEntity> getBoard(Long id){
        Optional<BoardEntity> result = boardRepository.findByid(id);
        if(result.isPresent()){
            boardRepository.updateOne(id,"view");
        }
        return result;

    }
}
