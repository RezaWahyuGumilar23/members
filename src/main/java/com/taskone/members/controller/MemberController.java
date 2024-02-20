package com.taskone.members.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskone.members.dto.MemberDto;
import com.taskone.members.model.Member;
import com.taskone.members.services.MemberService;

@RestController
@RequestMapping("/api/v1/member")
public class MemberController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MemberService memberService;

	@GetMapping("/all")
	public List<MemberDto> all() {
		return memberService.all().stream().map(member -> modelMapper.map(member, MemberDto.class))
				.collect(Collectors.toList());
	}

	@PostMapping
	public ResponseEntity<MemberDto> addNewMember(@RequestBody MemberDto newMember) {
		Member memberRequest = modelMapper.map(newMember, Member.class);
		Member member = memberService.create(memberRequest);
		MemberDto memberResponse = modelMapper.map(member, MemberDto.class);
		
		return new ResponseEntity<MemberDto>(memberResponse, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<MemberDto> getMember(@PathVariable Long id) {
		Member member = memberService.item(id);
		MemberDto memberResponse = modelMapper.map(member, MemberDto.class);
		return ResponseEntity.ok().body(memberResponse);
	}

	@DeleteMapping("/{id}")
	public void deleteMember(@PathVariable Long id) {
		memberService.delete(id);
	}

}
