package com.mini.erp.controllers.security;

import com.mini.erp.user.pojo.AuthorityInfo;
import com.mini.erp.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var userInfo = this.userRepository.findUserByEmail(email);
        log.info("user info: " + userInfo);

        String userEmail = userInfo.getEmail();
        String userPassword = userInfo.getPassword();
        boolean enabled = userInfo.isEnabled();

        return new UserDetailsImpl(userEmail, userPassword, enabled, authorities(userEmail));
    }

    private List<GrantedAuthority> authorities(String email){
        List<AuthorityInfo> authorities = this.userRepository.getAuthoritiesByEmail(email);

        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthority()))
                .collect(Collectors.toList());

    }
}
