package com.hackerrank.stocktrade.repository;

import com.hackerrank.stocktrade.model.Trade;
import com.hackerrank.stocktrade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeRepository  extends JpaRepository<Trade, Long> {

//    @Query(value="select t from Trade t where user in ( select u from User u where u.id = :userId) ")
//    @Query(value="from Trade t where t.user.id = :userId ")
//    Iterable<Trade> findAllByUser(@Param("userId") Long userId);
    Iterable<Trade> findAllByUser(User user);

    Iterable<Trade> findAllTradesBySymbol(String symbol);

    Iterable<Trade> findAllTradesByUserAndSymbol(User user, String symbol);



}
