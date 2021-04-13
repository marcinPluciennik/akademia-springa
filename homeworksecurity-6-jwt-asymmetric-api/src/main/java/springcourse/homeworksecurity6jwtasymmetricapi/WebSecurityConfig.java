package springcourse.homeworksecurity6jwtasymmetricapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/movies").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/movie").hasRole("ADMIN")
                .and()
                .addFilterBefore(new JwtFilter(secret), UsernamePasswordAuthenticationFilter.class);
        http.csrf().disable(); // to use it add http.csrf().disable(); in WebSecurityConfig
    }
}
