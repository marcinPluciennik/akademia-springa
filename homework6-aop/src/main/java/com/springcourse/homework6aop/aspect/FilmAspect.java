package com.springcourse.homework6aop.aspect;

import com.springcourse.homework6aop.service.MailService;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;

@Aspect
@Component
public class FilmAspect {

    private MailService mailService;

    @Autowired
    public FilmAspect(MailService mailService) {
        this.mailService = mailService;
    }

    @After("@annotation(AfterAddFilm)")
    public void senEmail() throws MessagingException {
        mailService.sendMail("springowy@gmail.com", "Your film has been added",
                "Success!", true);
    }
}
