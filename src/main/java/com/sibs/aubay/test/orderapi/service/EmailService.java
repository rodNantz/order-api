package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.email.CompletedOrderInfo;
import com.sibs.aubay.test.orderapi.entity.Order;

public interface EmailService {

    void sendEmail(CompletedOrderInfo completionInfo);

}
