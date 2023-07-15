package com.sibs.aubay.test.orderapi.dao;

import com.sibs.aubay.test.orderapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserRepository extends JpaRepository<User,Integer> {

}
