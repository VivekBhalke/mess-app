package com.messapp.messapp.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.PerDayAmount;

@Repository
public interface PerDayAmountRepository extends JpaRepository<PerDayAmount, Long> {
	
	@Query("SELECT p FROM PerDayAmount p")
    PerDayAmount getSinglePerDayAmount();
}
