package com.taskone.members.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taskone.members.base.BaseService;
import com.taskone.members.exceptions.MemberNotFoundException;
import com.taskone.members.model.Member;
import com.taskone.members.repository.MemberRepository;

import jakarta.transaction.Transactional;

@Service
public class MemberService implements BaseService<Member> {
	
	@Autowired
	private MemberRepository memberRepository; 

	@Override
	@Transactional
	public Collection<Member> all() {
		return memberRepository.findAll();
	}

	@Override
	@Transactional
	public Member item(Long id) {
		return memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
	}

	@Override
	@Transactional
	public Member create(Member data) {
		return memberRepository.save(data);
	}

	@Override
	@Transactional
	public void update(Long id, Member data) {
		memberRepository.findById(id).map(member -> {
			member.setName(data.getName());
			member.setMobileNo(data.getMobileNo());
			return memberRepository.save(member);
		})
		.orElseThrow(() -> new MemberNotFoundException(id));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		memberRepository.deleteById(id);
	}

}
