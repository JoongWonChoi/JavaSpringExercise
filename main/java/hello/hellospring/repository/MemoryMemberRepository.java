package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryMemberRepository implements MemberRepository {

    public static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; //key값 생성
    @Override
    public Member save(Member member) { //Member 클래스의 객체가 (임의의 'member')가 파라미터로 전달. Member 자료형을 반환하는 클래스
        member.setId(++sequence); //넘어온 member객체의 아이디를 설정(++)
        store.put(member.getId(), member); //store 해쉬맵에 member아이디와 member객체 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id로 찾는 Name은 String이므로 반환형으로 String 사용하면 안되나?
        // 내 생각 ===> Member클래스의 Id와 Name은 private이기에 Getter-setter 메서드를 통하여 접근해야함.
        //        ===> 현재 메서드에서는 Name 자체를 반환하는 것이 아니라 getId라는 Member클래스(자료형)의 '메서드'를 반환하는 것
        //        ===> 따라서 반환형이 String이 아닌 Member여야 하고, 그래야 Member자료형의 메서드가 반환 가능해지는 것.??
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name)) //member의 getName이 파라미터로 넘어온 name과 같으면 반환
                .findAny(); //하나라도 찾는다는 의미. 루프를 다 돌고 하나라도 찾아지면 반환, 끝까지 갔는데 없으면 Optional 작동
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //store객체에 저장된 value값들을 ArrayList컬렉션에 담아서 return
    }

    public void clearStore(){
        store.clear();
    }



}

