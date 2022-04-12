package com.logs.newlogs.controller;

import com.logs.newlogs.service.LogsService;
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

    @Autowired
    private LogsService logService;

    @PostMapping("/createLogs")
    public ResponseEntity<?> createLogs(@RequestParam("caller") String caller,@RequestParam("message") String message, @RequestParam("logLevel") LogLevel logLevel){
        try {

            logService.createAllLogs(caller, message, logLevel);
            return new ResponseEntity<>("logs created", HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>("unable to creates logs because of:"+e,HttpStatus.EXPECTATION_FAILED);
        }
    }

}

