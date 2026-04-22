package com.example.Ex.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.Ex.DAO.Collectivities;
import com.example.Ex.Exception.BadRequestException;

public class CollectivityController {

    private ResponseEntity<?> createCollectivityEntity(Collectivities collectivity){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(null);

            
        } catch (BadRequestException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    
}
