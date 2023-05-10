package com.JobSeeker.JobSeekerService.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class JobSeekerDetailsRequest {
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
