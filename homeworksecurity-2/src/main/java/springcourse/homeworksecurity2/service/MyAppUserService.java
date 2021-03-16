package springcourse.homeworksecurity2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springcourse.homeworksecurity2.entity.MyAppUser;
import springcourse.homeworksecurity2.entity.VerificationToken;
import springcourse.homeworksecurity2.repo.MyAppUserRepo;
import springcourse.homeworksecurity2.repo.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class MyAppUserService {

    private MyAppUserRepo myAppUserRepo;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;

    @Autowired
    public MyAppUserService(MyAppUserRepo myAppUserRepo, PasswordEncoder passwordEncoder, VerificationTokenRepo verificationTokenRepo, MailSenderService mailSenderService) {
        this.myAppUserRepo = myAppUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
    }

    public void addNewUser(MyAppUser user, HttpServletRequest request) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myAppUserRepo.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(user, token);
        verificationTokenRepo.save(verificationToken);

        String confirmationUrl = "http://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + "/verify-token?token=" + token;

        try{
            mailSenderService.sendMail(user.getUsername(),
                    "Verification Token",
                    confirmationUrl,
                    false);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void verifyToken(String token) {
        MyAppUser myAppUser = verificationTokenRepo.findByValue(token).getMyAppUser();
        myAppUser.setEnabled(true);
        myAppUserRepo.save(myAppUser);
    }
}
