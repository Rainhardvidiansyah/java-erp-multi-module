package com.mini.erp.controllers.dto;

import jakarta.validation.constraints.NotEmpty;

public class LoginRequestDto {


    @NotEmpty(message = "Please enter an email address")
    private String email;

    @NotEmpty(message = "Please enter a password")
    private String password;

    public LoginRequestDto(){}

    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
