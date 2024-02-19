package com.taskone.members.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
	@JsonProperty("borrow_id")
	private Long borrowId;
	
	@JsonProperty("borrowing_date")
	private String borrowingDate;
	
	@JsonProperty("return_date")
	private String returnDate;
	
	@JsonProperty("return_charge")
	private Long returnCharge;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
	@JsonProperty("member")
    private Member member;
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
	@JsonProperty("book")
    private Book book;
	
	public Borrow() {
		super();
	}
	
	public Borrow(Member member, String borrowingDate, String returnDate, Long returnCharge) {
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

	public Long getReturnCharge() {
		return returnCharge;
	}

	public void setReturnCharge(Long returnCharge) {
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
