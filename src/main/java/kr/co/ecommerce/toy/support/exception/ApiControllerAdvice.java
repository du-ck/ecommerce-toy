package kr.co.ecommerce.toy.support.exception;

import kr.co.ecommerce.toy.interfaces.api.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApiControllerAdvice {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        //조회결과가 없는 exception 의 경우 success = true 처리.
        return ResponseEntity.status(404).body(new ErrorResponse(true, "404", e.getMessage()));
    }

    @ExceptionHandler(value = OutOfStockException.class)
    public ResponseEntity<ErrorResponse> handleOutOfStockException(OutOfStockException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(409).body(new ErrorResponse(false, "409", e.getMessage()));
    }

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalStateException(IllegalStateException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(500).body(new ErrorResponse(false, "500", e.getMessage()));
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error(e.getMessage());
        return ResponseEntity.status(400).body(new ErrorResponse(false, "400", e.getMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(500).body(new ErrorResponse(false, "500", "에러가 발생했습니다."));
    }
}
