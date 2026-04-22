package com.example.Ex.Controller;

import java.lang.reflect.Member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MembersController {

    public ResponseEntity<?> memberRegistration(Member member){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);

            
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
