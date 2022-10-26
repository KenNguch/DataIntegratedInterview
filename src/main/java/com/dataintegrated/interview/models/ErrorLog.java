package com.dataintegrated.interview.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorLog {
    String message;
    String systemMessage;
    String url;
}
