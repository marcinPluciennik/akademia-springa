package springcourse.homeworksecurity2.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import springcourse.homeworksecurity2.repo.MyAppUserRepo;


@Primary
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private MyAppUserRepo myAppUserRepo;

    @Autowired
    public UserDetailsServiceImpl(MyAppUserRepo myAppUserRepo) {
        this.myAppUserRepo = myAppUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s){
        try{
            return myAppUserRepo.findMyAppUserByUsername(s).get();
        }catch (UsernameNotFoundException e){
            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
