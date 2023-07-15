package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.StockMovement;
import org.springframework.data.jpa.repository.JpaRepository;

//@RepositoryRestResource
public interface StockMovementRepository extends JpaRepository<StockMovement,Integer>, StockMovementCustomRepo {

}
