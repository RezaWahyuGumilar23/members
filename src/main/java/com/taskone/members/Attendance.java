package com.taskone.members;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "attendance")
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "attendance_id")
	private Long attendanceId;
	
	private String dateTime;
	
	private String loginTime;
	
	private String logoutTime;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
	
	public Attendance() {
		super();
	}
	
	public Attendance(Member member, String dateTime, String loginTime, String logoutTime) {
		this.member = member;
		this.dateTime = dateTime;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
	}

	public Long getAttendanceId() {
		return attendanceId;
	}

	public void setAttendanceId(Long attendanceId) {
		this.attendanceId = attendanceId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
	
	
}
