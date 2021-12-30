package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    MemoryMemberRepository mmr = new MemoryMemberRepository();
    MemberService memberService = new MemberService();


    //shift+f10 ==> 이전 run restart
    @AfterEach
    public void clearRepo(){
        mmr.clearStore();
    }

    @Test
    void 회원등록() {
        //given (테스트를 진행할 검증 데이터) ==> member의 이름 등록
        Member member = new Member();
        member.setName("hello");
        //when (특정 Event처리 테스트) ==> 회원가입
        Long saveId = memberService.join(member); //회원가입 후 반환되는 id를 saveId 변수에 저장
        //then (테스트 결과)
        Member findName = memberService.findOneMember(saveId).get();// ==> saveId id를 통해 회원을 찾아 이름에 접근하고 Optional의 get() 메서드로 값 반환.
                                                                    //findOneMember는 MemoryMemberRepository의 findById 메서드를 호출 . . 이 메서드는 Optional type으로 wrapping된 Member자료형을 반환하기 때문에 findName 변수의 자료형은 Member.

        assertThat(findName.getName()).isEqualTo(member.getName());


    }

    @Test
    void 회원_중복_테스트(){ //설정한 validateDuplicateName 메서드가 정상적으로 작동되는지 판별하는 테스트 로직
        //given ==> 회원 등록
        Member member1 = new Member();
        member1.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");
        //when
        memberService.join(member1);

        //  ===== try-catch절로 오류 검증 테스트 =====
        /*try{
            memberService.join(member2);
            fail("Error must be existed . .");
        }
        catch(IllegalStateException e){ //catch구문까지 오면 예외가 정상적으로 작동 된 것.
        }
        */ // ===> 단체 주석 : cntr + shift + /

        IllegalStateException e = assertThrows(IllegalStateException.class,  // cntr+alt+v -> 자동 자료형 + 변수명 생성
                () -> memberService.join(member2));//예외가 발생해야 함
//then
        assertThat(e.getMessage()).isEqualTo("already exists Name . .");



    }

    @Test
    void findOneMember() {
    }

    @Test
    void findMembers() {
    }
}