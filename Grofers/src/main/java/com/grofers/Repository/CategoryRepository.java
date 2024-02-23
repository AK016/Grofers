package com.grofers.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grofers.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
