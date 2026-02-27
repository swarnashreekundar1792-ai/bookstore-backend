package com.bookstore.api.controller;

import com.bookstore.api.entity.Book;
import com.bookstore.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:3000")
public class BookController {

    @Autowired
    private BookService bookService;

    // GET /api/books — get all books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // GET /api/books/{id} — get one book
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        if (book.isPresent()) {
            return ResponseEntity.ok(book.get());
        }
        return ResponseEntity.notFound().build();
    }

    // POST /api/books — create new book
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    // PUT /api/books/{id} — update existing book
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book book) {
        Book updated = bookService.updateBook(id, book);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // DELETE /api/books/{id} — delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // GET /api/books/search/{keyword} — search books by title or author
    @GetMapping("/search/{keyword}")
    public List<Book> searchBooks(@PathVariable String keyword) {
        return bookService.searchBooks(keyword);
    }

    // GET /api/books/category/{categoryId} — get books by category
    @GetMapping("/category/{categoryId}")
    public List<Book> getBooksByCategory(@PathVariable Long categoryId) {
        return bookService.getBooksByCategory(categoryId);
    }
}
