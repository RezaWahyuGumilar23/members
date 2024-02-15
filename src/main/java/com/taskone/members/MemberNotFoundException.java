package com.taskone.members;

public class MemberNotFoundException extends RuntimeException {
	MemberNotFoundException(Long id) {
		super("Could not found member with id : " + id);
	}
}
