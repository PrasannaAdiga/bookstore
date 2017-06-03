package com.bookstore.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;

@RestController
public class BookResource {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping (value="/books", method=RequestMethod.POST)
	public Book createBook(@RequestBody Book book) {
		return bookService.save(book);
	}
	
}
