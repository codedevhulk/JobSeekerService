package com.JobSeeker.JobSeekerService;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;
import com.JobSeeker.JobSeekerService.Model.JobSeekerDetailsRequest;
import com.JobSeeker.JobSeekerService.Model.SignInDetailsRequest;
import com.JobSeeker.JobSeekerService.Repository.JobSeekerRepository;

@Service
public class JobSeekerService {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
    JobSeekerRepository repo;
	public void signupDetails(JobSeekerDetailsRequest jobSeekerDetailsRequest) {
		String encryptedPassword = passwordEncoder.encode(jobSeekerDetailsRequest.getPassword());
		JobSeekerDetails details =  JobSeekerDetails.builder()
				.firstName(jobSeekerDetailsRequest.getFirstName())
				.lastName(jobSeekerDetailsRequest.getLastName())
				.mobileNumber(jobSeekerDetailsRequest.getMobileNumber())
				.email(jobSeekerDetailsRequest.getEmail())
				.password(encryptedPassword)
				.qualification(jobSeekerDetailsRequest.getQualification())
				.skillSet(jobSeekerDetailsRequest.getSkillSet())
				.experience(jobSeekerDetailsRequest.getExperience())
				.summary(jobSeekerDetailsRequest.getSummary())
				.address(jobSeekerDetailsRequest.getAddress())
				.build();
		repo.save(details);
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
	
	

}
