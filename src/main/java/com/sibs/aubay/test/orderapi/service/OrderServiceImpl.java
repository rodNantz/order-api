package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.dao.OrderRepository;
import com.sibs.aubay.test.orderapi.dao.StockMovementRepository;
import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import javax.transaction.Transactional;

import com.sibs.aubay.test.orderapi.entity.User;
import com.sibs.aubay.test.orderapi.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private StockMovementRepository stockRepository;

    public OrderServiceImpl(OrderRepository orderRepository,
                            StockMovementRepository stockRepository) {
        this.orderRepository = orderRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order findByItem(Item item) {
        return orderRepository.findByItem(item);
    }

    @Override
    public Order findByItemAndUser(Order order) {
        return orderRepository.findByItemAndUser(order.getItem(), order.getUser());
    }

    @Override
    public Order findById(int orderId) {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent())
            return order.get();
        else
            throw new CustomException("Order id not found: "+ orderId);
    }

    @Override
    @Transactional
    public StockMovement updateOrderSaveStock(Order order, StockMovement stock) {
        stock.setQuantity(stock.getQuantity() - order.getQuantity());
        order.setQuantity(0);

        orderRepository.save(order);
        return stockRepository.save(stock);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public void deleteById(int orderId) {
        orderRepository.deleteById(orderId);
    }
}
