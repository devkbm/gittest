package com.like.menu.domain.repository.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;

import org.hibernate.validator.constraints.NotEmpty;

import com.like.common.domain.annotation.DTOInfo;
import com.like.menu.domain.model.Menu;


import lombok.Data;

@Data
public class MenuDTO implements Serializable {
	
	LocalDateTime createdDt;	
	
	String createdBy;
	
	LocalDateTime modifiedDt;
	
	String modifiedBy;
	
	@NotEmpty
	private String menuGroupCode;
			
	@NotEmpty
	private String menuCode;
		
	@NotEmpty
	private String menuName;
		
	private String parentMenuCode;
		
	private long sequence;
			
	private long level;
	
	private String program;
						
	public MenuDTO() {}
	
	public MenuDTO(Menu menu) {		
		this.createdDt = menu.getCreatedDt();
		this.createdBy = menu.getCreatedBy();
		this.modifiedDt = menu.getModifiedDt();
		this.modifiedBy = menu.getModifiedBy();
		this.menuGroupCode = menu.getMenuGroup().getMenuGroupCode();
		this.menuCode = menu.getMenuCode();
		this.menuName = menu.getMenuName();
		this.parentMenuCode = menu.getParentMenuCode();
		this.sequence = menu.getSequence();
		this.level = menu.getLevel();		
		this.program = menu.getProgram().getProgramCode();
	}		
	
}
