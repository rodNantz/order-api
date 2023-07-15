package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;

import java.util.List;

public interface StockMovementService {

    List<StockMovement> findAll();

    StockMovement findByItem(Item item);

    StockMovement findById(int stockId);

    Order updateStockSaveOrder(StockMovement stock, Order order);

    StockMovement save(StockMovement stockMove);

    void deleteById(int stockId);

    boolean validateStockMovement(StockMovement stock, Order order);

}
