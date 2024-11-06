package com.mini.erp.user.service;

import com.mini.erp.user.exception.UserAlreadyExistException;
import com.mini.erp.user.pojo.RegistrationRequest;
import com.mini.erp.user.pojo.UserInfoAuthentication;
import com.mini.erp.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class RegistrationServiceTest {


    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private RegistrationService registrationService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void register() {

        RegistrationRequest request = new RegistrationRequest("email", "password");

        Mockito.when(userRepository.registrationNewUser(request)).thenReturn(request);

        var regService = registrationService.register(request);

        Assertions.assertNotNull(regService);
        Assertions.assertEquals("email", regService.getEmail());

        Mockito.verify(userRepository, Mockito.times(1)).registrationNewUser(request);
    }


    @Test
    void whenUserExist_thenThrowException(){

        var registration = new RegistrationRequest("Email", "password");

        Mockito.when(userRepository.findUserByEmail("Email")).thenReturn(new UserInfoAuthentication());

        UserAlreadyExistException ex = Assertions.assertThrows(UserAlreadyExistException.class, () -> registrationService.register(registration));
    }
}