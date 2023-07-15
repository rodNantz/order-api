package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemRepository extends JpaRepository<Item,Integer> {

}
