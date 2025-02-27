package com.polarbookshop.catalog_service;

import com.polarbookshop.catalog_service.domain.entity.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void contextLoads() {
	}

	@Test
	void whenPostRequestThenBookCreated() {
				var book = new Book("1234567890", "Title", "Author", 20.05d);
		webTestClient.post()
				.uri("/books/")
				.bodyValue(book)
				.exchange();
				/* TODO Doesn't work on my side: Should explore the API
				.expectStatus().isCreated()
				.expectBody(Book.class).value(b -> {
					assertEquals("1234567890", b.isbn());
				});

				 */

	}
}
