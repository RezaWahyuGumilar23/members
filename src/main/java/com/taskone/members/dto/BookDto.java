package com.taskone.members.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BookDto {
	
	@JsonProperty("book_id")
	private Long bookId;
	
	@JsonProperty("title")
	private String title;
	
	@JsonProperty("author")
	private String author;
	
	@JsonProperty("genre")
	private String genre;
}
