package com.taskone.members.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "borrows")
@Data
public class Borrow {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "borrow_id")
	private Long borrowId;
	
	private String borrowingDate;
	
	private String returnDate;
	
	private Long returnCharge;
	
	@ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
	
	@ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
	
	
}
