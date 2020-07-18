package pl.kolak.hostingzdjec.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.kolak.hostingzdjec.model.UserApp;
import pl.kolak.hostingzdjec.repository.UserRepository;
import pl.kolak.hostingzdjec.service.UserDetailsServiceImpl;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserRepository userRepository;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, UserRepository userRepository) {
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/upload").hasRole("ADMIN")
                .antMatchers("/gallery").hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        UserApp appUserUser = new UserApp("user", passwordEncoder().encode("user123"), "ROLE_USER");
        UserApp appUserAdmin = new UserApp("admin", passwordEncoder().encode("admin123"), "ROLE_ADMIN");
        userRepository.save(appUserUser);
        userRepository.save(appUserAdmin);
    }
}
