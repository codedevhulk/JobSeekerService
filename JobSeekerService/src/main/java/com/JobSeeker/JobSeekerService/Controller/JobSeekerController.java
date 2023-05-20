package com.JobSeeker.JobSeekerService.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.JobSeeker.JobSeekerService.JobSeekerService;
import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;
import com.JobSeeker.JobSeekerService.Model.JobSeekerDetailsRequest;
import com.JobSeeker.JobSeekerService.Model.SignInDetailsRequest;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/jobseeker")
@Slf4j
@CrossOrigin("http://localhost:3000/")
public class JobSeekerController {
	
	
	

	
	
	@Autowired
	JobSeekerService jobSeekerService;
	// To post the jobSeeker details
	
	@PutMapping("/updateprofile")
	public boolean signupDetails(@RequestBody JobSeekerDetails jobSeekerDetailsRequest){
		
		
		log.info(jobSeekerDetailsRequest.toString());
		log.info(jobSeekerDetailsRequest.getAddress());
	jobSeekerService.signupDetails(jobSeekerDetailsRequest);
//		
//		log.info("Passing the jobSeekerDetails to service layer"+ jobSeekerDetailsRequest.toString());
		return true;
	}
	
	
	@DeleteMapping("/delete/{userName}")
	public String deleteUserByUserName(@PathVariable String userName) {
		
		
		
		jobSeekerService.deleteUserByUserName(userName);
		return "user has been deleted with name "+userName;
	}
	
//	@PostMapping("/signup")
//	public ResponseEntity<String> signupDetails(@RequestBody JobSeekerDetailsRequest jobSeekerDetailsRequest){
//		jobSeekerService.signupDetails(jobSeekerDetailsRequest);
//		return new ResponseEntity<>("Your details are registered successfully",HttpStatus.OK);
//	}
	@GetMapping("/all")
	public List<JobSeekerDetails> allDetails() {
		return jobSeekerService.allDetails();
	}
	
	
	@GetMapping("/{userName}")
	public ResponseEntity<JobSeekerDetails> getJobSeekerByUserName(@PathVariable String userName){
		
		JobSeekerDetails jobSeekerDetails=jobSeekerService.getJobSeekerByUserName(userName);
		return new ResponseEntity<>(jobSeekerDetails,HttpStatus.OK);
		
		
	}
	
	@PostMapping("/signin")
	public ResponseEntity<String> signInDetails(@RequestBody SignInDetailsRequest signInDetailsRequest){
		String message=jobSeekerService.signInDetails(signInDetailsRequest);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	
	
	// To Get a Job seeker details by his Id
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<JobSeekerDetails> getJobSeekerById(@PathVariable int id){
		
		
		JobSeekerDetails jobSeekerDetails=jobSeekerService.getJobSeekerById(id);
		
		return new ResponseEntity<>(jobSeekerDetails,HttpStatus.OK);
	}
	
	
	
	

}
