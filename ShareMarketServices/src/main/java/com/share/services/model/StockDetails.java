package com.share.services.model;

import com.share.services.model.enums.Cap;
import com.share.services.model.enums.Category;
import com.share.services.model.enums.ExchangeType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="stockdetails")
public class StockDetails {
    @Id
    @GeneratedValue
    private BigInteger id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ExchangeType exchange;

    @Enumerated(EnumType.STRING)
    private Cap cap;

    @Enumerated(EnumType.STRING)
    private Category category;
}
