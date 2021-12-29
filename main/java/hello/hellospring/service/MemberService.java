package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // cntr+shift+t ===> Test 페이지 제작

    private final MemberRepository memberrepo = new MemoryMemberRepository(); //MemberRepository는 인터페이스이기때문에 구현체는 반드시 구현 클래스 인스턴스여야함.

    // 회원가입
    public Long join(Member member){
        //같은 이름의 중복 회원 X
        validateDuplicateName(member); // (cntl+alt+shift+t)
        //
        memberrepo.save(member);
        return member.getId();



    }
    // 같은 이름의 중복 회원 판별 로직
    private void validateDuplicateName(Member member){
        memberrepo.findByName(member.getName()) //Optional<member> 반환이기 때문에 바로 ifPresent메소드 연결 가능
                .ifPresent( m -> {
                    throw new IllegarStateException("already exists Name . .");
                });
    }

    // 회원 검색
    public Optional<Member> findOneMember(Long memberId){ //why optional??
        return memberrepo.findById(memberId);
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberrepo.findAll();
    }

}
