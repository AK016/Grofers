package com.grofers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.Entity.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long>{

}
