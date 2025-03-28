package com.fastersheep.fastersheep.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fastersheep.fastersheep.model.RoleEnum;
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
            throw new UsernameNotFoundException(username + " Not Found");
        }

        BigInteger roles = repo.findRolesByUsername(username);

        List<String> rolesArr = new ArrayList<>();

        for (RoleEnum roleEnum : RoleEnum.values()) {
            if (roles.mod(roleEnum.getValue()) == 
                BigInteger.valueOf(0)) {
                rolesArr.add("ROLE_" + roleEnum.name());
            }
        }

        return new UserPrincipal(user, rolesArr);

    }

}
