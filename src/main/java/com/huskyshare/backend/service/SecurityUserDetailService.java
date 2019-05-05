package com.huskyshare.backend.service;

import com.huskyshare.backend.dao.UserDao;
import com.huskyshare.backend.entity.SecurityUserDetail;
import com.huskyshare.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class SecurityUserDetailService {
    @Autowired
    private UserDao userDao;

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDao.findUserByUsername(name);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return optionalUser.map(SecurityUserDetail::new).get();
    }
}
