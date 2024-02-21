package com.taskone.members.controller.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.taskone.members.model.Member;
import com.taskone.members.services.MemberService;

@Controller
public class MemberUIController {
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/members")
	public ModelAndView getAllMembers() {
		ModelAndView mav = new ModelAndView("member");
		mav.addObject("members", memberService.all());
		return mav;
	}
	
	@GetMapping("/addmember")
	public ModelAndView formNewMember() {
		ModelAndView mav = new ModelAndView("addmember");
		mav.addObject("member", new Member());
		return mav;
	}
	
	@PostMapping("/member")
	public ModelAndView addNewMember(@ModelAttribute Member member) {
		memberService.create(member);
		
		ModelAndView mav = new ModelAndView("successaddmember");
		mav.addObject("member", member);
		return mav;
	}

}
