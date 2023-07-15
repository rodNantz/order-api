package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import com.sibs.aubay.test.orderapi.entity.User;

import java.util.List;

public interface OrderService {

    List<Order> findAll();

    Order findByItem(Item item);

    Order findByItemAndUser(Order order);

    Order findById(int orderId);

    StockMovement updateOrderSaveStock(Order order, StockMovement stock);

    Order save(Order order);

    void deleteById(int orderId);
}
