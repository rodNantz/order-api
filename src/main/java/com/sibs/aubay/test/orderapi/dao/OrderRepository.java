package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Integer>, OrderCustomRepo {

}
