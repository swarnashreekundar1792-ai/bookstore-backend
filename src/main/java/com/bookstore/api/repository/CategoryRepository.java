package com.bookstore.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.api.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
