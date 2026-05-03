package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemberRepository;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Service
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();


    private final MemberRepository memberRepository;

    //@Autowired //스프링 컨테이너에 등록된 memberRepository를 주입한다.
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }//서비스가 직접 new 로 생성하지않고 외부에서 받아오고 있다. dependency injection DI

    public Long join(Member member){

        validateDuplicateMember(member);
            memberRepository.save(member);
        //Optional<Member> result = memberRepository.findByName(member.getName());



        //memberRepository.save(member);

        return member.getId();


    }

    private void validateDuplicateMember(Member member){

        memberRepository.findByName(member.getName()).ifPresent( m->{
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });

    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }


}
