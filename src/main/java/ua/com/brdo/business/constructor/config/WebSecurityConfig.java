package ua.com.brdo.business.constructor.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import ua.com.brdo.business.constructor.service.impl.UserServiceImpl;
import ua.com.brdo.business.constructor.utils.restsecurity.RESTAuthenticationEntryPoint;
import ua.com.brdo.business.constructor.utils.restsecurity.RESTAuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private RESTAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private RESTAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private UserServiceImpl userServiceImpl;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("register/*").permitAll()
                .antMatchers(HttpMethod.GET, "/api/users/available**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/questions/**", "/api/options/**").hasAnyRole("USER", "EXPERT")
                .antMatchers("/api/questions/**", "/api/options/**").hasAnyRole("EXPERT")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "EXPERT");
                .antMatchers("/user/**").hasRole("USER")
                .antMatchers("/expert/**").hasRole("EXPERT")
                .antMatchers("/admin/**").hasRole("ADMIN");
        http.csrf().disable();
        http.exceptionHandling().authenticationEntryPoint(authenticationEntryPoint);
        http.formLogin().successHandler(authenticationSuccessHandler);
        http.formLogin().failureHandler(new SimpleUrlAuthenticationFailureHandler());
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}