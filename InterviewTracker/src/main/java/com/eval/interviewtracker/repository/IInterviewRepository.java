package com.eval.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eval.interviewtracker.entity.Interview;

@Repository
public interface IInterviewRepository extends JpaRepository<Interview, Long> {

	@Query("select k from #{#entityName} k where k.interviewerName = :interviewerName")
	List<Interview> getInterviewByInterviewerName(@Param("interviewerName") String interviewerName);

	@Query("select k from #{#entityName} k where k.interviewName = :interviewName")
	List<Interview> getInterviewByInterviewName(@Param("interviewName") String interviewName);	
	
	@Query("select k from #{#entityName} k where k.interviewerName = :interviewerName and k.interviewName = :interviewName")
	List<Interview> getInterviewByInterviewerandInterviewName(@Param("interviewerName") String interviewerName,@Param("interviewName") String interviewName);	
}
