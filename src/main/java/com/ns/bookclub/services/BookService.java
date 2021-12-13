package com.ns.bookclub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ns.bookclub.models.Book;
import com.ns.bookclub.models.User;
import com.ns.bookclub.repositories.BookRepository;
import com.ns.bookclub.repositories.UserRepository;

@Service
public class BookService {
	
	private final BookRepository bookRepo;
	private final UserRepository userRepo;
	public BookService(BookRepository bookRepo, UserRepository userRepo) {
		this.bookRepo = bookRepo;
		this.userRepo = userRepo;
	}
	
	public List<Book> allBooks(){
		return bookRepo.findAll();
	}
	public List<User> allUsers(){
		return userRepo.findAll();
	}
	
	public Book findBook(Long id){
		Optional<Book> optionalBook = bookRepo.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	public Book saveBook(Book book) {
		return bookRepo.save(book);
	}
	
	public void deleteBook(Long id) {
		bookRepo.deleteById(id);
	}
}
