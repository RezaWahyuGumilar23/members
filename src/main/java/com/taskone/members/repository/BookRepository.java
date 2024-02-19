package com.taskone.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskone.members.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {}
