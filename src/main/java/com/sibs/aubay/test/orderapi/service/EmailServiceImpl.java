package com.sibs.aubay.test.orderapi.service;

import com.sibs.aubay.test.orderapi.email.CompletedOrderInfo;
import com.sibs.aubay.test.orderapi.entity.Order;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender mailSender;

    @Value( "${spring.mail.username}" )
    private String senderAddress;

    @Override
    public void sendEmail(CompletedOrderInfo completionInfo) {
        System.out.println("Email service... TODO");
        int origQtty = completionInfo.getOrigOrderQuantity();
        Order order = completionInfo.getOrder();
        int origStock = completionInfo.getOrigStock();
        StockMovement stock = completionInfo.getStock();

        StringBuilder loggerSb = new StringBuilder("Order completed: ");
        loggerSb.append(origQtty).append(" ");
        loggerSb.append(order.getItem().getName()).append(", from ");
        loggerSb.append(order.getUser().getName()).append(". ");

        loggerSb.append("Stock decreased from ").append(origStock);
        loggerSb.append(" to ").append(stock.getQuantity()).append(" items.");

        logger.info(loggerSb.toString());
        sendMail(loggerSb.toString(), order.getUser().getEmail());
    }


    private boolean sendMail(String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Orders system email");
        message.setText(content);
        message.setFrom(senderAddress);
        message.setTo(email);

        try {
            mailSender.send(message);
            logger.info("Email sent to "+ email);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return false;
        }
    }

}
