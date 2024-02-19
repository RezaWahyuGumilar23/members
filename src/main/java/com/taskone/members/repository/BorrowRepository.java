package com.taskone.members.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskone.members.model.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {}
