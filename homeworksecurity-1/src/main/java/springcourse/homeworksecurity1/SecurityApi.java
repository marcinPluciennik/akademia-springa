package springcourse.homeworksecurity1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class SecurityApi {

    private AuthenticationSuccessListener authenticationSuccessListener;

    @Autowired
    public SecurityApi(AuthenticationSuccessListener authenticationSuccessListener) {
        this.authenticationSuccessListener = authenticationSuccessListener;
    }

    @GetMapping("/helloAdmin")
    public String getHelloAdmin(Principal principal){
        return "Hello Admin: " + principal.getName();
    }

    @GetMapping("/helloUser")
    public String getHelloUser(Principal principal){
        return "Hello User: " + principal.getName();
    }

    @GetMapping("/helloStranger")
    public String getHelloStranger(Principal principal){
        if (principal == null){
            return "Hello Stranger";
        }
        return "Hello " + principal.getName();
    }

    @GetMapping("/sayGoodBye")
    public String getSayGoodBye(){
        return "PaPa";
    }

    @GetMapping
    public String getLoginCounter(Principal principal){
        int result = authenticationSuccessListener.getLogins().get(principal.getName());
        return principal.getName() + ", you has just logged in: " + result +
                (result == 1 ? " time." : " times.");
    }
}
