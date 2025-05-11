package com.messapp.messapp.jpaRepositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.PersonEntity;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceEntity , Long>{

	public List<AttendanceEntity> findByPersonAndDate(PersonEntity person , Date date);
	
	public List<AttendanceEntity> findByPerson(PersonEntity person);
	
	public List<AttendanceEntity> findByPersonAndDateBetween(PersonEntity person , Date startDate , Date endDate);
	
	
	
}
