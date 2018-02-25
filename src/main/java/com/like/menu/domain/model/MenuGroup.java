package com.like.menu.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.common.domain.AuditEntity;

import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@Table(name = "cmmenugroup")
@EntityListeners(AuditingEntityListener.class)
public class MenuGroup extends AuditEntity implements Serializable {
	
	@Id
	@Column(name="menu_group_code")
	private String menuGroupCode;
	
	@Column(name="menu_group_name")
	private String menuGroupName; 
		
	@Column(name="description")
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "menuGroup")          
    List<Menu> menuList = new ArrayList<Menu>();
	
	public MenuGroup() {}			

	public MenuGroup(String menuGroupCode, String menuGroupName, String description) {	
		this.menuGroupCode = menuGroupCode;
		this.menuGroupName = menuGroupName;
		this.description = description;
	}
	
	public void setMenuList(List<Menu> menuList) {
		this.menuList = menuList;
	}
	
}
