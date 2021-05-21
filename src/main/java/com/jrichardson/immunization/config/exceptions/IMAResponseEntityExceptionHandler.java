package com.jrichardson.immunization.config.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author john richardson
 *
 */
@ControllerAdvice
public class IMAResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(IMAEntityRequestException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(IMAEntityRequestException tue,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), tue.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(400), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IMADatabaseException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(IMADatabaseException tde,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), tde.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(500), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IMAInvalidRequestException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(IMAInvalidRequestException tire,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), tire.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(500), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IMAUserNotFoundException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(IMAUserNotFoundException tunfe,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), tunfe.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(404), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IMANotFoundException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(IMANotFoundException tunfe,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), tunfe.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(404), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(MethodArgumentTypeMismatchException mme,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), mme.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(400), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<IMAError> handleUserNotFoundException(Exception e,  WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), e.getMessage(),
                request.getDescription(false));
        IMAError error = new IMAError(HttpStatus.valueOf(500), false, errorDetails);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

