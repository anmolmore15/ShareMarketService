package com.share.services.repository;

import com.share.services.model.StockDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;

public interface StockRepository extends JpaRepository<StockDetails,BigInteger> {
    public ResponseEntity<StockDetails> getStockByName(String name);

    public StockDetails findByName(String name);
}
