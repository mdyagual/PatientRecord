package com.example.patientrecord.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfig {

   /* @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;*/

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public MapReactiveUserDetailsService userDetailsService(){
        UserDetails user1 = User.builder()
                .username("mdyagual")
                .password(this.passwordEncoder().encode("mdyagual"))
                .roles("ADMIN")
                .build();
        UserDetails user2 = User.builder()
                .username("sposada")
                .password(this.passwordEncoder().encode("sposada"))
                .roles("USER")
                .build();
        return new MapReactiveUserDetailsService(user1,user2);
    }

    @Bean
    public SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http){
            http.authorizeExchange()
                    .pathMatchers("/home/user").hasAnyRole("USER","ADMIN")
                    .pathMatchers("/home/admin").hasAnyRole("ADMIN")
                    .anyExchange()
                    .authenticated()
                    .and()
                    .formLogin();
            return http.build();
    }

}
