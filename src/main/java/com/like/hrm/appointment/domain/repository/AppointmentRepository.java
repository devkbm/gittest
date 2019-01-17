package com.like.hrm.appointment.domain.repository;

import org.springframework.stereotype.Repository;

import com.like.hrm.appointment.domain.entity.AppointmentMemorandum;

@Repository
public interface AppointmentRepository {

	AppointmentMemorandum getAppointmentMemorandum();
	
	void saveAppointmentMemorandum();
	
	void deleteAppointmentMemorandum();	
}