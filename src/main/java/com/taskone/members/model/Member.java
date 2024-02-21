package com.taskone.members.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "members")
@Data
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "member_id")
	private Long memberId;
	private String name;
	private String mobileNo;
	@OneToMany(mappedBy = "member")
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Borrow> borrow;
	
}
