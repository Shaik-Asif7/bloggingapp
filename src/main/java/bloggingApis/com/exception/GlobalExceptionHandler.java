package bloggingApis.com.exception;

import bloggingApis.com.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserCustomException.class)
    public ResponseEntity<?> userCustomExcetionHandler(UserCustomException userCustomException){
        return new ResponseEntity<>(userCustomException.getMessage(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CategoryCustomException.class)
    public ResponseEntity<?> categoryCustomExcetionHandler(CategoryCustomException ce){
        return new ResponseEntity<>(ce.getMessage(), HttpStatus.NOT_FOUND);
    }
}
