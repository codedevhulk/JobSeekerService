package com.JobSeeker.JobSeekerService.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobSeeker.JobSeekerService.JobSeekerService;
import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;
import com.JobSeeker.JobSeekerService.Model.JobSeekerDetailsRequest;
import com.JobSeeker.JobSeekerService.Model.SignInDetailsRequest;

@RestController
@RequestMapping("/jobseeker")
public class JobSeekerController {
	
	
	
	// Just checking github flowwwwwww
	//jwhjsdjhdwjhwdwjhewke	j	wek
	
	@Autowired
	JobSeekerService jobSeekerService;
	// To post the jobSeeker details
	@PostMapping("/signup")
	public ResponseEntity<String> signupDetails(@RequestBody JobSeekerDetailsRequest jobSeekerDetailsRequest){
		jobSeekerService.signupDetails(jobSeekerDetailsRequest);
		return new ResponseEntity<>("Your details are registered successfully",HttpStatus.OK);
	}
	@GetMapping("/all")
	public List<JobSeekerDetails> allDetails() {
		return jobSeekerService.allDetails();
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signInDetails(@RequestBody SignInDetailsRequest signInDetailsRequest){
		String message=jobSeekerService.signInDetails(signInDetailsRequest);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	
	

}
