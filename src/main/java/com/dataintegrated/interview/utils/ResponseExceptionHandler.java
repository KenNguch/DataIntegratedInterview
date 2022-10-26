package com.dataintegrated.interview.utils;

import com.dataintegrated.interview.models.ErrorLog;
import com.dataintegrated.interview.models.ExemptionMessages;
import com.dataintegrated.interview.models.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class ResponseExceptionHandler extends DefaultErrorAttributes {
    @ExceptionHandler(value = {NullPointerException.class, InterruptedException.class, RuntimeException.class, OutOfMemoryError.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected @ResponseBody ResponseModel<ErrorLog> handleNullPointerException(Exception ex, WebRequest request) {
        var error = new ErrorLog(ExemptionMessages.INTERNAL_SERVER_ERROR.getCustomMessage(), ex.getMessage(), request.getDescription(true));
        log.error("Error: {}", error);
        return new ResponseModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), LocalDateTime.now(), error);
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(value = HttpStatus.CONFLICT)
    protected @ResponseBody ResponseModel<ErrorLog> handleConflictException(NullPointerException ex, WebRequest request) {
        var error = new ErrorLog(ExemptionMessages.IllegalArgumentException.getCustomMessage(), ex.getMessage(), request.getDescription(true));
        log.error("Error: {}", error);
        return new ResponseModel<>(HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT.getReasonPhrase(), LocalDateTime.now(), error);
    }

    @ExceptionHandler(value = {JpaSystemException.class})
    @ResponseStatus(value = HttpStatus.BAD_GATEWAY)
    protected @ResponseBody ResponseModel<ErrorLog> handleNullPointerException(IllegalArgumentException ex, WebRequest request) {
        var error = new ErrorLog(ExemptionMessages.BAD_GATEWAY.getCustomMessage(), ex.getMessage(), request.getDescription(true));
        log.error("Error: {}", error);
        return new ResponseModel<>(HttpStatus.BAD_GATEWAY.value(), HttpStatus.BAD_GATEWAY.getReasonPhrase(), LocalDateTime.now(), error);
    }
}
