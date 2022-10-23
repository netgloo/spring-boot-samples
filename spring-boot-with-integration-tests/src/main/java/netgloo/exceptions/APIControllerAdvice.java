package netgloo.exceptions;

import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@ControllerAdvice(annotations = RestController.class)
@RequestMapping(produces = "application/api.error+json")
@Log
public class APIControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(APIIdNotFoundException.class)
    public ResponseEntity<APIError> notFoundException(final APIIdNotFoundException e) {
        log.info("InterviewKeyNotFoundException: " + e.getMessage() + " " +  e.getId());
        return error(e, HttpStatus.NOT_FOUND, e.getId().toString());
    }

    @ExceptionHandler(java.lang.IllegalArgumentException.class)
    public ResponseEntity<APIError> argumentNotValid(final APIIllegalArgumentException e) {
        log.info("IllegalArgumentException handler executed");
        return error(e, HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIError> internalServerError(final Exception e) {
        log.severe("Internal server error: " + e.getMessage() + " " + e.getStackTrace());
        return error(e, HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<APIError> error(final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message = Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new APIError(logRef, message, exception), httpStatus);
    }

}