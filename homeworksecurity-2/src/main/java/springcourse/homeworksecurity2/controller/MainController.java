package springcourse.homeworksecurity2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springcourse.homeworksecurity2.entity.MyAppUser;
import springcourse.homeworksecurity2.service.MyAppUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private MyAppUserService myAppUserService;

    @Autowired
    public MainController(MyAppUserService myAppUserService) {
        this.myAppUserService = myAppUserService;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/singup")
    public ModelAndView singup(){
        return new ModelAndView("registration", "user", new MyAppUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(MyAppUser user, HttpServletRequest request){
        myAppUserService.addNewUser(user, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView verify(@RequestParam String token){
        myAppUserService.verifyToken(token);
        return new ModelAndView("redirect:/login");
    }
}
