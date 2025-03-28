package com.fastersheep.fastersheep.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserPrincipal implements UserDetails {

    private Users user;
    private Set<String> rolesArr = new HashSet<>();

    public UserPrincipal(Users user) {
        this.user = user;
    }

    public UserPrincipal(Users user, Set<String> rolesArr) {
        this.user = user;
        this.rolesArr = rolesArr;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {  
        Set<GrantedAuthority> authorities = new HashSet<>();

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
