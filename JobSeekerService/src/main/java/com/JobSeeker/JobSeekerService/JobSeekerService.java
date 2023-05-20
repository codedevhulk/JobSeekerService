package com.JobSeeker.JobSeekerService;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;
import com.JobSeeker.JobSeekerService.Model.JobSeekerDetailsRequest;
import com.JobSeeker.JobSeekerService.Model.SignInDetailsRequest;
import com.JobSeeker.JobSeekerService.Repository.JobSeekerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobSeekerService {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
    JobSeekerRepository repo;
	public void signupDetails(JobSeekerDetails jobSeekerDetailsRequest) {
		
		
		
		
		JobSeekerDetails jobSeekerDetails=repo.findByUserName(jobSeekerDetailsRequest.getUserName());
		
		//log.info(jobSeekerDetails.toString());
		
		//updating the Jobseekerdetails
		
		
		if(Objects.nonNull(jobSeekerDetails)) {
			
			
			
			if(Objects.nonNull(jobSeekerDetails.getJobSeekerId())) {
				
				
				
				jobSeekerDetails.setJobSeekerId(jobSeekerDetailsRequest.getJobSeekerId());
			}
			
			
			
			
			if(Objects.nonNull(jobSeekerDetailsRequest.getFirstName())) {
				jobSeekerDetails.setFirstName(jobSeekerDetailsRequest.getFirstName());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getLastName())) {
				jobSeekerDetails.setLastName(jobSeekerDetailsRequest.getLastName());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getUserName())) {
				jobSeekerDetails.setUserName(jobSeekerDetailsRequest.getUserName());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getMobileNumber())) {
				jobSeekerDetails.setMobileNumber(jobSeekerDetailsRequest.getMobileNumber());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getEmail())) {
				jobSeekerDetails.setEmail(jobSeekerDetailsRequest.getEmail());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getQualification())) {
				jobSeekerDetails.setQualification(jobSeekerDetailsRequest.getQualification());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getSkillSet())) {
				jobSeekerDetails.setSkillSet(jobSeekerDetailsRequest.getSkillSet());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getExperience())) {
				jobSeekerDetails.setExperience(jobSeekerDetailsRequest.getExperience());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getSummary())) {
				jobSeekerDetails.setSummary(jobSeekerDetailsRequest.getSummary());
			}
			if(Objects.nonNull(jobSeekerDetailsRequest.getAddress())) {
				jobSeekerDetails.setAddress(jobSeekerDetailsRequest.getAddress());
			}
			
			
			
			
			
			
			repo.save(jobSeekerDetails);
			log.info(jobSeekerDetails.toString());
			
		}
		
		
		
		// User does not exist so continuing with creating a new user
		
		else {
		
		
		//String encryptedPassword = passwordEncoder.encode(jobSeekerDetailsRequest.getPassword());
//		JobSeekerDetails details =  JobSeekerDetails.builder()
//				.firstName(jobSeekerDetailsRequest.getFirstName())
//				.lastName(jobSeekerDetailsRequest.getLastName())
//				.mobileNumber(jobSeekerDetailsRequest.getMobileNumber())
//				.email(jobSeekerDetailsRequest.getEmail())
//				.password(encryptedPassword)
//				.qualification(jobSeekerDetailsRequest.getQualification())
//				.skillSet(jobSeekerDetailsRequest.getSkillSet())
//				.experience(jobSeekerDetailsRequest.getExperience())
//				.summary(jobSeekerDetailsRequest.getSummary())
//				.address(jobSeekerDetailsRequest.getAddress())
//				.build();
//		repo.save(details);
		//jobSeekerDetailsRequest.setPassword(encryptedPassword);
		repo.save(jobSeekerDetailsRequest);
		
		log.info(jobSeekerDetailsRequest.toString());
		//repo.save(null)
	}
		
	}
	public List<JobSeekerDetails> allDetails() {
		return repo.findAll();
	}
	public String signInDetails(SignInDetailsRequest signInDetailsRequest) {
		String message="";
		JobSeekerDetails signinDetails = repo.findByEmail(signInDetailsRequest.getEmail());
		if (signinDetails!= null) 
		{
		 String password = signInDetailsRequest.getPassword();
		 String encodedPassword = signinDetails.getPassword();
         Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        if (isPwdRight) 
        {
        	
        	
		    message="Login Success";
		    return message;

		} else 
		{
			message = "Login Failed";
			return message;
	     }

		}
		else {
            message="Email not exits";
            return message;
        }
		
	}
	public JobSeekerDetails getJobSeekerById(long id) {
		// TODO Auto-generated method stub
		
		JobSeekerDetails jobSeekerDetails= repo.findByJobSeekerId(id);
		return jobSeekerDetails;
	}
	public JobSeekerDetails getJobSeekerByUserName(String userName) {
		// TODO Auto-generated method stub
		log.info("user name"+userName);
		
		return repo.findByUserName(userName);
	}
	public void deleteUserByUserName(String userName) {
		// TODO Auto-generated method stub
		repo.deleteByUserName(userName);
		
	}
	
	

}
