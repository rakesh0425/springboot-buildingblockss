package com.stacksimpllify.restservices.springboot_buildingblock.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimpllify.restservices.springboot_buildingblock.entities.Order;

@Repository
public interface OrderRespository extends JpaRepository<Order, Long>{

}
