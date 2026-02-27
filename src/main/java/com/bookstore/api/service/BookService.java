package com.bookstore.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.api.entity.Book;
import com.bookstore.api.repository.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        Book savedBook = bookRepository.save(book);
        return bookRepository.findById(savedBook.getId()).orElse(savedBook);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCategory(bookDetails.getCategory());
        book.setPrice(bookDetails.getPrice());
        book.setStock(bookDetails.getStock());
        book.setDescription(bookDetails.getDescription());
        book.setIsbn(bookDetails.getIsbn());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> searchBooks(String keyword) {
        return bookRepository
            .findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(keyword, keyword);
    }

    public List<Book> getBooksByCategory(Long categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }
}
