package br.com.banco.controllers.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class ControllerExceptionHandler {
  @ExceptionHandler(DateTimeParseException.class)
  public ResponseEntity<CustomError> resourceNotFound(DateTimeParseException e, HttpServletRequest request) {
    HttpStatus status = HttpStatus.NOT_FOUND;
    CustomError error = new CustomError(Instant.now(), status.value(), "Datas inválidas", request.getRequestURI());
    return ResponseEntity.status(status).body(error);
  }
}
