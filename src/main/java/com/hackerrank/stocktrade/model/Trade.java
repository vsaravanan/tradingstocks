package com.hackerrank.stocktrade.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
public class Trade implements Serializable {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;
    private String symbol;
    private Integer shares;
    private Float price;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp timestamp;
    
    public Trade() {
    }
    
    public Trade(Long id, String type, User user, String symbol, Integer quantity, Float price, Timestamp timestamp) {

        this.id = id;
        this.type = type;
        this.user = user;
        this.symbol = symbol;
        this.shares = quantity;
        this.price = price;
        this.timestamp = timestamp;
    }

}
