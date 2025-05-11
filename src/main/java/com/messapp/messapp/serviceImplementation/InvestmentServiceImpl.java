package com.messapp.messapp.serviceImplementation;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messapp.messapp.entities.InvestmentEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.jpaRepositories.InvestmentRepository;
import com.messapp.messapp.services.InvestmentService;

@Service
public class InvestmentServiceImpl implements InvestmentService {

	@Autowired
	private InvestmentRepository investmentRepo;
	
	@Override
	public InvestmentEntity getTheLatestInvestmentEntity(PersonEntity person) {
		return investmentRepo.findTopByPersonOrderByDateDesc(person);
	}

	@Override
	public double getTotalInvestmentForAndAfter(PersonEntity person, Date date) {
		return investmentRepo.findByDateAfterAndPerson(date , person)
				.stream()
				.mapToDouble(InvestmentEntity::getAmountOfInvestment)
				.sum();
				
	}

	@Override
	public double getTotalInvestmentFor(PersonEntity person) {
		return investmentRepo.findByPerson(person)
					.stream()
					.mapToDouble(InvestmentEntity::getAmountOfInvestment)
					.sum();
	}

}
