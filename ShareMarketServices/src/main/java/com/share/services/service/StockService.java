package com.share.services.service;

import com.share.services.model.StockDetails;
import com.share.services.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class StockService{

    @Autowired
    private StockRepository stockRepository;

    Logger logger = LoggerFactory.getLogger(StockService.class);

    public StockDetails saveStockDetails(StockDetails stock){
        return stockRepository.save(stock);
    }

    public StockDetails getStockById(BigInteger id){
        return stockRepository.findById(id).orElse(null);
    }

    public StockDetails getStockByName(String name){
        try {
            StockDetails stockDetails = stockRepository.findByName(name);
            return stockDetails;
        } catch (Exception e) {
            logger.info("And the error is {}",e);
        }
        return new StockDetails();
    }
}
