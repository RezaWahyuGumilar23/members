package com.taskone.members;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Integer memberId;
	
	private String name;
	
	private String mobileNo;
	
	private boolean loginStatus;
	
	@OneToOne(mappedBy = "member", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Attendance attendance;
	
	public Member() {
		super();
	}
	
	public Member(Integer memberId, String name, String mobileNo, boolean loginStatus) {
		this.memberId = memberId;
		this.name = name;
		this.mobileNo = mobileNo;
		this.loginStatus = loginStatus;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public String getName() {
		return name;
	}

	public void setMemberId(Integer memberId) {
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

	public Attendance getAttendance() {
		return attendance;
	}

	public void setAttendance(Attendance attendance) {
		this.attendance = attendance;
	}
	
	
	
}
