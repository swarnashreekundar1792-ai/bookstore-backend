package com.bookstore.api.controller;

import com.bookstore.api.entity.Cart;
import com.bookstore.api.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {

    @Autowired
    private CartService cartService;

    // Get the logged in user's cart
    @GetMapping
    public Cart getCart(Principal principal) {
        return cartService.getCartByUsername(principal.getName());
    }

    // Add a book to the cart
    @PostMapping("/add")
    public Cart addToCart(@RequestParam Long bookId, @RequestParam int quantity, Principal principal) {
        return cartService.addToCart(principal.getName(), bookId, quantity);
    }

    // Remove a specific item from the cart
    @DeleteMapping("/remove/{cartItemId}")
    public Cart removeFromCart(@PathVariable Long cartItemId, Principal principal) {
        return cartService.removeFromCart(principal.getName(), cartItemId);
    }

    // Clear the entire cart
    @DeleteMapping("/clear")
    public String clearCart(Principal principal) {
        cartService.clearCart(principal.getName());
        return "Cart cleared successfully";
    }
}