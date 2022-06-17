package com.userservice.handler;

import com.userservice.model.ErrorMessageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class JarvisExceptionHandler {

    @ExceptionHandler()
    public ResponseEntity<Object> handleJarvisException(JarvisException e){

        ErrorMessageResponse error = new ErrorMessageResponse(e.getCode(), e.getLocalizedMessage(), new Date());

        HttpStatus httpStatus = HttpStatus.valueOf(e.getCode());
        return new ResponseEntity<>(error, httpStatus);
    }
}
