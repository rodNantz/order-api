package com.sibs.aubay.test.orderapi.email;

import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
public class CompletedOrderInfo {

    @Getter @Setter
    private Order order;
    @Getter @Setter
    private int origOrderQuantity;

    @Getter @Setter
    private StockMovement stock;
    @Getter @Setter
    private int origStock;



}
