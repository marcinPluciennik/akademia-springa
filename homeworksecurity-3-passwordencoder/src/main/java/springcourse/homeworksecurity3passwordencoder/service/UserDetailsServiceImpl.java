package springcourse.homeworksecurity3passwordencoder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springcourse.homeworksecurity3passwordencoder.repo.App3UserRepo;

@Service
@Primary
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private App3UserRepo app3UserRepo;

    @Autowired
    public UserDetailsServiceImpl(App3UserRepo app3UserRepo) {
        this.app3UserRepo = app3UserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s){
        try{
            return app3UserRepo.findAllByUsername(s).get();
        }catch (UsernameNotFoundException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
