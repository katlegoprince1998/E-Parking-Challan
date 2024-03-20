package com.groupg.eparkingchallan.config.JSONWebToken;


import com.groupg.eparkingchallan.entity.User;
import com.groupg.eparkingchallan.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomeUserDetails implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if(user == null){
            throw new UsernameNotFoundException("User not found " + username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org
                .springframework
                .security
                .core
                .userdetails
                .User(user.getEmail(), user.getPassword(), authorities);
    }
}
