package com.apress;

import lombok.AllArgsConstructor;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
@Configuration
public class ContactSecurityConfig extends WebSecurityConfigurerAdapter {

    private ContactRepository contactRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService( email -> {

            Contact contact = this.contactRepository.findById(email).orElseThrow(() -> new RuntimeException("Contact not found!"));

            PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
            String password = encoder.encode(contact.getPassword());

            return User
                    .withUsername(email)
                    .password(password)
                    .accountLocked(!contact.isActive())
                    .roles(contact.getRoles().split(","))
                    .build();
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/actuator/health/**","/actuator/info","/").permitAll()
            .anyRequest().fullyAuthenticated()
            .and()
                .httpBasic()
            .and()
                .formLogin();

    }

}
