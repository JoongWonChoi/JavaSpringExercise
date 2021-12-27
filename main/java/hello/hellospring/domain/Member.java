package hello.hellospring.domain;

public class Member {

    private Long id;
    private String name;

    public Long getId(){
        return id;
    }
    public Long setId(Long id){
        this.id = id;
        return id;
    }
    public String getName(){
        return name;
    }
    public String setname(String name){
        this.name = name;
        return name;
    }

}
