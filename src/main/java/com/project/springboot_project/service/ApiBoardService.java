package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.dto.CreateBoardDto;
import com.project.springboot_project.domain.dto.PageableBoardDto;
import com.project.springboot_project.domain.dto.UpdateBoardDto;
import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.repository.BoardRepository;
import org.springframework.transaction.annotation.Transactional;

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
        boardEntity.setCategory(createBoardDto.getCategory());
        boardEntity.setRegdate(cm.nowDate(null)); //등록날짜
        boardEntity.setRef(0L); //참조값

        /* 파일업로드 */

        String fileUrl = cm.fileUpload(createBoardDto.getFile()); //파일업로드 주소
        boardEntity.setThumbnail(fileUrl);

        BoardEntity result = boardRepository.save(boardEntity);

        return Optional.ofNullable(result);
    }

    public Optional<BoardEntity> update(UpdateBoardDto updateBoardDto){
        System.out.println("updateBoardDto" + updateBoardDto);
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(Long.valueOf(updateBoardDto.getId()));
        boardEntity.setUserId(updateBoardDto.getUserId());
        boardEntity.setTitle(updateBoardDto.getTitle());
        boardEntity.setContent(updateBoardDto.getContent());
        boardEntity.setCategory(updateBoardDto.getCategory());
        boardEntity.setUpdatedate(cm.nowDate(null));

        /* 파일업로드 */
        String fileUrl = cm.fileUpload(updateBoardDto.getFile()); //파일업로드 주소
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

    public PageableBoardDto getListPaging(int count, int page){
        List<BoardEntity> result = boardRepository.findByPaging(count,page);

        int allCount = boardRepository.countAll();
        PageableBoardDto pageableBoardDto = new PageableBoardDto();
        pageableBoardDto.setCount(allCount);
        pageableBoardDto.setBoard(result);


        return pageableBoardDto;
    }

    public Optional<BoardEntity> getBoard(Long id){
        Optional<BoardEntity> result = boardRepository.findByid(id);
        if(result.isPresent()){
            boardRepository.updateOne(id,"view");
        }
        return result;

    }
}
