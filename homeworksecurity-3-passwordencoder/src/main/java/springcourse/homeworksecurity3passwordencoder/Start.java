package springcourse.homeworksecurity3passwordencoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springcourse.homeworksecurity3passwordencoder.entity.App3User;
import springcourse.homeworksecurity3passwordencoder.repo.App3UserRepo;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;
    private App3UserRepo app3UserRepo;

    @Autowired
    public Start(PasswordEncoder passwordEncoder, App3UserRepo app3UserRepo) {
        this.passwordEncoder = passwordEncoder;
        this.app3UserRepo = app3UserRepo;

        App3User app3User = new App3User();
        app3User.setUsername("Marcin");
        app3User.setPassword(passwordEncoder.encode("Marcin123"));
        app3UserRepo.save(app3User);
    }
}
