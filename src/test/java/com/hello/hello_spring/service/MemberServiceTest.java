package com.hello.hello_spring.service;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {


    //MemberService memberService = new MemberService();
    MemberService memberService ;
    MemoryMemberRepository repository ;

    @BeforeEach
    public void beforeEach(){
        repository= new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    void 회원가입() {//한글도 가능.
        //given
        //이런 상황이 주어졌을 때

        Member member = new Member();
        member.setName("spring");
        Member member2 = new Member();
        member2.setName("hello1");
        //when
        //이걸 실행했을 떄에.

        Long saveId = memberService.join(member);

        Long saveId2 = memberService.join(member2); // 여기서 에러 발생.
        //then
        //이러한 결과가 나와야 해.
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());



        //빌드될 때 테스트는 실제 코드에 포함되지 않음.
    }

    @Test public void  중복회원 (){
        //given

        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring2");

        //when
        memberService.join(member1);
        //assertThrows(IllegalStateException.class,() -> memberService.join(member1));

        IllegalStateException e = assertThrows(IllegalStateException.class,() -> memberService.join(member1));


        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }*/

    }

    public void fail(){
        System.out.println("페일입니다");
    }



    @Test
    void findMembers() {



    }

    @Test
    void findOne() {
    }
}