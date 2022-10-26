package com.dataintegrated.interview.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 *  This class is used to return an error response to the client
 */
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorLog {
    String message;
    String systemMessage;
    String url;
}
