package io.test.money_transfer.config.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<Object> handleValidationException(final ValidationException ex, final WebRequest request) {
        var httpStatus = BAD_REQUEST;
        var errorResponse = ErrorResponse.of(ex.getMessage(), httpStatus.value(), ex.getStatus());
        log.error("Handle ValidationException:", ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    protected ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex, final WebRequest request) {
        var httpStatus = HttpStatus.NOT_FOUND;
        var errorResponse = ErrorResponse.of(ex.getMessage(), httpStatus.value(), httpStatus.getReasonPhrase());
        log.error("Handle EntityNotFoundException:", ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {
        var httpStatus = BAD_REQUEST;
        var validationFields = ex.getConstraintViolations().stream()
                .map(this::wrap)
                .collect(Collectors.toList());
        log.error("Handle ConstraintViolationException:", ex);
        return ResponseEntity.status(httpStatus).body(ErrorResponse.of(httpStatus.name(),
                "Validation errors", httpStatus.value(), validationFields));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        log.warn("Handle MethodArgumentTypeMismatchException", ex);
        var httpStatus = BAD_REQUEST;
        var errorResponse = ErrorResponse.of(httpStatus.getReasonPhrase(), httpStatus.value(),
                String.format("INVALID_%s", ex.getName().toUpperCase()));
        log.error("Handle MethodArgumentTypeMismatchException:", ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(final RuntimeException ex, final WebRequest request) {
        log.warn("Handle RuntimeException", ex);
        var httpStatus = BAD_REQUEST;
        var errorResponse = ErrorResponse.of(ex.getMessage(), httpStatus.value(), httpStatus.getReasonPhrase());
        log.error("Handle RuntimeException:", ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAnException(final Exception ex, final WebRequest request) {
        log.error("Handle Exception", ex);
        final HttpStatus httpStatus = INTERNAL_SERVER_ERROR;
        var errorResponse = ErrorResponse.of(httpStatus.getReasonPhrase(), httpStatus.value(), httpStatus.getReasonPhrase());
        log.error("Handle Exception:", ex);
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), httpStatus, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers,
                                                               final HttpStatus status, final WebRequest request) {
        var errorResponse = ErrorResponse.of("Invalid request body", status.value(), status.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
                                                                       final HttpHeaders headers, final HttpStatus status,
                                                                       final WebRequest request) {
        var errorResponse = ErrorResponse.of(ex.getMessage(), status.value(), status.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        var errorResponse = ErrorResponse.of(ex.getMessage(), status.value(), status.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @Override
    public ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        var unsupported = "Unsupported content type: " + ex.getContentType();
        var supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
        var errorResponse = ErrorResponse.of(String.join("; ", unsupported, supported, ex.getMessage()),
                status.value(), status.getReasonPhrase());
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        var httpStatus = BAD_REQUEST;
        var fields = ex.getBindingResult().getFieldErrors().stream()
                .map(this::wrap)
                .collect(Collectors.toList());
        var message = fields.isEmpty() ? ex.getBindingResult().getAllErrors().get(0).getDefaultMessage() : "Validation errors";
        return ResponseEntity.status(httpStatus).body(ErrorResponse.of(httpStatus.name(),
                message, httpStatus.value(), fields));
    }

    private ValidationResponse wrap(final FieldError fieldError) {
        return ValidationResponse.of(fieldError.getField(), fieldError.getRejectedValue(), fieldError.getDefaultMessage());
    }

    private ValidationResponse wrap(final ConstraintViolation violation) {
        var fieldName = StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                .reduce((first, second) -> second)
                .map(Path.Node::getName)
                .orElse(StringUtils.EMPTY);
        return ValidationResponse.of(fieldName, violation.getInvalidValue(), violation.getMessage());
    }
}
