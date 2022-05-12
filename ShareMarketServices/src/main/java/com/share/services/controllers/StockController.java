package com.share.services.controllers;

import com.share.services.model.StockDetails;
import com.share.services.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
public class StockController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public StockDetails getStockDetails(@RequestParam(value = "id") BigInteger stockId){
        return stockService.getStockById(stockId);
    }

    @PostMapping("/stock")
    public StockDetails saveStockDetails(@RequestBody StockDetails stockDetails){
        return stockService.saveStockDetails(stockDetails);
    }

    @GetMapping("/stockByName")
    public ResponseEntity<StockDetails> getStockByName(@RequestParam String name){
        return new ResponseEntity<>(stockService.getStockByName(name),HttpStatus.OK);
    }
}
