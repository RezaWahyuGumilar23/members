package com.taskone.members.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskone.members.dto.BorrowDto;
import com.taskone.members.model.Borrow;
import com.taskone.members.services.BorrowService;

@RestController
@RequestMapping("/api/v1/borrow")
public class BorrowController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private BorrowService borrowService;
	
	@PostMapping
	public ResponseEntity<BorrowDto> borrowBook(@RequestBody BorrowDto borrowDto) {
		Borrow borrowRequest = modelMapper.map(borrowDto, Borrow.class);
		Borrow borrow = borrowService.create(borrowRequest);
		BorrowDto borrowResponse = modelMapper.map(borrow, BorrowDto.class);
		
		return new ResponseEntity<BorrowDto>(borrowResponse, HttpStatus.CREATED);
	}
	
	@PutMapping("/return/{id}")
	public void returnBook(@PathVariable Long id) {
		borrowService.update(id, null);
	}
}
