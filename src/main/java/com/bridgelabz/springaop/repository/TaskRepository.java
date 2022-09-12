package com.bridgelabz.springaop.repository;

import com.bridgelabz.springaop.entities.TaskDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskDetails, Long> {

}
