package com.bookstore.authservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.bookstore.authservice.dao.UserDAO;
import com.bookstore.authservice.repository.UserRepository;
import com.bookstore.authservice.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class AppUserDetailsServiceImpl implements AppUserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public AppUserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userNameOrEmail) throws UsernameNotFoundException {

        Optional<UserDAO> userOptional = userRepository
                .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

        UserDAO user = userOptional.orElseThrow(() ->
                new UsernameNotFoundException(String.format("The username or email Id %s doesn't exist",
                        userNameOrEmail))
        );

        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getUserName(), user.getPassword(), authorities);

        return userDetails;
    }
}