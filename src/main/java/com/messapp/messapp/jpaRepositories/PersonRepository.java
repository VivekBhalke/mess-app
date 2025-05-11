package com.messapp.messapp.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.PersonEntity;

@Repository
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {
	
	
}
