package net.courseproject.alex.veterinary.exception.controller;

import net.courseproject.alex.veterinary.exception.UserAlreadyExistsException;
import net.courseproject.alex.veterinary.exception.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class ServerErrorController extends ResponseEntityExceptionHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    protected ResponseEntity<Object> handleConflict(RuntimeException ex) {
        ErrorResponse response = new ErrorResponse()
                .setStatus(520)
                .setDateTime(LocalDateTime.now().format(FORMATTER))
                .setMessage(ex.getMessage())
                .setError("Internal Server Error");
        return ResponseEntity.status(520).body(response);
    }

    @ExceptionHandler(value = AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException exception) {
        ErrorResponse response = new ErrorResponse()
                .setStatus(403)
                .setDateTime(LocalDateTime.now().format(FORMATTER))
                .setMessage(exception.getMessage());
        return ResponseEntity.status(403).body(response);
    }
}
