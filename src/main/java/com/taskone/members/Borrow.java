package com.taskone.members;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrows")
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrow_id")
	private Long borrowId;
	
	private String borrowingDate;
	
	private String returnDate;
	
	private Integer returnCharge;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;
	
	public Borrow() {
		super();
	}
	
	public Borrow(Member member, String borrowingDate, String returnDate, Integer returnCharge) {
		this.member = member;
		this.borrowingDate = borrowingDate;
		this.returnDate = returnDate;
		this.returnCharge = returnCharge;
	}

	public Long getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Long borrowId) {
		this.borrowId = borrowId;
	}

	public String getBorrowingDate() {
		return borrowingDate;
	}

	public void setBorrowingDate(String borrowingDate) {
		this.borrowingDate = borrowingDate;
	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public Integer getReturnCharge() {
		return returnCharge;
	}

	public void setReturnCharge(Integer returnCharge) {
		this.returnCharge = returnCharge;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	
}
