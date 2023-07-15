package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.User;

public interface OrderCustomRepo {

    Order findByItem(Item item);

    Order findByItemAndUser(Item item, User user);

}
