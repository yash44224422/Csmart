package org.yashrajguru.csmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.yashrajguru.csmart.model.Users;
import org.yashrajguru.csmart.repo.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authManager; //auth user login is info

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);//12 round 2 to powoer 12


    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String verify(Users user) {
        Authentication authentication =
                authManager.authenticate( new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));


        if( authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "fail";
    }
}