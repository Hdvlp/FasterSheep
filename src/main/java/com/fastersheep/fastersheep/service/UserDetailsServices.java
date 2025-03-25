package com.fastersheep.fastersheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastersheep.fastersheep.model.UserPrincipal;
import com.fastersheep.fastersheep.model.Users;
import com.fastersheep.fastersheep.repo.UserRepo;

@Service
public class UserDetailsServices implements UserDetailsService{

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = repo.findByUsername(username);

        if (user == null){
            System.out.println(username + " Not Found");
            throw new UsernameNotFoundException(username + " Not Found");
        }
        return new UserPrincipal(user);
    }

}
