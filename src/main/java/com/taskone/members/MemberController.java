package com.taskone.members;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {
	private final MemberRepository repository;
	private final AttendanceRepository attendanceRepository;
	
	MemberController(MemberRepository repository, AttendanceRepository attendanceRepository) {
		this.repository = repository;
		this.attendanceRepository = attendanceRepository;
	}
	
	@GetMapping("/members")
	List<Member> all() {
		return repository.findAll();
	}
	
	@PostMapping("/members/add")
	Member addNewMember(@RequestBody Member newMember) {
		return repository.save(newMember);
	}
	
	@GetMapping("/members/{id}")
	Member getMember(@PathVariable Long id) {
		return repository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
	}
	
	@DeleteMapping("/members/{id}")
	void deleteMember(@PathVariable Long id) {
		repository.deleteById(id);
	}
	
	@PutMapping("/members/attendance/{id}")
	void checkInAndCheckOut(@PathVariable Long id) {
		boolean loginStatus = getMember(id).isLoggedIn();
		repository.findById(id).map(member -> {
			member.setLoginStatus(!loginStatus);
			recordcheckInAndCheckOutTime(
					new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime()), 
					id, 
					member.isLoggedIn()
				);
			return repository.save(member);
		})
		.orElseThrow(() -> new MemberNotFoundException(id));
	}
	
	void recordcheckInAndCheckOutTime(String currentDate, Long memberId, boolean isLoggedIn) {
		String loginDate = new SimpleDateFormat("MMMM dd, yyyy").format(Calendar.getInstance().getTime());
		String loginTime = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		
		attendanceRepository.findByDateTime(currentDate).ifPresentOrElse(attendance -> {
			attendanceRepository.findById(attendance.getAttendanceId())
			.map(attendanceById -> {
				attendanceById.setDateTime(loginDate);
				if(isLoggedIn) attendanceById.setLoginTime(loginTime);
				else attendanceById.setLogoutTime(loginTime);
				
				return attendanceRepository.save(attendanceById);
			});
		}, () -> {
			attendanceRepository.save(new Attendance(getMember(memberId), loginDate, loginTime, ""));
		});
	}
	
}
