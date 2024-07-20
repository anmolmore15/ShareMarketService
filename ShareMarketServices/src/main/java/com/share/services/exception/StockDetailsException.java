package com.share.services.exception;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StockDetailsException extends Exception {
    public StockDetailsException() {
        super("StockDetailsException super caller.");
    }
    public StockDetailsException(String message) {
        super(message);
        log.info("In StockDetailsException");
    }
}
