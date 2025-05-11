package com.messapp.messapp.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.messapp.messapp.dto.AttendanceShowUserDTO;
import com.messapp.messapp.entities.AttendanceEntity;

@Component
public class AttendanceEntityMapper implements Function<AttendanceEntity, AttendanceShowUserDTO
>{

	@Override
	public AttendanceShowUserDTO apply(AttendanceEntity t) {
		// TODO Auto-generated method stub
		var output = new AttendanceShowUserDTO();
		output.setMealtype(t.getMealType());
		output.setDate("");
		output.converSqlDateToString(t.getDate());
		return output;
	}

	
}
