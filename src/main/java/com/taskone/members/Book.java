package com.taskone.members;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long bookId;
	
	private String title;
	
	private String author;
	
	private String genre;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Borrow> borrow = new HashSet<>();
	
	public Book() {
		super();
	}

	public Book(Long bookId, String title, String author, String genre, Borrow borrow) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.genre = genre;
	}

	public Long getBookId() {
		return bookId;
	}

	public void setBookId(Long bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Set<Borrow> getBorrow() {
		return borrow;
	}

	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}
	
}
