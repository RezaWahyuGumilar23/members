package com.taskone.members.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taskone.members.model.Member;
import com.taskone.members.services.MemberService;

@RestController
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	Collection<Member> all() {
		return memberService.all();
	}
	
	@PostMapping("/members/add")
	Member addNewMember(@RequestBody Member newMember) {
		return memberService.create(newMember);
	}
	
	@GetMapping("/members/{id}")
	Member getMember(@PathVariable Long id) {
		return memberService.item(id);
	}
	
	@DeleteMapping("/members/{id}")
	void deleteMember(@PathVariable Long id) {
		memberService.delete(id);
	}
	
	@PutMapping("/members/attendance/{id}")
	void checkInAndCheckOut(@PathVariable Long id, @RequestBody Member newMember) {
		memberService.update(id, newMember);
	}
	
}
