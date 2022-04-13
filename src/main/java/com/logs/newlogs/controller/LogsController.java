package com.logs.newlogs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Timestamp;

@RestController
public class LogsController {

    //Logger variable to create logs for logsController class

    //createLogs function
    //input:parameter send by different microservices : caller[microservice name], message[message to log], logLevel[logging level which microservice wants to create log ]
    //output: log entry in file log4j.log
    @PostMapping("/createLogs")
    public ResponseEntity<?> createLogs(@RequestParam("caller") String caller, @RequestParam("message") String message, @RequestParam("logLevel") LogLevel logLevel){

        //Logger variable to create logs for logsController class
        Logger LOGGER = LogManager.getLogger(caller);
        //try block
        try {
            //switch case to create log entry based on log level
             switch(logLevel){

                case DEBUG:
                    //logger.log();
                    LOGGER.debug(message);
                    break;

                case ERROR:
                case FATAL:
                    LOGGER.error(message);
                    break;

                case WARN:
                    LOGGER.warn(message);
                    break;

                case OFF:
                    LOGGER.info("Logging is OFF");
                    break;

                case TRACE:
                    LOGGER.trace(message);
                    break;

                default:
                case INFO:
                    LOGGER.info(message);
                    break;
            }
            //response entity in case of log is created successfully return http status OK
            return new ResponseEntity<>("logs created", HttpStatus.OK);
        }
        //Exception response back to calling microservice
        catch(Exception e){
            //response entity in case of exception return http status for expectation failed
            return new ResponseEntity<>("unable to creates logs because of:"+e,HttpStatus.EXPECTATION_FAILED);
        }
    }

}

