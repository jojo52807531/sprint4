package com.youssef.security;

import com.youssef.entities.Role;
import com.youssef.entities.User;
import com.youssef.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepo;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = (User) userRepo.findByUsername(username);


       for( Role r: user.getRoles() ){
           System.out.println("role = "+r.getName() );
       }


        if (user != null) {
            org.springframework.security.core.userdetails.User authUser = new org.springframework.security.core.userdetails.User(
                    user.getUsername(),
                    user.getPassword(),

                   user.getRoles().stream().map((role) ->

                                    new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList()

                            )


            );
            System.out.println("auths = "+authUser.getAuthorities());


            return authUser;
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
