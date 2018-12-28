package com.like.hrm.appointment.domain.event;

import org.springframework.context.ApplicationEvent;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ToString
public class ProcessEvent extends ApplicationEvent {
	
	private static final long serialVersionUID = -4365056669864989318L;
	
	public ProcessEvent(Object source) {
		super(source);		
		// TODO Auto-generated constructor stub
	}

}
