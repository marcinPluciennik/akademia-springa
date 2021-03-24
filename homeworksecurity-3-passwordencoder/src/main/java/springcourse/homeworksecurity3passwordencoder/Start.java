package springcourse.homeworksecurity3passwordencoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springcourse.homeworksecurity3passwordencoder.encoder.MarcinPasswordEncoder;
import springcourse.homeworksecurity3passwordencoder.entity.App3User;
import springcourse.homeworksecurity3passwordencoder.repo.App3UserRepo;

@Component
public class Start {

    private PasswordEncoder passwordEncoder;
    private App3UserRepo app3UserRepo;
    private MarcinPasswordEncoder marcinPasswordEncoder;

    @Autowired
    public Start(PasswordEncoder passwordEncoder, App3UserRepo app3UserRepo, MarcinPasswordEncoder marcinPasswordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.app3UserRepo = app3UserRepo;
        this.marcinPasswordEncoder = marcinPasswordEncoder;

        final Logger LOGGER = LoggerFactory.getLogger(Start.class);

        final String USERNAME = "Marcin";
        final String PASSWORD = "Marcin123";

        App3User app3User = new App3User();
        app3User.setUsername(USERNAME);
        app3User.setPassword(passwordEncoder.encode(PASSWORD));
        app3UserRepo.save(app3User);

        marcinPasswordEncoder.encode(PASSWORD);
        marcinPasswordEncoder.matches(PASSWORD,app3UserRepo.findAllByUsername(USERNAME).get().getPassword());
        LOGGER.info("FULL USER PASSWORD IN THE DATABASE: " + app3UserRepo.findAllByUsername(USERNAME).get().getPassword());
    }
}
