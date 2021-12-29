package hello.hellospring.repository;
import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repo = new MemoryMemberRepository(); //회원 저장소의 객체 repo 선언

    // !!Test는 각 테스트 케이스(메서드)에 의존 관계가 없이 작성되어야함.
    //Test case는 작성 순서에 의존하지 않음!!
    //따라서 Test를 진행 한 후 레포지토리를 Clear를 시켜주어야함.
    //그렇지 않으면 각 테스트케이스에서 진행된 과정이 다른 테스트케이스 진행에 영향을 미칠 수 있음.
    @AfterEach //Test 하나 진행 후 Clear
    public void afterEach(){
        repo.clearStore();
    }

    @Test
    public void save(){ //저장 test
        Member member = new Member(); //Member클래스에 접근하기위한 member객체 선언
        member.setName("Spring"); //member레퍼런스를 통해 Spring이라는 이름 저장

        repo.save(member);// ===> save메서드 내부에서 ++sequence

        Member result = repo.findById(member.getId()).orElse(null); //get()메소드를 사용하여 Optional 객체에 저장된 값에 접근 가능.
        assertEquals(member, result);
        //assertThat(member).isEqualTo(result);
    }
    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("jwc1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("jwc2");
        repo.save(member2);

        Member result = repo.findByName("jwc1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("jwc1");
        repo.save(member1);

        Member member2 = new Member();
        member2.setName("jwc2");
        repo.save(member2);

        List<Member> result = repo.findAll(); //findAll메서드를 통해서 반환된 저장된 회원 정보를 Member클래스를 자료형으로 하는 리스트에 담음
        assertThat(result.size()).isEqualTo(2);
    }
}
