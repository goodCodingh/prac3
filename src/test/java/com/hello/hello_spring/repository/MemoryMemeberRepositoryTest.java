package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import  static org.assertj.core.api.Assertions.*;

import java.util.List;
import java.util.Optional;

public class MemoryMemeberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

        @Test
        public void save() {
            Member member = new Member();

            member.setName("SpringMan");

            Member memberTest = repository.save(member);

            Optional<Member> findedMember = repository.findById(member.getId());

            Member result = repository.findById(member.getId()).get();

            System.out.println();



            assertThat(member).isEqualTo(result);
        }

        @Test
        public void findByName(){

            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            Optional<Member> result = repository.findByName("spring1");

            Member testResult = repository.findByName("spring1").get();

            assertThat(testResult).isEqualTo(member1);



        }

        @Test
        public void findAll(){
            Member member1 = new Member();
            member1.setName("spring1");
            repository.save(member1);

            Member member2 = new Member();
            member2.setName("spring2");
            repository.save(member2);

            List<Member> list = repository.findAll();

            assertThat(list.size()).isEqualTo(2);

        }



}
