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
	public Collection<Member> all() {
		return memberService.all();
	}
	
	@PostMapping("/members/add")
	public Member addNewMember(@RequestBody Member newMember) {
		return memberService.create(newMember);
	}
	
	@GetMapping("/members/{id}")
	public Member getMember(@PathVariable Long id) {
		return memberService.item(id);
	}
	
	@DeleteMapping("/members/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.delete(id);
	}
	
	@PutMapping("/members/attendance/{id}")
	public void checkInAndCheckOut(@PathVariable Long id, @RequestBody Member newMember) {
		memberService.update(id, newMember);
	}
	
}
