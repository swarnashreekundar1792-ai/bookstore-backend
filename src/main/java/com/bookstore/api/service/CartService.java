package com.bookstore.api.service;

import com.bookstore.api.entity.*;
import com.bookstore.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    // Get or create a cart for the logged in user
    public Cart getCartByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    // Add a book to the cart
    public Cart addToCart(String username, Long bookId, int quantity) {
        Cart cart = getCartByUsername(username);

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Check if this book is already in the cart
        List<CartItem> items = cart.getItems();
        if (items != null) {
            for (CartItem item : items) {
                if (item.getBook().getId().equals(bookId)) {
                    item.setQuantity(item.getQuantity() + quantity);
                    cartItemRepository.save(item);
                    return cartRepository.findById(cart.getId()).get();
                }
            }
        }

        // Book not in cart yet, create new cart item
        CartItem newItem = new CartItem();
        newItem.setCart(cart);
        newItem.setBook(book);
        newItem.setQuantity(quantity);
        cartItemRepository.save(newItem);

        return cartRepository.findById(cart.getId()).get();
    }

    // Remove an item from the cart
    public Cart removeFromCart(String username, Long cartItemId) {
        Cart cart = getCartByUsername(username);
        cartItemRepository.deleteById(cartItemId);
        return cartRepository.findById(cart.getId()).get();
    }

    // Clear the entire cart
    public void clearCart(String username) {
        Cart cart = getCartByUsername(username);
        cartItemRepository.deleteAll(cart.getItems());
    }
}