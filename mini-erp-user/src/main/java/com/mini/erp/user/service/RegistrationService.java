package com.mini.erp.user.service;

import com.mini.erp.user.exception.EmailAndPasswordException;
import com.mini.erp.user.exception.UserAlreadyExistException;
import com.mini.erp.user.pojo.RegistrationRequest;
import com.mini.erp.user.pojo.UserInfoAuthentication;
import com.mini.erp.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    private static final Logger log = LoggerFactory.getLogger(RegistrationService.class);

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public RegistrationRequest register(RegistrationRequest request) throws EmailAndPasswordException {

        UserInfoAuthentication user = userRepository.findUserByEmail(request.getEmail());

        if(user != null){
            log.info("Is user already in the repository? {}", user.getEmail());
            throw new UserAlreadyExistException();
        }

        log.info("Registering new user in Registration Service Class: {}", request);
        userRepository.registrationNewUser(request);
        return request;
    }
}
