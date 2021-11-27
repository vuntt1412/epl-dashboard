package com.vuntt1412.epldashboard.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * Adding spring-boot-starter-security dependency enables authentication for the application, which includes a login form, and a user and a default password.
 * <p>
 * The @EnableWebSecurity is crucial if we disable the default security configuration.
 * <p>
 * Then we customize the AuthenticationManager by setting up AuthenticationManagerBuilder.
 * <p>
 * https://spring.io/guides/topicals/spring-security-architecture
 */
@EnableWebSecurity
public class ApplicationConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // AuthenticationManagerBuilder used to create an AuthenticationManager
        auth.inMemoryAuthentication()
                .withUser("admin")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("admin"))
                .roles("ADMIN")
                .and()
                .withUser("user1")
                .password(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("user1"))
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // HttpSecurity lets us configure paths and restrictions for those paths
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/team/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }
}
