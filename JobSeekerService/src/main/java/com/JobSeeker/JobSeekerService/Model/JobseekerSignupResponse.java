package com.JobSeeker.JobSeekerService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobseekerSignupResponse {
	long jobSeekerId;
	String message;
}
