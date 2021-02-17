package springcourse.homeworksecurity1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    public static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationSuccessListener.class);
    private Map<String, Integer> logins = new HashMap<>();

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String loginName = event.getAuthentication().getName();
        if (logins.containsKey(loginName)) {
            logins.put(loginName, logins.get(loginName) + 1);
        } else {
            logins.put(loginName, 1);
        }
        LOGGER.info(loginName + " has just logged in " + logins.get(loginName) +
                (logins.get(loginName) == 1 ? " time" : " times"));
    }

    public Map<String, Integer> getLogins() {
        return logins;
    }
}
