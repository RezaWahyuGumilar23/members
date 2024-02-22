package com.taskone.members.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class MemberDto {
	@JsonProperty("member_id")
	private Long memberId;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("mobile_no")
	private String mobileNo;
	
	@JsonProperty("book_borrowed")
	private Long borrowedBook;
}
