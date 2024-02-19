package com.taskone.members;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	private final MemberRepository memberRepository;
	private final BorrowRepository borrowRepository;
	private final BookRepository bookRepository;
	
	@Value("${useDefault:charge}")
	private String borrowCharge;
	
	@Value("${useDefault:defaultperiod}")
	private String defaultPeriod;
	
	MemberController(MemberRepository repository, BorrowRepository attendanceRepository, BookRepository bookRepository) {
		this.memberRepository = repository;
		this.borrowRepository = attendanceRepository;
		this.bookRepository = bookRepository;
	}
	
	@GetMapping("/members")
	List<Member> all() {
		return memberRepository.findAll();
	}
	
	@PostMapping("/members/add")
	Member addNewMember(@RequestBody Member newMember) {
		return memberRepository.save(newMember);
	}
	
	@GetMapping("/members/{id}")
	Member getMember(@PathVariable Long id) {
		return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
	}
	
	@DeleteMapping("/members/{id}")
	void deleteMember(@PathVariable Long id) {
		memberRepository.deleteById(id);
	}
	
	@PutMapping("/members/attendance/{id}")
	void checkInAndCheckOut(@PathVariable Long id) {
		boolean loginStatus = getMember(id).isLoggedIn();
		memberRepository.findById(id).map(member -> {
			member.setLoginStatus(!loginStatus);
			return memberRepository.save(member);
		})
		.orElseThrow(() -> new MemberNotFoundException(id));
	}
	
	@PostMapping("/members/borrow")
	Borrow borrowBook(@RequestBody Borrow borrow) {
		Borrow recordBorrow = new Borrow();
		memberRepository.findById(borrow.getMember().getMemberId()).ifPresent(
				member -> {
					recordBorrow.setMember(member);
				});
		bookRepository.findById(borrow.getBook().getBookId()).ifPresent(
				book->{
					recordBorrow.setBook(book);
				});
		
		recordBorrow.setBorrowingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
		return borrowRepository.save(recordBorrow);
	}
	
	@PutMapping("/members/borrow/return/{id}")
	void returnBook(@PathVariable Long id) {
		borrowRepository.findById(id).map(borrow -> {
			LocalDate borrowDate = LocalDate.parse(borrow.getBorrowingDate(), DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
			LocalDate returnDate = LocalDate.now();
			Integer delay = borrowDate.compareTo(returnDate);
			
			borrow.setReturnDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
			if (delay > Integer.parseInt(defaultPeriod)) {
				borrow.setReturnCharge((Integer.parseInt(defaultPeriod) - delay) * Integer.parseInt(borrowCharge));
			}
			return borrowRepository.save(borrow);
		}).orElseThrow(() -> new MemberNotFoundException(id));
	}
	
}
