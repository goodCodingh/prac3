package com.hello.hello_spring;


import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository()); // 이걸 호출해서 스프링 컨테이너에 스프링빈으로 등록을 한다.
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository(); // 구현체로 리턴을 해야한다. 실질적인 것은 결국 return이다.
    }



}
