package springcourse.homeworksecurity2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import springcourse.homeworksecurity2.entity.MyAppUser;
import springcourse.homeworksecurity2.repo.VerificationTokenRepo;
import springcourse.homeworksecurity2.service.MyAppUserService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private MyAppUserService myAppUserService;
    private VerificationTokenRepo verificationTokenRepo;

    @Autowired
    public MainController(MyAppUserService myAppUserService, VerificationTokenRepo verificationTokenRepo) {
        this.myAppUserService = myAppUserService;
        this.verificationTokenRepo = verificationTokenRepo;
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(){
        return "success";
    }

    @RequestMapping("/singup")
    public ModelAndView singup(){
        return new ModelAndView("registration", "user", new MyAppUser());
    }

    @RequestMapping("/register")
    public ModelAndView register(@Validated MyAppUser myAppUser, BindingResult bindingResult, HttpServletRequest request){
        if (bindingResult.hasErrors()){
            return new ModelAndView("redirect:/singup");
        }
        myAppUserService.addNewUser(myAppUser, request);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token")
    public ModelAndView verify(@RequestParam String token){
        myAppUserService.verifyTokenUser(token);
        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/verify-token-admin")
    public ModelAndView verifyAdmin(@RequestParam String token){
        myAppUserService.verifyTokenAdmin(token);
        return new ModelAndView("redirect:/login");
    }
}
