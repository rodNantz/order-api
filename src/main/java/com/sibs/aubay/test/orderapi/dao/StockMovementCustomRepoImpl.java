package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Item;
import com.sibs.aubay.test.orderapi.entity.StockMovement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StockMovementCustomRepoImpl implements StockMovementCustomRepo {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public StockMovementCustomRepoImpl(EntityManager em){
        this.entityManager = em;
    }

    private final String itemJpql = "from StockMovement where item_id = :itemId ";

    @Override
    public StockMovement findByItem(Item item) {
        TypedQuery<StockMovement> query = entityManager.createQuery(itemJpql, StockMovement.class);
        List<StockMovement> stocks = query.setParameter("itemId", item.getId())
                                            .getResultList();
        return (stocks.isEmpty() ? null : stocks.get(0));
    }

}
