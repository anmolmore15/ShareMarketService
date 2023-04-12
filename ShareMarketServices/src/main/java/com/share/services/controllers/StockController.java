package com.share.services.controllers;

import com.share.services.exception.StockDetailsException;
import com.share.services.model.StockDetails;
import com.share.services.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.util.Map;
import java.util.Optional;


@RestController
public class StockController {

    private StockService stockService;

    @Autowired
    public StockController(StockService stockService){
       this.stockService = stockService;
    }


    private Logger logger = LoggerFactory.getLogger(StockController.class);

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
    public ResponseEntity<Optional<StockDetails>> getStockByName(@RequestParam String name)
            throws StockDetailsException {
        Optional<StockDetails> stockDetails = stockService.getStockByName(name);

        logger.info("stock details are {}", stockDetails);

        if(stockDetails.isPresent()){
            return ResponseEntity.ok(stockDetails);
        }
        else {
            throw new StockDetailsException("Stock details not found");
        }
    }
}
