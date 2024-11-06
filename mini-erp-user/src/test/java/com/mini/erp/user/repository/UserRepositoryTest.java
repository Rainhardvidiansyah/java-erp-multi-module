package com.mini.erp.user.repository;

import com.mini.erp.user.exception.EmailAndPasswordException;
import com.mini.erp.user.pojo.RegistrationRequest;
import com.mini.erp.user.pojo.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


class UserRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private UserRepository userRepository;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrationNewUser() {

        String sql = "INSERT INTO users (email, password) VALUES (?,?)";
        Mockito.when(jdbcTemplate.update(Mockito.eq(sql), Mockito.anyString(), Mockito.anyString())).thenReturn(1);

        var repository = userRepository.registrationNewUser(new RegistrationRequest("email", "password"));

        Assertions.assertNotNull(repository);
        Assertions.assertSame("email", repository.getEmail());
        Assertions.assertSame("password", repository.getPassword());

        Mockito.verify(jdbcTemplate, Mockito.times(1)).update(Mockito.eq(sql), Mockito.anyString(), Mockito.anyString());

    }

    @Test
    void findUserByEmail() {

        String sql = "SELECT * FROM users WHERE email = ?";

        Mockito.when(jdbcTemplate.queryForObject(Mockito.eq(sql), Mockito.any(BeanPropertyRowMapper.class), Mockito.anyString()))
                .thenReturn(new User());

        var repo = userRepository.findUserByEmail("email");
        Assertions.assertNotNull(repo);

        Mockito.verify(jdbcTemplate, Mockito.times(1))
                .queryForObject(Mockito.eq(sql), Mockito.any(BeanPropertyRowMapper.class), Mockito.anyString());
    }


}