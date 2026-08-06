package com.restaurant.dto;
public class AuthResponse {
    private Long userId; private String name; private String email;
    private String role; private String token; private String message;
    public AuthResponse(){}
    public AuthResponse(Long userId,String name,String email,String role,String token){
        this.userId=userId;this.name=name;this.email=email;this.role=role;this.token=token;this.message="Success";
    }
    public Long getUserId(){return userId;} public void setUserId(Long u){userId=u;}
    public String getName(){return name;} public void setName(String n){name=n;}
    public String getEmail(){return email;} public void setEmail(String e){email=e;}
    public String getRole(){return role;} public void setRole(String r){role=r;}
    public String getToken(){return token;} public void setToken(String t){token=t;}
    public String getMessage(){return message;} public void setMessage(String m){message=m;}
}
