package au.com.developer.api.common.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

//import org.springframework.dao.DataAccessException; // Enable if using "spring-boot-starter-data-jpa" in pom.xml

/**
 * http://www.baeldung.com/global-error-handler-in-a-spring-rest-api
 *
 * If you don't include annotations then this will apply to every controller, which means you will
 * get JSON error pages for HTML based controllers.
 */
@ControllerAdvice(annotations = RestController.class)
@Slf4j
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    // HTTP 400
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String errorDetail = fieldError.getField() + " " + fieldError.getDefaultMessage();
            CustomError error = new CustomError(UUID.randomUUID().toString(), "INVALID_ARGUMENT", errorDetail, fieldError.getField());
            errors.add(error);
        }
        for (ObjectError objectError : ex.getBindingResult().getGlobalErrors()) {
            String errorDetail = objectError.getObjectName() + " " + objectError.getDefaultMessage();
            CustomError error = new CustomError(UUID.randomUUID().toString(), "INVALID_ARGUMENT", errorDetail, objectError.getObjectName());
            errors.add(error);
        }
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // HTTP 400
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /*// Enable if using "spring-boot-starter-data-jpa" in pom.xml
    // HTTP 400
    @ExceptionHandler({DataAccessException.class})
    public ResponseEntity<Object> handleDataAccess(DataAccessException ex, WebRequest request) {
        String id = UUID.randomUUID().toString();
        LOGGER.error(id + " " + ex.toString(), ex);
        CustomErrors errors = new CustomErrors(
                id,
                HttpStatus.BAD_REQUEST.toString() + " - " + HttpStatus.BAD_REQUEST.getReasonPhrase(),
                ex.getMostSpecificCause().getLocalizedMessage(),
                null
        );
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }*/

    // HTTP 400
    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // HTTP 400
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    // HTTP 404
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    // HTTP 405
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    // HTTP 415
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomErrors errors = new CustomErrors(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(), null);
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    // HTTP 500
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        String id = UUID.randomUUID().toString();
        log.error("{} {}", id, ex.toString(), ex);
        CustomErrors errors = new CustomErrors(
                id,
                HttpStatus.INTERNAL_SERVER_ERROR.toString() + " - " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "An unexpected error occurred during processing, please try again later.",
                null
        );
        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
