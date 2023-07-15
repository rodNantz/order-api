package com.sibs.aubay.test.orderapi.rest;

import com.sibs.aubay.test.orderapi.email.CompletedOrderInfo;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import com.sibs.aubay.test.orderapi.exception.CustomException;
import com.sibs.aubay.test.orderapi.service.EmailService;
import com.sibs.aubay.test.orderapi.service.OrderService;
import com.sibs.aubay.test.orderapi.service.StockMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderRestController {

    Logger logger = LoggerFactory.getLogger(OrderRestController.class);

    private OrderService orderService;
    private StockMovementService stockService;
    private EmailService emailService;

    // when a stock movement is created, the system should try to attribute it
    // to an order that isn't complete.

    public OrderRestController(OrderService orderService,
                               StockMovementService stockService,
                               EmailService emailService) {
        this.orderService = orderService;
        this.stockService = stockService;
        this.emailService = emailService;
    }


    // GET all
    @GetMapping("/orders")
    public List<Order> findAll(){
        return orderService.findAll();
    }

    // GET by id
    @GetMapping("/orders/{orderId}")
    public Order getOrder(@PathVariable int orderId){
        Order order = orderService.findById(orderId);

        if (order == null)
            throw new RuntimeException("Order not found: "+ order);

        return order;
    }


    // POST
    @PostMapping("/orders")
    public Order addOrder(@RequestBody Order order){
        Order orderWithSameItemAndUser = orderService.findByItemAndUser(order);
        if (orderWithSameItemAndUser != null) {
            orderWithSameItemAndUser.setQuantity(order.getQuantity()
                    + orderWithSameItemAndUser.getQuantity());
            return updateOrder(orderWithSameItemAndUser);
        }

        order.setId(0);
        return this.updateOrder(order);
    }

    // PUT
    @PutMapping("/orders")
    public Order updateOrder(@RequestBody Order order){
        StockMovement stock = stockService.findByItem(order.getItem());
        if (stock != null) {
            if (stockService.validateStockMovement(stock, order)) {
                CompletedOrderInfo completionInfo = new CompletedOrderInfo(
                                    order, order.getQuantity(), stock, stock.getQuantity());
                Order newOrder = stockService.updateStockSaveOrder(stock, order);
                emailService.sendEmail(completionInfo);
                return newOrder;
            }
        }

        return orderService.save(order);
    }


    // DELETE
    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable int orderId){
        Order dbOrder = orderService.findById(orderId);
        if (dbOrder == null)
            throw new CustomException("Order id not found: "+ orderId);

        orderService.deleteById(orderId);

        return "Deleted order id: "+ orderId;
    }

}
