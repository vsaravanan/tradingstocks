package com.hackerrank.stocktrade.controller;

import com.hackerrank.stocktrade.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/erase")
public class ResourcesController {

    @Autowired
    TradeService tradeService;

    @RequestMapping(value = "", method = RequestMethod.DELETE)
    public void erase() {
        tradeService.deleteAll();

    }

    
}
