package com.messapp.messapp.middleware;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messapp.messapp.entities.BillingEntity;
import com.messapp.messapp.entities.InvestmentEntity;
import com.messapp.messapp.entities.PerDayAmount;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.MessType;
import com.messapp.messapp.exception.InsufficientFundsException;
import com.messapp.messapp.jpaRepositories.BillingRepository;
import com.messapp.messapp.jpaRepositories.PerDayAmountRepository;
import com.messapp.messapp.serviceImplementation.AttendanceServiceImpl;
import com.messapp.messapp.serviceImplementation.InvestmentServiceImpl;
import com.messapp.messapp.services.BillingService;

@Service
public class BillingServiceImpl implements BillingService {

	@Autowired
	private BillingRepository billingRepository;
	
	@Autowired
	private PerDayAmountRepository perDayAmountRepo;
	
	@Autowired
	private AttendanceServiceImpl attendanceService;
	
	@Autowired
	private InvestmentServiceImpl investmentService;
	
	@Override
	public BillingEntity getTheLatestBillingEntity(PersonEntity person) {
		
		return billingRepository.findTopByPersonOrderByDateDesc(person);
	}

	@Override
	public void makeBill(PersonEntity person) {
		
		try {
			BillingEntity billingEntity = getTheLatestBillingEntity(person);
			
			double attendanceCount = attendanceService.getAttendanceCount(person , billingEntity);
			MessType messtype = person.getMessType();
			PerDayAmount perDay = perDayAmountRepo.getSinglePerDayAmount();
			double amountToBePaid = 
				    messtype == MessType.BOTH ? perDay.getPerDayBoth() * attendanceCount :
				    messtype == MessType.MORNING ? perDay.getPerDayMorning() * attendanceCount :
				    perDay.getPerDayEvening() * attendanceCount;
			double amountOfInvestment = investmentService.getTotalInvestmentFor(person);
			double amountWithAdmin = billingEntity == null ? 0 : billingEntity.getAmountWithAdmin();
			double totalInvestment = amountOfInvestment + amountWithAdmin;
			if(totalInvestment >= amountToBePaid)
			{
				BillingEntity newBill = new BillingEntity();
				newBill.setPerson(person);
				LocalDate localDate = LocalDate.now(); // or any LocalDate
				java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
				newBill.setDate(sqlDate);
				newBill.setAmountWithAdmin(totalInvestment - amountToBePaid);
				billingRepository.save(newBill);
			}
			else {
				throw new InsufficientFundsException(amountToBePaid);
			}
		}catch(RuntimeException e)
		{
			throw e;
		}
	}

}
