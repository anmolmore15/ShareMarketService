package com.share.services.service;

import com.share.services.model.StockDetails;
import com.share.services.model.enums.Cap;
import com.share.services.repository.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Optional;

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

    public Optional<StockDetails> getStockByName(String name){
        try {
            Optional<StockDetails> stockDetails = stockRepository.findByName(name);
            if(stockDetails.isPresent()){
                stockDetails.filter(s -> s.getCap().equals(Cap.MID));
            }
            return stockDetails;
        } catch (Exception e) {
            logger.info("And the error is {}",e);
        }
        return null;
    }


}
