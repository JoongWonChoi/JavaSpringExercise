package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    //static
    @GetMapping("hello") //입력받을 url ====> ex) localhost:8080/hello
    public String test(Model a) { //model객체를 파라미터로 불러옴
        a.addAttribute("data", "hello!!"); //model에 key : value 추가
        return "hello"; //ViewResolver를 통해 검색할 html파일 이름 ====> ex)hello.html 탐색
    }

    //MVC 방식
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name", required = true) String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";

    }

    //API 방식
    @GetMapping("hello-string")
    @ResponseBody //HTTP body 부분에 직접 데이터를 넣어준다는 의미
    public String helloSpring(@RequestParam("name") String name) {
        return "hello" + name; // ===> hello 'name'

    }
    //동적 전달방식과의 차이 : view가 필요없이, (템플릿이 없어도 됨) 바로 데이터가 화면에 내려짐.

    //객체로 넘어온 data 전달시 json형태로 (자동변환하여) 전달하기
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloAPi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;//Hello의 객체인 hello를 반환
        //Spring 자체적으로 객체로 반환되는 데이터들은 JSON형식으로 반환(HttpMessageConverter)

    }
    //Getter-Setter Class
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
