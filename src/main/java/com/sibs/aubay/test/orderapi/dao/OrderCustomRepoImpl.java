package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.Order;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sibs.aubay.test.orderapi.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderCustomRepoImpl implements OrderCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public OrderCustomRepoImpl(EntityManager em){
        this.entityManager = em;
    }

    private final String itemJpql = "from Order where item_id = :itemId ";
    private final String itemAndUserJpql = "from Order where item_id = :itemId " +
                                            " and user_id = :userId ";

    @Override
    public Order findByItem(Item item) {
        TypedQuery<Order> query = entityManager.createQuery(itemJpql, Order.class);
        List<Order> orders = query.setParameter("itemId", item.getId())
                                    .getResultList();
        return (orders.isEmpty() ? null : orders.get(0));
    }

    @Override
    public Order findByItemAndUser(Item item, User user) {
        TypedQuery<Order> query = entityManager.createQuery(itemAndUserJpql, Order.class);
        List<Order> orders = query.setParameter("itemId", item.getId())
                    .setParameter("userId", user.getId())
                    .getResultList();
        return (orders.isEmpty() ? null : orders.get(0));
    }

}
