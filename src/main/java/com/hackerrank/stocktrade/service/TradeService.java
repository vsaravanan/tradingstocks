package com.hackerrank.stocktrade.service;

import com.hackerrank.stocktrade.exception.BadResourceRequestException;
import com.hackerrank.stocktrade.exception.NoSuchResourceFoundException;
import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import com.hackerrank.stocktrade.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class TradeService {
    
    @Autowired
    TradeRepository tradeRepository;

    @Autowired
    UserService userService;

    @Transactional
    public void createTrade(Trade trade) {
        Trade existingTrade = getTradeOrNull(trade.getId());

        if (existingTrade != null) {
            throw new BadResourceRequestException("Trade with same id exists.");
        }

        User newUser = userService.getOrCreateUser(trade.getUser());
        trade.setUser(newUser);

        tradeRepository.save(trade);
    }

    private Trade getTradeOrNull(Long id) {
        return tradeRepository.findById(id).orElse(null);
    }

    public Trade getTradeById(Long id) {

        Trade trade = getTradeOrNull(id);

        if (trade == null) {
            throw new NoSuchResourceFoundException("No Trade with given id found.");
        }

        return trade;
    }

    public Iterable<Trade> findAll() {

        return tradeRepository.findAll();
    }

    public Iterable<Trade> findAllTradesByUserId(Long id) {

        User user = userService.getUserById(id);

        return tradeRepository.findAllByUser(user);

    }


    public Iterable<Trade> findAllTradesBySymbol(String stockSymbol) {

        Iterable<Trade> trades = tradeRepository.findAllTradesBySymbol(stockSymbol);
        if (! trades.iterator().hasNext()) {
            throw new NoSuchResourceFoundException("No Trade with given stock symbol found.");
        }

        return trades;
    }

    public Iterable<Trade> findAllTradesByUserIdAndSymbol(Long userId, String stockId) {

        User user = userService.getUserById(userId);

        Iterable<Trade> trades = tradeRepository.findAllTradesByUserAndSymbol(user, stockId);
        if (! trades.iterator().hasNext()) {
            throw new NoSuchResourceFoundException("No Trade with given userId and stock symbol found.");
        }

        return trades;
    }

    @Transactional
    public void deleteAll() {
        tradeRepository.deleteAll();
        userService.deleteAll();
    }

}
