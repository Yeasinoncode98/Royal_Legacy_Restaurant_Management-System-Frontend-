package com.restaurant.service;
import com.restaurant.dto.*;
import com.restaurant.exception.*;
import com.restaurant.model.User;
import com.restaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){ this.userRepository=userRepository; }

    public AuthResponse register(RegisterRequest req){
        if(userRepository.existsByEmail(req.getEmail())) throw new DuplicateEmailException(req.getEmail());
        User user=new User(req.getName(),req.getEmail(),req.getPassword(),req.getPhone());
        return buildResponse(userRepository.save(user));
    }

    // METHOD OVERLOADING
    public AuthResponse login(LoginRequest req){ return login(req.getEmail(),req.getPassword()); }
    public AuthResponse login(String email,String password){
        User user=userRepository.findByEmail(email)
            .orElseThrow(()->new IllegalArgumentException("Invalid email or password"));
        if(!user.getPassword().equals(password)) throw new IllegalArgumentException("Invalid email or password");
        return buildResponse(user);
    }

    public User findById(Long id){
        return userRepository.findById(id)
            .orElseThrow(()->new ResourceNotFoundException("User","id",id));
    }

    private AuthResponse buildResponse(User user){
        String token="user_"+user.getId()+"_"+System.currentTimeMillis();
        return new AuthResponse(user.getId(),user.getName(),user.getEmail(),user.getRole().name(),token);
    }
}
