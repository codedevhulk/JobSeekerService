package com.JobSeeker.JobSeekerService.Repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JobSeeker.JobSeekerService.Entity.JobSeekerDetails;

@Repository
public interface JobSeekerRepository extends JpaRepository <JobSeekerDetails,Long>{
    @Transactional
	JobSeekerDetails findByEmail(String email);
    
    
    @Transactional
    JobSeekerDetails findByUsername(String username);

    @Transactional
	void deleteByUsername(String username);

    @Transactional
	JobSeekerDetails findByJobSeekerId(long id);

}
