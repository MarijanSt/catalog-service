package com.polarbookshop.catalog_service.domain.service;

import com.polarbookshop.catalog_service.domain.repository.BookRepository;
import com.polarbookshop.catalog_service.domain.entity.Book;
import com.polarbookshop.catalog_service.domain.exception.BookAlreadyExistsException;
import com.polarbookshop.catalog_service.domain.exception.BookNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Iterable<Book> viewBookList() {
        return bookRepository.findAll();
    }

    public Book viewBookDetails(String isbn) {
        return bookRepository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException(isbn));
    }

    public Book addBookToCatalog(Book book) {
        if (bookRepository.findByIsbn(book.isbn()).isPresent()) {
            throw new BookAlreadyExistsException(book.isbn());
        }
        return bookRepository.save(book);
    }

    public void removeBookFromCatalog(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book editBookDetails(String isbn, Book book) {
        return bookRepository.findByIsbn(isbn)
                .map(existingBook -> {
            return bookRepository.save(new Book(existingBook.isbn(), book.title(), book.author(), book.price()));
        })
                .orElseGet(() -> addBookToCatalog(book));
    }


}
