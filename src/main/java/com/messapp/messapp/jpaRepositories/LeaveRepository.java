package com.messapp.messapp.jpaRepositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.LeavesEntity;
import com.messapp.messapp.entities.PersonEntity;

@Repository
public interface LeaveRepository extends JpaRepository<LeavesEntity, Long>{
	
	
	
	public List<LeavesEntity> findByPersonAndDate(PersonEntity person , Date date);
	
}
