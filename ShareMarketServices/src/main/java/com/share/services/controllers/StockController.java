package com.share.services.controllers;

import com.share.services.model.StockDetails;
import com.share.services.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    Logger logger = LoggerFactory.getLogger(StockController.class);

    @GetMapping("/stock")
    public StockDetails getStockDetails(@RequestParam(value = "id") BigInteger stockId){
        return stockService.getStockById(stockId);
    }

    @PostMapping("/stock")
    public StockDetails saveStockDetails(@RequestBody StockDetails stockDetails){
        StockDetails savedStockDetails = stockService.saveStockDetails(stockDetails);
        logger.info("stock details are {}", savedStockDetails);
        return savedStockDetails;
    }

    @GetMapping("/stockByName")
    public ResponseEntity<StockDetails> getStockByName(@RequestParam String name){
        StockDetails stockDetails = stockService.getStockByName(name);
        logger.info("stock details are {}", stockDetails);
        return new ResponseEntity<>(stockDetails,HttpStatus.OK);
    }
}
