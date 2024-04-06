package eu.fondy.task.controller;


import eu.fondy.task.exception.InsufficientChangeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class RestApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        Map<String, List<String>> errorsMap = getErrorsMap(errors);
        log.error("Validation has failed. Details: {}", errorsMap);
        return  ResponseEntity.badRequest().body(errorsMap);
    }


    @ExceptionHandler(InsufficientChangeException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(InsufficientChangeException ex) {
        log.error("Change calculation has failed. Details: {}", ex.getMessage());
        return  ResponseEntity.badRequest().body(getErrorsMap(List.of(ex.getMessage())));
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }


}
