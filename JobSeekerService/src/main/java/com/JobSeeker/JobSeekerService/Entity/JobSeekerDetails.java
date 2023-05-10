package com.JobSeeker.JobSeekerService.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JobSeekerDetails {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	String firstName;
	String lastName;
	String mobileNumber;
	String email;
	String password;
	String qualification;
	String skillSet;
	String experience;
	String summary;
	String address;
}
