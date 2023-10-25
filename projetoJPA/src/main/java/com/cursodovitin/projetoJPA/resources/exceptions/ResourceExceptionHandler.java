package com.cursodovitin.projetoJPA.resources.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cursodovitin.projetoJPA.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

/* o ControllerAdvice vai intercepitar as exceções que acontecerem */
@ControllerAdvice
public class ResourceExceptionHandler {

    /* essa anotacao fala que o metodo resourceNotFound vai intercepitar 
     * qualquer exceção do tipo ResourceNotFoundException e vai fazer o tratamento q estiver no metodo
    */ 
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> databese(DatabaseException e, HttpServletRequest request){
        String error = "Datebase error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
