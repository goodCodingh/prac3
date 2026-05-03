package com.hello.hello_spring.repository;

import com.hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    //이게 스태틱이기 때문에 MemoryMemberRepository가 다른 인스턴스여도 같은 HasgMap을 공유하는 것이다.
    private static long sequence = 0L;



    @Override
    public List<Member> findAll() {

        return new ArrayList<>(store.values());

    }

    @Override
    public Optional<Member> findByName(String name) {
        Optional<Member> member1 = store.values().stream()
                .filter(member -> member.getName().equals(name)).findAny();

        return member1;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Optional<Member> member1 = Optional.ofNullable( store.get(id));

        return member1;

    }

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(),member);

        return member;
    }

    public void clearStore(){
        store.clear();
    }
}
