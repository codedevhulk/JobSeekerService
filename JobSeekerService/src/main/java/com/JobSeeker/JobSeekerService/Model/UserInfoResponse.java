package com.JobSeeker.JobSeekerService.Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
	long id;
	String username;
	String email;
	String jwttoken;
	List<String> roles;
}
