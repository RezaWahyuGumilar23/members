package com.taskone.members.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskone.members.model.Borrow;
import com.taskone.members.services.BorrowService;

@RestController
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@PostMapping("/members/borrow")
	public Borrow borrowBook(@RequestBody Borrow borrow) {
		return borrowService.create(borrow);
	}
	
	@PutMapping("/members/borrow/return/{id}")
	public void returnBook(@PathVariable Long id) {
		borrowService.update(id, null);
	}
}
