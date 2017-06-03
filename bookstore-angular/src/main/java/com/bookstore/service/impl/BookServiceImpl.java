package com.bookstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookstore.domain.Book;
import com.bookstore.repository.BookRepository;
import com.bookstore.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public List<Book> findAll() {
		List<Book> bookList = (List<Book>) bookRepository.findAll();
		return getActiveBookList(bookList);
	}
	
	@Override
	public Book findOne(Long id) {
		return bookRepository.findOne(id);
	}
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public List<Book> blurrySearch(String keyword) {
		List<Book> bookList = bookRepository.findByTitleContaining(keyword);
		return getActiveBookList(bookList);
	}
	
	@Override
	public void removeOne(Long id) {
		bookRepository.delete(id);
	}
	
	private List<Book> getActiveBookList(List<Book> bookList) {
        List<Book> activeBookList = new ArrayList<>();
		for (Book book : bookList) {
			if(book.isActive()) {
				activeBookList.add(book);
			}
		}
		return activeBookList;
	}
	
}
