package org.yashrajguru.csmart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.yashrajguru.csmart.model.UserPrincipal;
import org.yashrajguru.csmart.model.Users;
import org.yashrajguru.csmart.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService {


    @Autowired
    private UserRepo repo ;
    @Override// need to implement userdetail
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = repo.findByUsername(username);
        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        return new UserPrincipal(user);
    }

}
