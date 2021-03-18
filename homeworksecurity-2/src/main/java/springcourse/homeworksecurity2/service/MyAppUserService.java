package springcourse.homeworksecurity2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import springcourse.homeworksecurity2.entity.MyAppUser;
import springcourse.homeworksecurity2.entity.VerificationToken;
import springcourse.homeworksecurity2.entity.VerificationTokenAdmin;
import springcourse.homeworksecurity2.repo.MyAppUserRepo;
import springcourse.homeworksecurity2.repo.VerificationTokenAdminRepo;
import springcourse.homeworksecurity2.repo.VerificationTokenRepo;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class MyAppUserService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MyAppUserService.class);

    @Value("${email.admin}")
    private String email_admin;

    private MyAppUserRepo myAppUserRepo;
    private PasswordEncoder passwordEncoder;
    private VerificationTokenRepo verificationTokenRepo;
    private MailSenderService mailSenderService;
    private VerificationTokenAdminRepo verificationTokenAdminRepo;

    @Autowired
    public MyAppUserService(MyAppUserRepo myAppUserRepo, PasswordEncoder passwordEncoder,
                            VerificationTokenRepo verificationTokenRepo,
                            MailSenderService mailSenderService,
                            VerificationTokenAdminRepo verificationTokenAdminRepo) {
        this.myAppUserRepo = myAppUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenRepo = verificationTokenRepo;
        this.mailSenderService = mailSenderService;
        this.verificationTokenAdminRepo = verificationTokenAdminRepo;
    }

    public void addNewUser(MyAppUser myAppUser, HttpServletRequest request) {
        myAppUser.setPassword(passwordEncoder.encode(myAppUser.getPassword()));

        if (myAppUserRepo.findMyAppUserByUsername(myAppUser.getUsername()).isPresent()){
            LOGGER.error("ACCOUNT EXISTS ALREADY!");
        }
        myAppUserRepo.save(myAppUser);
        LOGGER.info("SUCCESS, ACCOUNT HAS BEEN CREATED");

        String token = UUID.randomUUID().toString();

        VerificationToken verificationToken = new VerificationToken(myAppUser, token);
        verificationTokenRepo.save(verificationToken);
        String confirmationUrlUser = "http://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + "/verify-token?token=" + token;

        VerificationTokenAdmin verificationTokenAdmin = new VerificationTokenAdmin(myAppUser, token);
        verificationTokenAdminRepo.save(verificationTokenAdmin);
        String confirmationUrlUAdmin = "http://" + request.getServerName() + ":" + request.getServerPort() +
                request.getContextPath() + "/verify-token-admin?token=" + token;

        try{
            LOGGER.info("CHOSEN ROLES: " + myAppUser.getRoles().toString());
            if (myAppUser.getRoles().contains("ROLE_ADMIN")){
                mailSenderService.sendMail(email_admin,
                        "Verification Token Admin",
                        confirmationUrlUAdmin,
                        false);
                myAppUserRepo.findMyAppUserByUsername(myAppUser.getUsername()).get().getRoles().remove("ROLE_ADMIN");
                myAppUserRepo.save(myAppUser);
            }
            mailSenderService.sendMail(myAppUser.getUsername(),
                    "Verification Token",
                    confirmationUrlUser,
                    false);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }

    public void verifyTokenUser(String token) {
        MyAppUser myAppUser = verificationTokenRepo.findByValue(token).getMyAppUser();
        myAppUser.setEnabled(true);
        myAppUserRepo.save(myAppUser);
        LOGGER.info("ROLE USER HAS BEEN ACTIVATED");
    }

    public void verifyTokenAdmin(String token){
        MyAppUser myAppUser = verificationTokenAdminRepo.findByValue(token).getMyAppUser();
        myAppUser.getRoles().add("ROLE_ADMIN");
        myAppUser.setEnabled(true);
        myAppUserRepo.save(myAppUser);
        LOGGER.info("ROLE ADMIN HAS BEEN ACTIVATED");
    }
}
