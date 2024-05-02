package co.com.flypass.apirest.error;

import co.com.flypass.constants.Constants;
import co.com.flypass.error.ErrorResponse;
import co.com.flypass.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestControllerAdvice
public class ControllerAdvisor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAdvisor.class);
    private static final ConcurrentHashMap<String, HttpStatus> STATUS_MAP = new ConcurrentHashMap<>();

    public ControllerAdvisor() {
        STATUS_MAP.put(BadRequestException.class.getSimpleName(), HttpStatus.BAD_REQUEST);
        STATUS_MAP.put(ValidationException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
        STATUS_MAP.put(NotFoundException.class.getSimpleName(), HttpStatus.NOT_FOUND);
        STATUS_MAP.put(AlreadyExistException.class.getSimpleName(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BaseException baseException) {
        HttpStatus status = STATUS_MAP.get(baseException.getClass().getSimpleName());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exceptionName(baseException.getClass().getSimpleName())
                .userMessage(baseException.getUserMessage())
                .technicalMessage(baseException.getTechnicalMessage())
                .build();
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        LOGGER.error(exception.getMessage(), exception);
        ErrorResponse errorResponse = ErrorResponse.builder()
                .exceptionName(exception.getClass().getSimpleName())
                .userMessage(Constants.DEFAULT_MESSAGE_ERROR)
                .technicalMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
