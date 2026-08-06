package com.restaurant.exception;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleNotFound(ResourceNotFoundException ex){return err(HttpStatus.NOT_FOUND,ex.getMessage());}
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Map<String,Object>> handleDup(DuplicateEmailException ex){return err(HttpStatus.CONFLICT,ex.getMessage());}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleValidation(MethodArgumentNotValidException ex){
        String msg=ex.getBindingResult().getFieldErrors().stream().map(e->e.getField()+": "+e.getDefaultMessage()).findFirst().orElse("Validation failed");
        return err(HttpStatus.BAD_REQUEST,msg);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleIllegal(IllegalArgumentException ex){return err(HttpStatus.BAD_REQUEST,ex.getMessage());}
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,Object>> handleAll(Exception ex){return err(HttpStatus.INTERNAL_SERVER_ERROR,"Unexpected error");}
    private ResponseEntity<Map<String,Object>> err(HttpStatus status,String message){
        Map<String,Object> body=new HashMap<>();
        body.put("timestamp",LocalDateTime.now().toString());
        body.put("status",status.value()); body.put("message",message);
        return new ResponseEntity<>(body,status);
    }
}
