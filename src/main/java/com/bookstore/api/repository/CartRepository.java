package com.bookstore.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bookstore.api.entity.Cart;
import com.bookstore.api.entity.User;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Optional<Cart> findByUser(User user);
}
