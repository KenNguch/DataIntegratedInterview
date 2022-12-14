package com.dataintegrated.interview.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


/**
 *
 * @param <ResponseData>
 */
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ResponseModel<ResponseData> {
    private int code = HttpStatus.OK.value();
    private String status = HttpStatus.OK.getReasonPhrase();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time = LocalDateTime.now();
    private final ResponseData data;
}
