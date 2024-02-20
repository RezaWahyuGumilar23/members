package com.taskone.members.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.taskone.members.model.Book;
import com.taskone.members.model.Member;
import lombok.Data;

@Data
public class BorrowDto {
	
	@JsonProperty("borrow_id")
	private Long borrowId;
	
	@JsonProperty("borrowing_date")
	private String borrowingDate;
	
	@JsonProperty("return_date")
	private String returnDate;
	
	@JsonProperty("return_charge")
	private Long returnCharge;
	
	@JsonProperty("member")
    private Member member;
	
	@JsonProperty("book")
    private Book book;
}
