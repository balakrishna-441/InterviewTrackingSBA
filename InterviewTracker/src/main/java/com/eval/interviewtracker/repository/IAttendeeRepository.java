package com.eval.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eval.interviewtracker.entity.Attendee;

@Repository
public interface IAttendeeRepository extends JpaRepository<Attendee, Long> {
	
	@Query(value = "select * from Attendee at where user_id=?1",nativeQuery = true)
	List<Attendee> findAttendeByUserID(Long id);

	@Query(value = "select * from Attendee at where interview_id = ?1",nativeQuery = true)
	List<Attendee> findAttendeByInterviewID(Long id);
		
}
