package com.eval.interviewtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.eval.interviewtracker.entity.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long>,QueryByExampleExecutor<User> {

}
