package com.polarbookshop.catalog_service.domain.exception;

public class BookAlreadyExistsException extends RuntimeException {
    public BookAlreadyExistsException(String isbn) {
        super("The book with that ISBN exists already in the catalog: " + isbn);
    }
}
