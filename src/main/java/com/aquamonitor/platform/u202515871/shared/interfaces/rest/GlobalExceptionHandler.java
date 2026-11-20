package com.aquamonitor.platform.u202515871.shared.interfaces.rest;

import com.aquamonitor.platform.u202515871.shared.application.result.ApplicationError;
import com.aquamonitor.platform.u202515871.shared.interfaces.rest.resources.MessageResource;
import com.aquamonitor.platform.u202515871.shared.interfaces.rest.transform.MessageResourceFromApplicationErrorAssembler;
import jakarta.servlet.ServletException;
import org.jspecify.annotations.NullMarked;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.text.MessageFormat;
import java.util.Comparator;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@RestControllerAdvice
@NullMarked
public class GlobalExceptionHandler {
    private static final String MESSAGES_BASENAME = "messages";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageResource> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        var errorDetails = ex.getBindingResult().getFieldErrors().stream()
                .sorted(Comparator.comparing(FieldError::getField))
                .map(FieldError::getDefaultMessage)
                .reduce((a, b) -> a + "; " + b)
                .orElse(resolveMessageOrDefault("validation.request.failed", "Request validation failed"));
        var applicationError = ApplicationError.validationError("request-body", errorDetails);
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<MessageResource> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        var applicationError = ApplicationError.validationError(
                "request-body",
                resolveMessageOrDefault("validation.request.malformed", "The request body is malformed or contains invalid data types"));
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<MessageResource> handleIllegalArgumentException(IllegalArgumentException ex) {
        var applicationError = ApplicationError.validationError(
                resolveMessageOrDefault("validation.request.argument", "request-argument"),
                ex.getMessage() != null ? ex.getMessage() : resolveMessageOrDefault("validation.request.failed", "Request validation failed")
        );
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<MessageResource> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        var applicationError = ApplicationError.conflict("resource", "data-integrity-violation");
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    @ExceptionHandler(ServletException.class)
    public ResponseEntity<MessageResource> handleServletException(ServletException ex) {
        var status = ex instanceof ErrorResponse errorResponse ? errorResponse.getStatusCode().value() : 400;
        var message = resolveMessageOrDefault("error.request.message", "The request could not be processed");
        return ResponseEntity.status(status).body(new MessageResource(message));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResource> handleRuntimeException(RuntimeException ex) {
        var applicationError = ApplicationError.unexpected(
                resolveMessageOrDefault("error.unexpected.context", "global-exception-handler"),
                ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred"
        );
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<MessageResource> handleException(Exception ex) {
        var applicationError = ApplicationError.unexpected(
                resolveMessageOrDefault("error.unexpected.context", "global-exception-handler"),
                ex.getMessage() != null ? ex.getMessage() : "An unexpected error occurred"
        );
        return MessageResourceFromApplicationErrorAssembler.toResponseFromApplicationError(applicationError);
    }

    private String resolveMessageOrDefault(String key, String defaultValue, Object... args) {
        try {
            var bundle = ResourceBundle.getBundle(MESSAGES_BASENAME, LocaleContextHolder.getLocale());
            if (!bundle.containsKey(key)) {
                return defaultValue;
            }
            return MessageFormat.format(bundle.getString(key), args);
        } catch (MissingResourceException ex) {
            return defaultValue;
        }
    }
}
