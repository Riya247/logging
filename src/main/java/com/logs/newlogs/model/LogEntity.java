package com.logs.newlogs.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Component;

import java.security.Timestamp;
@Getter
@Setter
@Component
public class LogEntity {

    private String caller;
    private Timestamp time;
    private LogLevel logLevel;
    private String message;

}
