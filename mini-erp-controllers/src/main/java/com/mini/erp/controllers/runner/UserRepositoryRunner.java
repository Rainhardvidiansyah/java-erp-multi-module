package com.mini.erp.controllers.runner;

import com.mini.erp.user.pojo.UserInfoAuthentication;
import com.mini.erp.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryRunner implements CommandLineRunner {

    private final UserRepository userRepository;


    @Override
    public void run(String... args) throws Exception {

        var user = userRepository.findUserByEmail("maulida@email.com");
        System.out.println(user);

    }
}
