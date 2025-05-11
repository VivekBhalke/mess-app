package com.messapp.messapp.jpaRepositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.BillingEntity;
import com.messapp.messapp.entities.InvestmentEntity;
import com.messapp.messapp.entities.PersonEntity;

@Repository
public interface InvestmentRepository extends JpaRepository<InvestmentEntity, Long>{

	public InvestmentEntity findTopByPersonOrderByDateDesc(PersonEntity person);
	
	public List<InvestmentEntity> findByPerson(PersonEntity person);
	
	public List<InvestmentEntity> findByDateAfterAndPerson(Date date , PersonEntity person);
}
