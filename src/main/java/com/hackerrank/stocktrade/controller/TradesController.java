package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/trades")
public class TradesController {

    @Autowired
    TradeService tradeService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewTrade(@RequestBody @Valid Trade trade) {

        tradeService.createTrade(trade);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Iterable< Trade> findAll() {

        return tradeService.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Trade getTradeById(@PathVariable("id") Long id) {
        return  tradeService.getTradeById(id);
    }

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public Iterable< Trade> findAllTradesByUserId(@PathVariable("id") Long id) {

        return tradeService.findAllTradesByUserId(id);
    }

    @RequestMapping(value = "/stocks/{id}", method = RequestMethod.GET)
    public Iterable< Trade> findAllTradesBySymbol(@PathVariable("id") String id) {

        return tradeService.findAllTradesBySymbol(id);
    }

    @RequestMapping(value = "/users/{userId}/stocks/{stockId}", method = RequestMethod.GET)
    public Iterable< Trade> findAllTradesByUserIdAndSymbol(
            @PathVariable("userId") Long userId,
            @PathVariable("stockId") String stockId
            ) {

        return tradeService.findAllTradesByUserIdAndSymbol(userId, stockId);
    }

}
