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
import com.JobSeeker.JobSeekerService.exception.CustomException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JobSeekerService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	JobSeekerRepository repo;

	public void signupDetails(JobSeekerDetails jobSeekerDetailsRequest) {

		if (jobSeekerDetailsRequest.getUserName().isEmpty()) {

			throw new CustomException("User name is mandatory for signup, please provide the user name", "BAD_REQUEST",
					400);
		}

		log.info("Checking whether job seeker details details exists");
		
		JobSeekerDetails jobSeekerDetails = repo.findByUserName(jobSeekerDetailsRequest.getUserName());

		// log.info(jobSeekerDetails.toString());

		// updating the Jobseekerdetails

		if (Objects.nonNull(jobSeekerDetails)) {

			if (Objects.nonNull(jobSeekerDetails.getJobSeekerId())) {

				jobSeekerDetails.setJobSeekerId(jobSeekerDetailsRequest.getJobSeekerId());
			}

			if (Objects.nonNull(jobSeekerDetailsRequest.getFirstName())) {
				jobSeekerDetails.setFirstName(jobSeekerDetailsRequest.getFirstName());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getLastName())) {
				jobSeekerDetails.setLastName(jobSeekerDetailsRequest.getLastName());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getUserName())) {
				jobSeekerDetails.setUserName(jobSeekerDetailsRequest.getUserName());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getMobileNumber())) {
				jobSeekerDetails.setMobileNumber(jobSeekerDetailsRequest.getMobileNumber());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getEmail())) {
				jobSeekerDetails.setEmail(jobSeekerDetailsRequest.getEmail());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getQualification())) {
				jobSeekerDetails.setQualification(jobSeekerDetailsRequest.getQualification());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getSkillSet())) {
				jobSeekerDetails.setSkillSet(jobSeekerDetailsRequest.getSkillSet());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getExperience())) {
				jobSeekerDetails.setExperience(jobSeekerDetailsRequest.getExperience());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getSummary())) {
				jobSeekerDetails.setSummary(jobSeekerDetailsRequest.getSummary());
			}
			if (Objects.nonNull(jobSeekerDetailsRequest.getAddress())) {
				jobSeekerDetails.setAddress(jobSeekerDetailsRequest.getAddress());
			}

			
			log.info("Saving jobseekerDetails details objects");
			repo.save(jobSeekerDetails);
			//log.info(jobSeekerDetails.toString());

		}

		// User does not exist so continuing with creating a new user

		else {

			// String encryptedPassword =
			// passwordEncoder.encode(jobSeekerDetailsRequest.getPassword());
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
			// jobSeekerDetailsRequest.setPassword(encryptedPassword);
			repo.save(jobSeekerDetailsRequest);

			log.info(jobSeekerDetailsRequest.toString());
			// repo.save(null)
		}

	}

	public List<JobSeekerDetails> allDetails() {
		
		
		
		log.info("Fetching all the job seeker details from Repo");
		return repo.findAll();
	}

	public String signInDetails(SignInDetailsRequest signInDetailsRequest) {
		String message = "";
		
		
		JobSeekerDetails signinDetails = repo.findByEmail(signInDetailsRequest.getEmail());
		if (signinDetails != null) {
			String password = signInDetailsRequest.getPassword();
			String encodedPassword = signinDetails.getPassword();
			Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
			if (isPwdRight) {

				message = "Login Success";
				return message;

			} else {
				message = "Login Failed";
				return message;
			}

		} else {
			message = "Email not exits";
			return message;
		}

	}

	public JobSeekerDetails getJobSeekerById(long id) {
		// TODO Auto-generated method stub
		log.info("Getting the job seeker by ID " + id);
		//JobSeekerDetails jobSeekerDetails = repo.findByJobSeekerId(id);
		JobSeekerDetails jobSeekerDetails = repo.findById(id).get();
		if (Objects.isNull(jobSeekerDetails)) {
			throw new CustomException("Job seeker not found with ID: " + id, "NOT_FOUND", 404);
		} else {
			return jobSeekerDetails;
		}

	}

	public JobSeekerDetails getJobSeekerByUserName(String userName) {
		// TODO Auto-generated method stub
		log.info("Getting the job seeker details by user name" + userName);

		JobSeekerDetails jobSeekerDetailsByName = repo.findByUserName(userName);
		if (Objects.isNull(jobSeekerDetailsByName)) {

			throw new CustomException("Job seeker doesn't found with name: " + userName, "NOT_FOUND", 404);
		} else {
			return jobSeekerDetailsByName;
		}
	}

	public void deleteUserByUserName(String userName) {

		log.info("Finding the user with name " + userName + " , If not found throwing an error");

		JobSeekerDetails jobSeekerDetailsByName = repo.findByUserName(userName);
		if (Objects.isNull(jobSeekerDetailsByName)) {

			throw new CustomException("Job seeker doesn't found with name: " + userName, "NOT_FOUND", 404);
		}
		// TODO Auto-generated method stub
		log.info("Deleting the user with name " + userName);

		repo.deleteByUserName(userName);

	}

}
