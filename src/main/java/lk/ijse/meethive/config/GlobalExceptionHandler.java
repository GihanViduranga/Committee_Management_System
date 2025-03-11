package lk.ijse.meethive.config;

import lk.ijse.meethive.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.MethodNotAllowedException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodNotAllowedException.class)
    public ResponseEntity<ResponseDTO> handleException(MethodArgumentNotValidException exception){
        Map<String, String> error = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors())error.put(fieldError.getField(),fieldError.getDefaultMessage());

        ResponseDTO responseDTO = new ResponseDTO(
                401,
                "Validation Error",
                error
        );
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);

    }
}
