package com.taskone.members.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taskone.members.base.BaseService;
import com.taskone.members.exceptions.MemberNotFoundException;
import com.taskone.members.model.Borrow;
import com.taskone.members.repository.BorrowRepository;

import jakarta.transaction.Transactional;

@Service
public class BorrowService implements BaseService<Borrow> {
	
	@Value("${borrow.book.charge}")
	private Integer borrowCharge;
	
	@Value("${borrow.book.defaultperiod}")
	private Integer defaultPeriod;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private BorrowRepository borrowRepository;

	@Override
	@Transactional
	public Collection<Borrow> all() {
		return borrowRepository.findAll();
	}

	@Override
	@Transactional
	public Borrow item(Long id) {
		return borrowRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
	}

	@Override
	@Transactional
	public Borrow create(Borrow data) {
		Borrow recordBorrow = new Borrow();
		recordBorrow.setMember(memberService.item(data.getMember().getMemberId()));
		recordBorrow.setBook(bookService.item(data.getBook().getBookId()));
		recordBorrow.setBorrowingDate(LocalDate.now().format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
		return borrowRepository.save(recordBorrow);
	}

	@Override
	@Transactional
	public void update(Long id, Borrow data) {
		borrowRepository.findById(id).map(borrow -> {
			LocalDate borrowDate = LocalDate.parse(borrow.getBorrowingDate(), DateTimeFormatter.ofPattern("MMMM dd, yyyy"));
			LocalDate returnDate = LocalDate.now();
			Long borrowPeriod = ChronoUnit.DAYS.between(borrowDate, returnDate);
			
			borrow.setReturnDate(returnDate.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy")));
			if (borrowPeriod > defaultPeriod) {
				borrow.setReturnCharge((borrowPeriod - defaultPeriod) * borrowCharge);
			} else {
				borrow.setReturnCharge(0L);
			}
			return borrowRepository.save(borrow);
		}).orElseThrow(() -> new MemberNotFoundException(id));	
	}

	@Override
	@Transactional
	public void delete(Long id) {
		borrowRepository.deleteById(id);
	}

}
