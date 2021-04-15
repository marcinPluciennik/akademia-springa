package springcourse.homeworksecurity6jwtasymmetricapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import springcourse.homeworksecurity6jwtasymmetricapi.jwt.JwtFilter;
import springcourse.homeworksecurity6jwtasymmetricapi.service.KeyService;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private KeyService keyService;

    @Autowired
    public WebSecurityConfig(KeyService keyService) {
        this.keyService = keyService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/movies").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/movie").hasRole("ADMIN")
                .and()
                .addFilterBefore(new JwtFilter(keyService), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable(); // to use it add http.csrf().disable(); in WebSecurityConfig
    }
}
