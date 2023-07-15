package com.sibs.aubay.test.orderapi.rest;

import com.sibs.aubay.test.orderapi.email.CompletedOrderInfo;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import com.sibs.aubay.test.orderapi.service.EmailService;
import com.sibs.aubay.test.orderapi.service.OrderService;
import com.sibs.aubay.test.orderapi.service.StockMovementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StockMovementRestController {

    Logger logger = LoggerFactory.getLogger(StockMovementRestController.class);

    private StockMovementService stockService;
    private OrderService orderService;
    private EmailService emailService;

    // when a stock movement is created, the system should try to attribute it
    // to an order that isn't complete.

    public StockMovementRestController(StockMovementService stockService,
                                       OrderService orderService,
                                       EmailService emailService){
        this.stockService = stockService;
        this.orderService = orderService;
        this.emailService = emailService;
    }


    // GET all
    @GetMapping("/stocks")
    public List<StockMovement> findAll(){
        return stockService.findAll();
    }

    // GET by id
    @GetMapping("/stocks/{stockId}")
    public StockMovement getStockMovement(@PathVariable int stockId){
        StockMovement stock = stockService.findById(stockId);

        if (stock == null)
            throw new RuntimeException("Stock movement not found: "+ stock);

        return stock;
    }

    // POST
    @PostMapping("/stocks")
    public StockMovement addStockMove(@RequestBody StockMovement stockMove){
        StockMovement stockWithSameItem = stockService.findByItem(stockMove.getItem());
        if (stockWithSameItem != null) {
            stockWithSameItem.setQuantity(stockMove.getQuantity()
                                        + stockWithSameItem.getQuantity());
            return updateStock(stockWithSameItem);
        }
        stockMove.setId(0);
        return updateStock(stockMove);
    }


    // PUT
    @PutMapping("/stocks")
    public StockMovement updateStock(@RequestBody StockMovement stockMove){
        Order order = orderService.findByItem(stockMove.getItem());
        if (order != null){
            if (stockService.validateStockMovement(stockMove, order)) {
                CompletedOrderInfo completionInfo = new CompletedOrderInfo(
                        order, order.getQuantity(), stockMove, stockMove.getQuantity());
                StockMovement stockSaved = orderService.updateOrderSaveStock(order, stockMove);
                emailService.sendEmail(completionInfo);
                return stockSaved;
            }
        }

        return stockService.save(stockMove);
    }


    // DELETE
    @DeleteMapping("/stocks/{stockId}")
    public String deleteStock(@PathVariable int stockId){
        StockMovement dbStock = stockService.findById(stockId);
        if (dbStock == null)
            throw new RuntimeException("Stock movement id not found: "+ stockId);

        stockService.deleteById(stockId);

        return "Deleted stock movement id: "+ stockId;
    }


}
