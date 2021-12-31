package hello.hellospring.controller.test;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Test {

    @GetMapping("/test")
    public String test(Model model){
        String test = "Test Model Instance Of Spring";
        model.addAttribute("Test", test);
        return "test";
    }
}
