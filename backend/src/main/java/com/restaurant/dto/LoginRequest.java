package com.restaurant.dto;
import jakarta.validation.constraints.*;
public class LoginRequest {
    @Email @NotBlank private String email;
    @NotBlank private String password;
    public LoginRequest(){}
    public String getEmail(){return email;} public void setEmail(String e){email=e;}
    public String getPassword(){return password;} public void setPassword(String p){password=p;}
}
