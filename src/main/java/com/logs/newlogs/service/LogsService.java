package com.logs.newlogs.service;

import com.logs.newlogs.model.LogEntity;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.security.Timestamp;

@Service
public class LogsService {

    //creating logger for AiknoSSD logs
    static Logger LOGGER = LogManager.getLogger(LogsService.class);
    @Autowired
    LogEntity logAttributes;

    public void createAllLogs(String className, String msg, LogLevel level){


        logAttributes.setCaller(className);
        //logAttributes.setTime(time);
        logAttributes.setMessage(msg);
        logAttributes.setLogLevel(level);

        LogLevel l=logAttributes.getLogLevel();
        switch(l){

            case DEBUG:
                LOGGER.debug(className+": "+msg);
                break;

            case ERROR:
            case FATAL:
                LOGGER.error(className+": "+msg);
                break;

            case WARN:
                LOGGER.warn(className+": "+msg);
                break;

            case OFF:
                LOGGER.info(className+": Logging is off");
                break;

            case TRACE:
                LOGGER.trace(className+": "+msg);
                break;

            default:
            case INFO:
                LOGGER.info(className+": "+msg);
                break;
        }

    }


}
