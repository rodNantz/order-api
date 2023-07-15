package com.sibs.aubay.test.orderapi.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomException extends RuntimeException {

    Logger logger = LoggerFactory.getLogger(CustomException.class);

    public CustomException(String message) {
        super(message);
        logException(message, null);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
        logException(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
        logException(null, cause);
    }


    private void logException(String s, Throwable cause){
        logger.error(s, cause);
    }

}
