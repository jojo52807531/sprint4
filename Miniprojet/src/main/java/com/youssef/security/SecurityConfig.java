package com.youssef.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   @Autowired
    private CustomUserDetailsService userDetailsService;




    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {


        httpSecurity.csrf().disable()
                            .authorizeHttpRequests()
                .requestMatchers("/webjars/**", "/login", "/register", "/saveuser").permitAll().
           //    requestMatchers("/Addsinger","/Addtype","/showUpdate","/deletesinger").hasAnyAuthority("ADMIN").
                anyRequest().authenticated().and()

                                .formLogin(form -> form
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/Singers", true)
                                        .failureUrl("/login?error=true")


                                        .permitAll()
                                ).logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
                        .invalidateHttpSession(true)
                        .logoutSuccessUrl("/login")
                ).exceptionHandling().accessDeniedPage("/access");
        return httpSecurity.build();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }
}
