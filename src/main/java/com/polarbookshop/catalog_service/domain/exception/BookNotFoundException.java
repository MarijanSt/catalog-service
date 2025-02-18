package com.polarbookshop.catalog_service.domain.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String isbn) {
      super("The book with the specified ISBN is not found: " + isbn);
    }
}
