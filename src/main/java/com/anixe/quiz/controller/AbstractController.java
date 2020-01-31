package com.anixe.quiz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractController {

    public ResponseEntity returnError(HttpStatus status, String message){
        return ResponseEntity.status(status).body(message);
    }

    public ResponseEntity returnSuccess(Object model){
        return ResponseEntity.ok().body(model);
    }
}
