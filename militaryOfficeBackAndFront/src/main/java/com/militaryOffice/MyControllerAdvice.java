package com.militaryOffice;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler {
    @ResponseBody
    @ExceptionHandler(MainException.class)
    public ResponseEntity<?> handleControllerException(HttpServletRequest request, Throwable ex) {
        return new ResponseEntity<>("Что то пошло не так :( Попробуйте еще раз", HttpStatus.OK);
    }
}
