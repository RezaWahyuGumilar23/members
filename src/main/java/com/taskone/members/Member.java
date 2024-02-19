package com.taskone.members;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Long memberId;
	
	private String name;
	
	private String mobileNo;
	
	private boolean loginStatus;
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Set<Borrow> borrow = new HashSet<>();
	
	public Member() {
		super();
	}
	
	public Member(Long memberId, String name, String mobileNo, boolean loginStatus) {
		this.memberId = memberId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.loginStatus = loginStatus;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public Long getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public void setLoginStatus(boolean loginStatus) {
		this.loginStatus = loginStatus;
	}

	public boolean isLoggedIn() {
		return loginStatus;
	}

	public Set<Borrow> getBorrow() {
		return borrow;
	}

	public void setBorrow(Set<Borrow> borrow) {
		this.borrow = borrow;
	}
	
}
