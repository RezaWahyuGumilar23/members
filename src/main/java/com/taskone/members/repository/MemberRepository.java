package com.taskone.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskone.members.model.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}
