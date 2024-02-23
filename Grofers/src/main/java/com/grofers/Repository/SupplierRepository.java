package com.grofers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.Entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{
	
}
