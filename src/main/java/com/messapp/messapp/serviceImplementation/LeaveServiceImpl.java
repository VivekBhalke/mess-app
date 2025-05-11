package com.messapp.messapp.serviceImplementation;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.messapp.messapp.dto.LeaveInputDTO;
import com.messapp.messapp.dto.LeaveInputWithSqlDates;
import com.messapp.messapp.entities.AttendanceEntity;
import com.messapp.messapp.entities.LeavesEntity;
import com.messapp.messapp.entities.PersonEntity;
import com.messapp.messapp.enums.LeaveStatus;
import com.messapp.messapp.enums.LeaveType;
import com.messapp.messapp.enums.MealType;
import com.messapp.messapp.exception.ApiException;
import com.messapp.messapp.exception.ApiResponse;
import com.messapp.messapp.exception.EveningLeaveTimeGone;
import com.messapp.messapp.exception.LeaveDateInPastException;
import com.messapp.messapp.exception.LeaveForThisDayExists;
import com.messapp.messapp.exception.MorningLeaveTimeGone;
import com.messapp.messapp.jpaRepositories.AttendanceTimeRepository;
import com.messapp.messapp.jpaRepositories.LeaveRepository;
import com.messapp.messapp.middleware.ValidateAttendanceForLeave;
import com.messapp.messapp.middleware.ValidateLeaveTime;
import com.messapp.messapp.middleware.ValidateMealTypes;
import com.messapp.messapp.services.LeaveService;
import jakarta.transaction.Transactional;

@Service
public class LeaveServiceImpl implements LeaveService {

	@Autowired
	private LeaveRepository leaveRepository;

	@Autowired
	private ValidateAttendanceForLeave validateAttendanceForLeave;
	
	
	
	
	@Autowired
	private AttendanceTimeRepository attendanceTimeRepository;
	
	@Autowired
	private ValidateLeaveTime validateLeaveTime;
	
	
	@Override
	@Transactional
	public List<LeavesEntity> getLeaveByDate(PersonEntity person, Date date) {
		return leaveRepository.findByPersonAndDate(person, date);
	}


	@Override
	@Transactional
	public void putLeaveForOneDay(PersonEntity person, Date date , LeaveType leavetype , String reason) throws ApiException {
		try 
		{
			validateLeaveTime.validateLeaveTime(leavetype , date);
			ValidateMealTypes.validateMeal(person, leavetype);
			validateAttendanceForLeave.validateAttendance(person, date, leavetype);
			LeavesEntity leaveForToday = new LeavesEntity();
			leaveForToday.setPerson(person);
			leaveForToday.setDate(date);
			leaveForToday.setLeaveStatus(LeaveStatus.PENDING);
			leaveForToday.setLeaveType(leavetype);
			leaveForToday.setReason(reason);
			leaveRepository.save(leaveForToday);
		}
		catch(DataIntegrityViolationException e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw new LeaveForThisDayExists();
		}
		catch(MorningLeaveTimeGone e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw e;
		}
		catch(EveningLeaveTimeGone e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw e;
		}
		catch(RuntimeException e )
		{
			System.out.println("run time excption");
			System.out.println(e);
			throw e;
		}
	}


	@Override
	@Transactional
	public void putLeaveForMoreThanOneDay(PersonEntity person, List<LeaveInputWithSqlDates> leaveInputs)
			throws ApiException {
		try 
		{
			for(var leaveInput : leaveInputs)
			{
				System.out.println("reached here in the service of put leave for more than one day");
				validateLeaveTime.validateLeaveTime(leaveInput.getLeaveType() , leaveInput.getDate());
				ValidateMealTypes.validateMeal(person, leaveInput.getLeaveType());
				validateAttendanceForLeave.validateAttendance(person, leaveInput.getDate(), leaveInput.getLeaveType());
				LeavesEntity leaveForToday = new LeavesEntity();
				leaveForToday.setPerson(person);
				leaveForToday.setDate(leaveInput.getDate());
				leaveForToday.setLeaveStatus(LeaveStatus.PENDING);
				leaveForToday.setLeaveType(leaveInput.getLeaveType());
				leaveForToday.setReason(leaveInput.getReason());
				System.out.println("putting in the db in the service layer");
				leaveRepository.save(leaveForToday);
			}

		}
		catch(DataIntegrityViolationException e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw new LeaveForThisDayExists();
		}
		catch(MorningLeaveTimeGone e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw e;
		}
		catch(EveningLeaveTimeGone e)
		{
			System.out.println("put leave for one day exception");
			System.out.println(e);
			throw e;
		}
		catch(RuntimeException e )
		{
			System.out.println("run time excption");
			System.out.println(e);
			throw e;
		}
		
	}
	
	
	
	
	

}
