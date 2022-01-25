package com.example.example.dataSeed;


import com.example.example.model.User;
import com.example.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static com.example.example.model.ERole.ROLE_ADMIN;
import static com.example.example.model.ERole.ROLE_USER;

@Component
public class UserDataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData(){
        if (userRepository.count() == 0) {
            User user1 = new User("admin","admin@gmail.com","admin",ROLE_ADMIN);
            User user2 = new User("user","user@gmail.com","user",ROLE_USER);
            userRepository.save(user1);
            userRepository.save(user2);
        }
    }

}
