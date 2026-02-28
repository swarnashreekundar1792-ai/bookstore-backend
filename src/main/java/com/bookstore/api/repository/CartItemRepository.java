package com.bookstore.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bookstore.api.entity.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
