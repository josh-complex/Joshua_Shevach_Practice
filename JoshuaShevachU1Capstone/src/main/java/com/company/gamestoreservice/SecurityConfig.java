package com.company.gamestoreservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder authBuilder) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        authBuilder.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username, password, enabled from users where username = ?")
                .authoritiesByUsernameQuery(
                        "select username, authority from authorities where username = ?")
                .passwordEncoder(encoder);
    }

    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        httpSecurity
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/allDone")
                .deleteCookies("JSESSIONID")
                .deleteCookies("XSRF-TOKEN")
                .invalidateHttpSession(true);

        httpSecurity.httpBasic();

        httpSecurity.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/invoices/*").hasAnyAuthority("MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.GET, "/invoices").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/consoles").hasAnyAuthority("MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/consoles").hasAnyAuthority( "STAFF", "MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/consoles/*").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/games").hasAnyAuthority("MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/games").hasAnyAuthority("STAFF", "MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/games/*").hasAuthority("ADMIN")
                .mvcMatchers(HttpMethod.POST,"/t-shirts").hasAnyAuthority("MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.PUT,"/t-shirts").hasAnyAuthority("STAFF", "MANAGER", "ADMIN")
                .mvcMatchers(HttpMethod.DELETE,"/t-shirts/*").hasAuthority("ADMIN")
                .anyRequest().permitAll();
    }
}
