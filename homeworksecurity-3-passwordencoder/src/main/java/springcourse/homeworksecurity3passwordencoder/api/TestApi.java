package springcourse.homeworksecurity3passwordencoder.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class TestApi {

    @GetMapping("/forUser")
    public String forUser(Principal principal){
        return "Hello User: " + principal.getName() + " - NICE PASSWORD ENCODER :-P";
    }
}
