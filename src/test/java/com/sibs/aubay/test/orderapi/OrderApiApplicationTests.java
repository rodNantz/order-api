package com.sibs.aubay.test.orderapi;

import com.sibs.aubay.test.orderapi.dao.UserRepository;
import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import com.sibs.aubay.test.orderapi.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OrderApiApplicationTests {

	UserRepository userRepository;

	public OrderApiApplicationTests(UserRepository userR){
		this.userRepository = userR;

	}

	@Test
	void contextLoads() {
	}



}
