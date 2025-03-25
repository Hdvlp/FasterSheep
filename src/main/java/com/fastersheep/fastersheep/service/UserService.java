package com.fastersheep.fastersheep.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fastersheep.fastersheep.model.Users;
import com.fastersheep.fastersheep.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
    
    public String register(Users user){
        String s = encoder.encode(user.getPassword());

        user.setPassword("{bcrypt}"+s);

        int number = 0;
        try
        {
            Users newUser =  repo.save(user) ;
            number = newUser.getId();
        }
        catch (NumberFormatException e)
        {
            number = 0;
        }
        if (number == 0){
            return new String ("Error...");
        }
        return new String ("OK. <a href=\"/login\">Please log in here</a>.");
    }
}
