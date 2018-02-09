package com.jacksondouglas.ordering.controller.exception;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.jacksondouglas.ordering.service.exception.AuthorizationException;
import com.jacksondouglas.ordering.service.exception.DataIntegrityException;
import com.jacksondouglas.ordering.service.exception.FileException;
import com.jacksondouglas.ordering.service.exception.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {

    /**
     * Invoked when the {@link ObjectNotFoundException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} NOT_FOUND.
     */
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Invoked when the {@link DataIntegrityException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Invoked when the {@link MethodArgumentNotValidException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Validation error", System.currentTimeMillis());
        for (FieldError f : e.getBindingResult().getFieldErrors()) {
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Invoked when the {@link AuthorizationException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} FORBIDDEN.
     */
    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

    /**
     * Invoked when the {@link FileException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(FileException.class)
    public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Invoked when the {@link AmazonServiceException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(AmazonServiceException.class)
    public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
        HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
        StandardError error = new StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(code).body(error);
    }

    /**
     * Invoked when the {@link AmazonClientException} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(AmazonClientException.class)
    public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Invoked when the {@link AmazonS3Exception} is thrown.
     *
     * @param e exception thrown
     * @param request current request
     * @return the generated response with {@link HttpStatus} BAD_REQUEST.
     */
    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> amazonS3(AmazonS3Exception e, HttpServletRequest request) {
        StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
