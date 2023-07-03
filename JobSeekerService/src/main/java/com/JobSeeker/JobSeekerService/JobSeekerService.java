package com.JobSeeker.JobSeekerService;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;
import com.JobSeeker.JobSeekerService.Model.JobseekerSignupResponse;
import com.JobSeeker.JobSeekerService.Model.SignInDetailsRequest;
import com.JobSeeker.JobSeekerService.Model.UserInfoResponse;
import com.JobSeeker.JobSeekerService.Repository.JobSeekerRepository;
import com.JobSeeker.JobSeekerService.exception.CustomException;
//import com.RecruitService.RecruiterService.Entity.RecruiterDetails;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobSeekerService {
	@Autowired
	PasswordEncoder passwordEncoder;
    @Autowired
    JobSeekerRepository repo;
    @Autowired
    JwtUtils jwtUtils;
    
	public JobseekerSignupResponse createNewRecruiter(JobSeekerDetails rd) {
			
			JobSeekerDetails jobSeekerDetails=repo.findByUsername(rd.getUsername());
			
			if(!Objects.isNull(jobSeekerDetails)) {	 
				throw new CustomException("Username is already present.Try with other username!", "", 500);
			}
			
			rd.setPassword(passwordEncoder.encode(rd.getPassword()));
			//System.out.println(repo.save(rd));
			JobSeekerDetails savedObj=repo.save(rd);
			JobseekerSignupResponse signupresponse=new JobseekerSignupResponse(savedObj.getJobSeekerId(),"JobSeeker registered successfully!");
			return signupresponse;
	}
	
		public JobSeekerDetails updateRecruiterDetails(JobSeekerDetails rd) {
				
				return repo.save(rd);
			}
    
    
	/*public void signupDetails(JobSeekerDetails jobSeekerDetailsRequest) {
		
		
		if(jobSeekerDetailsRequest.getUserName().isEmpty()) {
			
			
			throw new CustomException("User name is mandatory for signup, please provide the user name","BAD_REQUEST",400);
		}
		
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
		
	}*/
	public List<JobSeekerDetails> allDetails() {
		return repo.findAll();
	}
	
	
	
	
	
	
	public ResponseEntity<UserInfoResponse> signInDetails(SignInDetailsRequest signInDetailsRequest) {
		//String message="";
		JobSeekerDetails signinDetails = repo.findByUsername(signInDetailsRequest.getUsername());
		if (signinDetails!= null) 
		{
		 String password = signInDetailsRequest.getPassword();
		 String encodedPassword = signinDetails.getPassword();
         Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
         if (isPwdRight) 
         {
         	String jwttoken = jwtUtils.generateTokenFromUsername(signInDetailsRequest.getUsername());
         	return ResponseEntity.ok()//.header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
     		        .body(new UserInfoResponse(signinDetails.getJobSeekerId(),
     		        							signinDetails.getUsername(),
     		        							signinDetails.getEmail(),
     		        							jwttoken,
     		        							Arrays.asList("ROLE_JOBSEEKER")
     		                                   ));
 		    

 		} else 
 		{
 			throw new CustomException("Invalid Credentials", "", 500);
 	     }

 		}
 		else {
 			throw new CustomException("Username not found.Please Register!", "", 500);
         }
		
	}
	public JobSeekerDetails getJobSeekerById(long id) {
		// TODO Auto-generated method stub
		
		JobSeekerDetails jobSeekerDetails= repo.findByJobSeekerId(id);
		if(Objects.isNull(jobSeekerDetails)) {
			throw new CustomException("Job seeker not found with ID: "+id,"NOT_FOUND",404);
		}
		else {
		return jobSeekerDetails;}
		
		
		
	}
	public JobSeekerDetails getJobSeekerByUserName(String userName) {
		// TODO Auto-generated method stub
		log.info("user name"+userName);
		
		
		JobSeekerDetails jobSeekerDetailsByName=repo.findByUsername(userName);
		if(Objects.isNull(jobSeekerDetailsByName)) {
			
			
			
			
			throw new CustomException("Job seeker doesn't found with name: "+userName,"NOT_FOUND",404);
		}
		else {
			return jobSeekerDetailsByName;
		}
	}
	public void deleteUserByUserName(String userName) {
		
		JobSeekerDetails jobSeekerDetailsByName=repo.findByUsername(userName);
		if(Objects.isNull(jobSeekerDetailsByName)) {
			
			throw new CustomException("Job seeker doesn't found with name: "+userName,"NOT_FOUND",404);
		}
		// TODO Auto-generated method stub
		repo.deleteByUsername(userName);
		
	}
	
	

}
