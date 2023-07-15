package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.dao.OrderRepository;
import com.sibs.aubay.test.orderapi.dao.StockMovementRepository;
import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import com.sibs.aubay.test.orderapi.exception.CustomException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockMovementServiceImpl implements StockMovementService {

    private StockMovementRepository stockRepository;
    private OrderRepository orderRepository;

    @Autowired
    public StockMovementServiceImpl(StockMovementRepository stockRepository,
                                    OrderRepository orderRepository){
        this.stockRepository = stockRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<StockMovement> findAll() {
        return stockRepository.findAll();
    }

    @Override
    public StockMovement findByItem(Item item) {
        return stockRepository.findByItem(item);
    }

    @Override
    public StockMovement findById(int stockId) {
        Optional<StockMovement> stock = stockRepository.findById(stockId);
        if (stock.isPresent())
            return stock.get();
        else
            throw new CustomException("Stock movement ID not found: "+ stockId);
    }

    @Override
    @Transactional
    public Order updateStockSaveOrder(StockMovement stock, Order order) {
        stock.setQuantity(stock.getQuantity() - order.getQuantity());
        order.setQuantity(0);

        stockRepository.save(stock);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public StockMovement save(StockMovement stockMove) {
        return stockRepository.save(stockMove);
    }

    @Override
    @Transactional
    public void deleteById(int stockId) {
        stockRepository.deleteById(stockId);
    }

    @Override
    public boolean validateStockMovement(StockMovement stock, Order order) {
        return order.getQuantity() > 0
                && stock.getQuantity() >= order.getQuantity();
    }
}
