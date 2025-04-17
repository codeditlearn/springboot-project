package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.dto.CreateEmoticonDto;
import com.project.springboot_project.domain.dto.UpdateEmoticonDto;
import com.project.springboot_project.domain.entity.BoardEntity;
import com.project.springboot_project.domain.entity.EmoticonEntity;
import com.project.springboot_project.repository.BoardRepository;
import com.project.springboot_project.repository.EmoticonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class ApiEmoticonService {
    private final EmoticonRepository emoticonRepository;
    private final CommonMethod cm;

    @Autowired
    public ApiEmoticonService(EmoticonRepository emoticonRepository, CommonMethod cm){
        this.emoticonRepository = emoticonRepository;
        this.cm = cm;
    }

    public List<EmoticonEntity> getListAll(){
        List<EmoticonEntity> result = emoticonRepository.findAll();
        return result;
    }

    public List<EmoticonEntity> getListPaging(int count, int page){
        List<EmoticonEntity> result = emoticonRepository.findByPaging(count,page);
        return result;

    }

    public Optional<EmoticonEntity> getOne(Long id){
        Optional<EmoticonEntity> result = emoticonRepository.findById(id);
        return result;
    }

    public Optional<EmoticonEntity> write(CreateEmoticonDto createEmoticonDto){
        EmoticonEntity emoticonEntity = new EmoticonEntity();
        if(createEmoticonDto.getImg1() != null){
            emoticonEntity.setImg1(cm.fileUpload(createEmoticonDto.getImg1()));
        }
        if(createEmoticonDto.getImg2() != null){
            emoticonEntity.setImg2(cm.fileUpload(createEmoticonDto.getImg2()));
        }
        if(createEmoticonDto.getImg3() != null){
            emoticonEntity.setImg3(cm.fileUpload(createEmoticonDto.getImg3()));
        }
        if(createEmoticonDto.getImg4() != null){
            emoticonEntity.setImg4(cm.fileUpload(createEmoticonDto.getImg4()));
        }
        if(createEmoticonDto.getImg5() != null){
            emoticonEntity.setImg5(cm.fileUpload(createEmoticonDto.getImg5()));
        }
        if(createEmoticonDto.getImg6() != null){
            emoticonEntity.setImg6(cm.fileUpload(createEmoticonDto.getImg6()));
        }
        if(createEmoticonDto.getImg7() != null){
            emoticonEntity.setImg7(cm.fileUpload(createEmoticonDto.getImg7()));
        }
        if(createEmoticonDto.getImg8() != null){
            emoticonEntity.setImg8(cm.fileUpload(createEmoticonDto.getImg8()));
        }
        if(createEmoticonDto.getImg9() != null){
            emoticonEntity.setImg9(cm.fileUpload(createEmoticonDto.getImg9()));
        }
        if(createEmoticonDto.getImg10() != null){
            emoticonEntity.setImg10(cm.fileUpload(createEmoticonDto.getImg10()));
        }
        if(createEmoticonDto.getImg11() != null){
            emoticonEntity.setImg11(cm.fileUpload(createEmoticonDto.getImg11()));
        }
        if(createEmoticonDto.getImg12() != null){
            emoticonEntity.setImg12(cm.fileUpload(createEmoticonDto.getImg12()));
        }
        if(createEmoticonDto.getImg13() != null){
            emoticonEntity.setImg13(cm.fileUpload(createEmoticonDto.getImg13()));
        }
        if(createEmoticonDto.getImg14() != null){
            emoticonEntity.setImg14(cm.fileUpload(createEmoticonDto.getImg14()));
        }
        if(createEmoticonDto.getImg15() != null){
            emoticonEntity.setImg15(cm.fileUpload(createEmoticonDto.getImg15()));
        }
        if(createEmoticonDto.getImg16() != null){
            emoticonEntity.setImg16(cm.fileUpload(createEmoticonDto.getImg16()));
        }
        if(createEmoticonDto.getImg17() != null){
            emoticonEntity.setImg17(cm.fileUpload(createEmoticonDto.getImg17()));
        }
        if(createEmoticonDto.getImg18() != null){
            emoticonEntity.setImg18(cm.fileUpload(createEmoticonDto.getImg18()));
        }
        if(createEmoticonDto.getImg19() != null){
            emoticonEntity.setImg19(cm.fileUpload(createEmoticonDto.getImg19()));
        }
        if(createEmoticonDto.getImg20() != null){
            emoticonEntity.setImg20(cm.fileUpload(createEmoticonDto.getImg20()));
        }
        if(createEmoticonDto.getImg21() != null){
            emoticonEntity.setImg21(cm.fileUpload(createEmoticonDto.getImg21()));
        }
        if(createEmoticonDto.getImg22() != null){
            emoticonEntity.setImg22(cm.fileUpload(createEmoticonDto.getImg22()));
        }
        if(createEmoticonDto.getImg23() != null){
            emoticonEntity.setImg23(cm.fileUpload(createEmoticonDto.getImg23()));
        }
        if(createEmoticonDto.getImg24() != null){
            emoticonEntity.setImg24(cm.fileUpload(createEmoticonDto.getImg24()));
        }
        if(createEmoticonDto.getImg25() != null){
            emoticonEntity.setImg25(cm.fileUpload(createEmoticonDto.getImg25()));
        }
        if(createEmoticonDto.getImg26() != null){
            emoticonEntity.setImg26(cm.fileUpload(createEmoticonDto.getImg26()));
        }
        if(createEmoticonDto.getImg27() != null){
            emoticonEntity.setImg27(cm.fileUpload(createEmoticonDto.getImg27()));
        }
        if(createEmoticonDto.getImg28() != null){
            emoticonEntity.setImg28(cm.fileUpload(createEmoticonDto.getImg28()));
        }
        if(createEmoticonDto.getImg29() != null){
            emoticonEntity.setImg29(cm.fileUpload(createEmoticonDto.getImg29()));
        }
        if(createEmoticonDto.getImg30() != null){
            emoticonEntity.setImg30(cm.fileUpload(createEmoticonDto.getImg30()));
        }
        if(createEmoticonDto.getImg31() != null){
            emoticonEntity.setImg31(cm.fileUpload(createEmoticonDto.getImg31()));
        }
        if(createEmoticonDto.getImg32() != null){
            emoticonEntity.setImg32(cm.fileUpload(createEmoticonDto.getImg32()));
        }

        emoticonEntity.setContent(createEmoticonDto.getContent());
        emoticonEntity.setTitle(createEmoticonDto.getTitle());
        emoticonEntity.setUserId(createEmoticonDto.getUserId());
        emoticonEntity.setCategory(createEmoticonDto.getCategory());
        emoticonEntity.setRegdate(cm.nowDate(null));
        EmoticonEntity result = emoticonRepository.save(emoticonEntity);


        return Optional.ofNullable(result);
    }

    public Optional<EmoticonEntity> update(UpdateEmoticonDto updateEmoticonDto){
        EmoticonEntity emoticonEntity = new EmoticonEntity();
        if(updateEmoticonDto.getImg1() != null){
            emoticonEntity.setImg1(cm.fileUpload(updateEmoticonDto.getImg1()));
        }
        if(updateEmoticonDto.getImg2() != null){
            emoticonEntity.setImg2(cm.fileUpload(updateEmoticonDto.getImg2()));
        }
        if(updateEmoticonDto.getImg3() != null){
            emoticonEntity.setImg3(cm.fileUpload(updateEmoticonDto.getImg3()));
        }
        if(updateEmoticonDto.getImg4() != null){
            emoticonEntity.setImg4(cm.fileUpload(updateEmoticonDto.getImg4()));
        }
        if(updateEmoticonDto.getImg5() != null){
            emoticonEntity.setImg5(cm.fileUpload(updateEmoticonDto.getImg5()));
        }
        if(updateEmoticonDto.getImg6() != null){
            emoticonEntity.setImg6(cm.fileUpload(updateEmoticonDto.getImg6()));
        }
        if(updateEmoticonDto.getImg7() != null){
            emoticonEntity.setImg7(cm.fileUpload(updateEmoticonDto.getImg7()));
        }
        if(updateEmoticonDto.getImg8() != null){
            emoticonEntity.setImg8(cm.fileUpload(updateEmoticonDto.getImg8()));
        }
        if(updateEmoticonDto.getImg9() != null){
            emoticonEntity.setImg9(cm.fileUpload(updateEmoticonDto.getImg9()));
        }
        if(updateEmoticonDto.getImg10() != null){
            emoticonEntity.setImg10(cm.fileUpload(updateEmoticonDto.getImg10()));
        }
        if(updateEmoticonDto.getImg11() != null){
            emoticonEntity.setImg11(cm.fileUpload(updateEmoticonDto.getImg11()));
        }
        if(updateEmoticonDto.getImg12() != null){
            emoticonEntity.setImg12(cm.fileUpload(updateEmoticonDto.getImg12()));
        }
        if(updateEmoticonDto.getImg13() != null){
            emoticonEntity.setImg13(cm.fileUpload(updateEmoticonDto.getImg13()));
        }
        if(updateEmoticonDto.getImg14() != null){
            emoticonEntity.setImg14(cm.fileUpload(updateEmoticonDto.getImg14()));
        }
        if(updateEmoticonDto.getImg15() != null){
            emoticonEntity.setImg15(cm.fileUpload(updateEmoticonDto.getImg15()));
        }
        if(updateEmoticonDto.getImg16() != null){
            emoticonEntity.setImg16(cm.fileUpload(updateEmoticonDto.getImg16()));
        }
        if(updateEmoticonDto.getImg17() != null){
            emoticonEntity.setImg17(cm.fileUpload(updateEmoticonDto.getImg17()));
        }
        if(updateEmoticonDto.getImg18() != null){
            emoticonEntity.setImg18(cm.fileUpload(updateEmoticonDto.getImg18()));
        }
        if(updateEmoticonDto.getImg19() != null){
            emoticonEntity.setImg19(cm.fileUpload(updateEmoticonDto.getImg19()));
        }
        if(updateEmoticonDto.getImg20() != null){
            emoticonEntity.setImg20(cm.fileUpload(updateEmoticonDto.getImg20()));
        }
        if(updateEmoticonDto.getImg21() != null){
            emoticonEntity.setImg21(cm.fileUpload(updateEmoticonDto.getImg21()));
        }
        if(updateEmoticonDto.getImg22() != null){
            emoticonEntity.setImg22(cm.fileUpload(updateEmoticonDto.getImg22()));
        }
        if(updateEmoticonDto.getImg23() != null){
            emoticonEntity.setImg23(cm.fileUpload(updateEmoticonDto.getImg23()));
        }
        if(updateEmoticonDto.getImg24() != null){
            emoticonEntity.setImg24(cm.fileUpload(updateEmoticonDto.getImg24()));
        }
        if(updateEmoticonDto.getImg25() != null){
            emoticonEntity.setImg25(cm.fileUpload(updateEmoticonDto.getImg25()));
        }
        if(updateEmoticonDto.getImg26() != null){
            emoticonEntity.setImg26(cm.fileUpload(updateEmoticonDto.getImg26()));
        }
        if(updateEmoticonDto.getImg27() != null){
            emoticonEntity.setImg27(cm.fileUpload(updateEmoticonDto.getImg27()));
        }
        if(updateEmoticonDto.getImg28() != null){
            emoticonEntity.setImg28(cm.fileUpload(updateEmoticonDto.getImg28()));
        }
        if(updateEmoticonDto.getImg29() != null){
            emoticonEntity.setImg29(cm.fileUpload(updateEmoticonDto.getImg29()));
        }
        if(updateEmoticonDto.getImg30() != null){
            emoticonEntity.setImg30(cm.fileUpload(updateEmoticonDto.getImg30()));
        }
        if(updateEmoticonDto.getImg31() != null){
            emoticonEntity.setImg31(cm.fileUpload(updateEmoticonDto.getImg31()));
        }
        if(updateEmoticonDto.getImg32() != null){
            emoticonEntity.setImg32(cm.fileUpload(updateEmoticonDto.getImg32()));
        }
        emoticonEntity.setId(updateEmoticonDto.getId());
        emoticonEntity.setContent(updateEmoticonDto.getContent());
        emoticonEntity.setTitle(updateEmoticonDto.getTitle());
        emoticonEntity.setUserId(updateEmoticonDto.getUserId());
        emoticonEntity.setCategory(updateEmoticonDto.getCategory());
        emoticonEntity.setUpdatedate(cm.nowDate(null));
        EmoticonEntity result = emoticonRepository.save(emoticonEntity);

        return Optional.ofNullable(result);
    }

    public int delete(Long id){
        int result = emoticonRepository.delete(id);
        return result;

    }
}
