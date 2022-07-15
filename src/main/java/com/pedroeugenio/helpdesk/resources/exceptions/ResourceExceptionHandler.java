package com.pedroeugenio.helpdesk.resources.exceptions;

import com.pedroeugenio.helpdesk.services.exceptions.DataIntegrityViolationException;
import com.pedroeugenio.helpdesk.services.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException ex,
                                                                 HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Object not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                 HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Violação de dados",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

}
