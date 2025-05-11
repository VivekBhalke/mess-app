package com.messapp.messapp.serviceImplementation;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.messapp.messapp.dto.AttendanceShowUserDTO;
import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.BillingEntity;
import com.messapp.messapp.entities.PerDayAmount;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.MessType;
import com.messapp.messapp.jpaRepositories.AttendanceRepository;
import com.messapp.messapp.jpaRepositories.PerDayAmountRepository;
import com.messapp.messapp.mapper.AttendanceEntityMapper;
import com.messapp.messapp.middleware.BillingServiceImpl;
import com.messapp.messapp.middleware.DateRangeUtil;
import com.messapp.messapp.services.AttendanceService;

import jakarta.transaction.Transactional;

@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@Autowired
	private PerDayAmountRepository perDayAmountRepo;
	
	@Autowired
	private AttendanceEntityMapper attendanceMapper;
	
	@Autowired
	private BillingServiceImpl billingService;
	
	@Override
	@Transactional
	public void save(AttendanceEntity attendance) {
		
		attendanceRepository.save(attendance);

	}


	@Override
	public List<AttendanceEntity> findByPersonAndDate(PersonEntity person, Date date) {
		
		return attendanceRepository.findByPersonAndDate(person, date);
	}


	@Override
	public List<AttendanceShowUserDTO> getAllAttendane(PersonEntity person) {
		List<AttendanceEntity> attendanceEntites = attendanceRepository.findByPerson(person);
		return attendanceEntites.stream()
			.map(attendanceMapper)
			.collect(Collectors.toList());
	}


	


	@Override
	public List<AttendanceShowUserDTO> getAttendanceForOneYear(PersonEntity person, long year) {
		Date startDate = DateRangeUtil.getStartOfYear(year);
		Date endDate = DateRangeUtil.getEndOfYear(year);
		return attendanceRepository.findByPersonAndDateBetween(person, startDate, endDate)
				.stream()
				.map(attendanceMapper)
				.collect(Collectors.toList());
	}


	@Override
	public List<AttendanceShowUserDTO> getAttendanceForOneDay(PersonEntity person, Date date) {
		
		return attendanceRepository.findByPersonAndDate(person, date)
				.stream()
				.map(attendanceMapper)
				.collect(Collectors.toList());
	}


	@Override
	public List<AttendanceShowUserDTO> getAttendanceForOneMonth(PersonEntity person, int month, long year) {
		// TODO Auto-generated method stub
		Date startDate = DateRangeUtil.getStartOfMonth(year, month);
		Date endDate = DateRangeUtil.getEndOfMonth(year , month);
		return attendanceRepository.findByPersonAndDateBetween(person, startDate, endDate)
				.stream()
				.map(attendanceMapper)
				.collect(Collectors.toList());
	}

	
	
	

	@Override
	public Double getAttendanceCount(PersonEntity person) {
		
		
		BillingEntity billingEntity = billingService.getTheLatestBillingEntity(person);
		
		return getAttendanceCount(person , billingEntity);
		
	}


	@Override
	public Double getAttendanceCount(PersonEntity person, BillingEntity billingEntity) {
		if(billingEntity == null)
		{
			List<AttendanceEntity> attendanceEntites = attendanceRepository.findByPerson(person);
			Double attendanceCount = (double) attendanceEntites.stream()
				    .map(AttendanceEntity::getDate)
				    .distinct()
				    .count();
			return attendanceCount;

		}
		Date date = billingEntity.getDate();
		return (double)attendanceRepository.findByDateAfterAndPerson(date, person)
				.stream()
				.map(AttendanceEntity::getDate)
			    .distinct()
			    .count();
	}
	
	

}
