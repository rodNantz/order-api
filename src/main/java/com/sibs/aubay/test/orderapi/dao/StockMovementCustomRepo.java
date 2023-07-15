package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.StockMovement;

public interface StockMovementCustomRepo {

    StockMovement findByItem(Item item);

}
