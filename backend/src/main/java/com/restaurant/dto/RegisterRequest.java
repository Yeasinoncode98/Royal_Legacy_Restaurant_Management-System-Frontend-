package com.restaurant.dto;
import jakarta.validation.constraints.*;
public class RegisterRequest {
    @NotBlank private String name;
    @Email @NotBlank private String email;
    @NotBlank @Size(min=6) private String password;
    @NotBlank private String phone;
    public RegisterRequest(){}
    public String getName(){return name;} public void setName(String n){name=n;}
    public String getEmail(){return email;} public void setEmail(String e){email=e;}
    public String getPassword(){return password;} public void setPassword(String p){password=p;}
    public String getPhone(){return phone;} public void setPhone(String p){phone=p;}
}
