package com.messapp.messapp.jpaRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.messapp.messapp.entities.BillingEntity;
import com.messapp.messapp.entities.PersonEntity;

@Repository
public interface BillingRepository extends JpaRepository<BillingEntity, Long> {

	public BillingEntity findTopByPersonOrderByDateDesc(PersonEntity person);
}
