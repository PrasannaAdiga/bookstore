package com.bookstore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

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
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public List<Book> getBooks() {
		return bookService.findAll();
	}
	
	@RequestMapping(value="/books", method=RequestMethod.PUT)
	public Book updateBook(@RequestBody Book book) {
		return bookService.save(book);
	}
	
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public Book getBook(@PathVariable("id") Long id){
		Book book = bookService.findOne(id);
		return book;
	}
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity<String> upload(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value="/update/image", method=RequestMethod.POST)
	public ResponseEntity<String> updateImagePost(
			@RequestParam("id") Long id,
			HttpServletResponse response, HttpServletRequest request
			){
		try {
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			Iterator<String> it = multipartRequest.getFileNames();
			MultipartFile multipartFile = multipartRequest.getFile(it.next());
			String fileName = id+".png";
			
			Files.delete(Paths.get("src/main/resources/static/image/book/"+fileName));
			
			byte[] bytes = multipartFile.getBytes();
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/"+fileName)));
			stream.write(bytes);
			stream.close();
			
			return new ResponseEntity<String>("Upload Success!", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>("Upload failed!", HttpStatus.BAD_REQUEST);
		}
	}
	
}
