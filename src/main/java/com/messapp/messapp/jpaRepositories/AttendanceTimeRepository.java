package com.messapp.messapp.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.AttendanceTimeEntity;


@Repository
public interface AttendanceTimeRepository  extends JpaRepository<AttendanceTimeEntity, Long>{
	@Query(value = "SELECT * FROM attendance_time LIMIT 1", nativeQuery = true)
    public AttendanceTimeEntity findSingleRecord();
}