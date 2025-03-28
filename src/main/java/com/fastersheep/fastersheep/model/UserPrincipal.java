package com.fastersheep.fastersheep.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private Users user;
    private List<String> rolesArr = new ArrayList<>();

    public UserPrincipal(Users user) {
        this.user = user;
    }

    public UserPrincipal(Users user, List<String> rolesArr) {
        this.user = user;
        this.rolesArr = rolesArr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String roleName : this.rolesArr){
            authorities.add(new SimpleGrantedAuthority(roleName));
        }
        Collection<GrantedAuthority> allowedOperations = 
                authorities;
        return allowedOperations;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

}
