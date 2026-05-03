package com.hello.hello_spring.controller;


import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.domain.MemberForm;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller// 스프링 컨테이너가 관리를 한다.
public class MemberController {

    //private final MemberService memberService  = new MemberService();

    private final MemberService memberService;

    @Autowired // Dependency Injection DI 의존성 주입
    public MemberController(MemberService memberService){ // @빈등록 안되어 있으면 에러뜸.
        this.memberService=memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String creaate(MemberForm form){
        Member member = new Member();

        member.setName(form.getName());
        System.out.println(member.getName());
        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";

    }


}
