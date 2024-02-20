package com.taskone.members.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.taskone.members.base.BaseService;
import com.taskone.members.exceptions.MemberNotFoundException;
import com.taskone.members.model.Book;
import com.taskone.members.repository.BookRepository;

import jakarta.transaction.Transactional;

@Service
public class BookService implements BaseService<Book> {
	
	@Autowired
	private BookRepository bookRepository; 

	@Override
	@Transactional
	public List<Book> all() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional
	public Book item(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
	}

	@Override
	@Transactional
	public Book create(Book data) {
		return bookRepository.save(data);
	}

	@Override
	@Transactional
	public void update(Long id, Book data) {
		bookRepository.findById(id).map(book -> {
			book.setTitle(data.getTitle());
			book.setAuthor(data.getAuthor());
			book.setGenre(data.getGenre());
			return bookRepository.save(book);
		})
		.orElseThrow(() -> new MemberNotFoundException(id));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		bookRepository.deleteById(id);
	}

}
