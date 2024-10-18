package org.yashrajguru.csmart.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.yashrajguru.csmart.model.Users;

public interface UserRepo extends JpaRepository <Users,Integer> {

    Users findByUsername(String username);
}
