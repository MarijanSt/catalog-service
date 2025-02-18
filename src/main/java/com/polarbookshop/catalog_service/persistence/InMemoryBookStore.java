package com.polarbookshop.catalog_service.persistence;

import com.polarbookshop.catalog_service.domain.entity.Book;
import com.polarbookshop.catalog_service.domain.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryBookStore implements BookRepository {

    private static final Map<String, Book> books = new ConcurrentHashMap<>();

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
        Book searchedBook = books.get(isbn);
        return Optional.ofNullable(searchedBook);
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return (books.get(isbn) != null);
    }

    @Override
    public Book save(Book book) {
        return books.put(book.isbn(), book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
        books.remove(books.get(isbn));
    }
}
