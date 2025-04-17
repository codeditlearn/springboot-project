package com.project.springboot_project.service;

import com.project.springboot_project.config.CommonMethod;
import com.project.springboot_project.domain.dto.CreateMemberDto;
import com.project.springboot_project.domain.dto.UpdateMemberDto;
import com.project.springboot_project.domain.dto.ValidMemberDto;
import com.project.springboot_project.domain.entity.MemberEntity;
import com.project.springboot_project.repository.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class ApiMemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CommonMethod cm;
    public ApiMemberService(MemberRepository memberRepository,PasswordEncoder passwordEncoder,CommonMethod cm){
        this.memberRepository = memberRepository;
        this.passwordEncoder = passwordEncoder;
        this.cm = cm;
    }



    public Optional<MemberEntity> join(CreateMemberDto createMemberDto){

        /* 아이디 검증 */
        boolean validated = validatedUserId(createMemberDto.getUserId());
        /* 아이디 중복 */
        if(validated){
            return Optional.empty();
        }

        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setName(createMemberDto.getName());
        memberEntity.setUserId(createMemberDto.getUserId());
        memberEntity.setNickname(createMemberDto.getNickname());
        memberEntity.setGrade(0);
        memberEntity.setPoint(0);
        memberEntity.setRegdate(cm.nowDate(null));
        memberEntity.setUserPw(passwordEncoder.encode(createMemberDto.getUserPw()));
        int result = memberRepository.save(memberEntity);
        if(result == 1){
            return Optional.ofNullable(memberEntity);
        }else{
            return Optional.empty();
        }


    }


    /* 아이디 검증 */
    public boolean validatedUserId(String userId){
        return memberRepository.findByUserId(userId).isPresent();
    }

    public Optional<MemberEntity> login(ValidMemberDto validMemberDto){
        MemberEntity member = new MemberEntity();
        Optional<MemberEntity> findUserId = memberRepository.findByUserId(validMemberDto.getUserId());
        findUserId.ifPresent(rs->{
            boolean validatedPw = passwordEncoder.matches(validMemberDto.getUserPw(),findUserId.get().getUserPw());
            if(validatedPw){
                member.setUserId(findUserId.get().getUserId());
                member.setUserPw(null);
                member.setName(findUserId.get().getName());
                member.setNickname(findUserId.get().getNickname());
                member.setGrade(findUserId.get().getGrade());
                member.setRegdate(findUserId.get().getRegdate());
                member.setPoint(findUserId.get().getPoint());
                member.setImg(findUserId.get().getImg());
            }
        });

        return Optional.ofNullable(member);
    }


    public boolean leave(ValidMemberDto validMemberDto){
        Optional<MemberEntity> findUserId = memberRepository.findByUserId(validMemberDto.getUserId());

        if(findUserId.isPresent()){
            boolean validatedPw = passwordEncoder.matches(validMemberDto.getUserPw(),findUserId.get().getUserPw());
            if(validatedPw){
                int result = memberRepository.delete(validMemberDto.getUserId());
                if(result == 1){
                    return true;
                }
            }
        }

        return false;
    }

    public Optional<MemberEntity> info(String userId){
        Optional<MemberEntity> result = memberRepository.findByUserId(userId);
        result.ifPresent(x->{
            result.get().setUserPw(null);
        });
        return result;
    }

    public Optional<MemberEntity> update(UpdateMemberDto updateMemberDto){
        MemberEntity member = memberRepository.findByUserId(updateMemberDto.getUserId())
                .orElseThrow(() ->  {throw new IllegalStateException("값이 없음"); });
        member.setNickname(updateMemberDto.getNickname());
        //member.setUserPw(passwordEncoder.encode(updateMemberDto.getUserPw()));
        memberRepository.save(member);
        Optional<MemberEntity> result = Optional.ofNullable(member);

        return result;
    }


}
