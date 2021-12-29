package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository mmr = new MemoryMemberRepository();


    @Test
    void 회원등록() {
        //given ==> 회원등록
        Member member = new Member();
        member.setName("hello");
        mmr.save(member);

        //when ==>


        //then


    }

    @Test
    void findOneMember() {
    }

    @Test
    void findMembers() {
    }
}