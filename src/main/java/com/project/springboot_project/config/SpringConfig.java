package com.project.springboot_project.config;

import com.project.springboot_project.repository.*;
import com.project.springboot_project.service.*;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringConfig{

    private final EntityManager em;
    private final PasswordEncoder passwordEncoder;
    private final CommonMethod cm;

    @Autowired
    public SpringConfig(EntityManager em,PasswordEncoder passwordEncoder,CommonMethod cm) {
        this.em = em;
        this.passwordEncoder = passwordEncoder;
        this.cm = cm;
    }

    /*Member*/
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);
    }

    @Bean
    public ApiMemberService apiMemberService(){return new ApiMemberService(memberRepository(),passwordEncoder,cm);}

    /*Board*/
    @Bean
    public BoardRepository boardRepository(){
        return new JpaBoardRepository(em);
    }

    @Bean
    public ApiBoardService apiBoardService(){return new ApiBoardService(boardRepository(),cm);}


    /*Comment*/
    @Bean
    public CommentRepository commentRepository(){return new JpaCommentRepository(em);}
    @Bean
    public ApiCommentService apiCommentService(){return new ApiCommentService(commentRepository(),cm);}

    /*Memo*/
    @Bean
    public MemoRepository memoRepository(){return new JpaMemoRepository(em);}
    @Bean
    public ApiMemoService apiMemoService(){return new ApiMemoService(memoRepository(),cm);}

    /*Emoticon*/
    @Bean
    public EmoticonRepository emoticonRepository(){return new JpaEmoticonRepositoryImpl(em);}
    @Bean
    public ApiEmoticonService emoticonService(){return new ApiEmoticonService(emoticonRepository(),cm);}
}
