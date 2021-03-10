package springcourse.kursspringboot2security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import springcourse.kursspringboot2security.entity.AppUser;
import springcourse.kursspringboot2security.repo.AppUserRepo;

@Component
public class Start {
    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;

        AppUser appUser = new AppUser();
        appUser.setUsername("Marcin");
        appUser.setEnabled(true);
        appUser.setPassword(passwordEncoder.encode("Marcin123"));
        appUserRepo.save(appUser);
    }
}
