package com.taskone.members.controller.ui;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taskone.members.dto.MemberDto;
import com.taskone.members.model.Member;
import com.taskone.members.services.MemberService;

@Controller
public class MemberUIController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private MemberService memberService;

	@GetMapping("/members")
	public ModelAndView getAllMembers() {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("members", memberService.all().stream().map(member -> modelMapper.map(member, MemberDto.class))
				.collect(Collectors.toList()));
		return mav;
	}

	@GetMapping("/addmember")
	public ModelAndView formNewMember() {
		ModelAndView mav = new ModelAndView("addmember");
		mav.addObject("member", new MemberDto());
		return mav;
	}

	@PostMapping("/member")
	public ModelAndView addNewMember(@ModelAttribute MemberDto member) {
		Member memberMapped = modelMapper.map(member, Member.class);
		memberService.create(memberMapped);

		ModelAndView mav = new ModelAndView("successaddmember");
		mav.addObject("member", memberMapped);
		return mav;
	}

}
