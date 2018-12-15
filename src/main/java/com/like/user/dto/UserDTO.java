package com.like.user.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.util.StringUtils;

import com.like.common.validation.annotation.Id;
import com.like.user.domain.model.QUser;
import com.like.user.domain.model.User;
import com.querydsl.core.BooleanBuilder;

import lombok.Data;

public class UserDTO {

	@Data
	public static class QueryCondition implements Serializable {

		private static final long serialVersionUID = -7886731992928427538L;

		private final QUser qUser = QUser.user;
		
		String userId;
		
		String name;
			
		
		public BooleanBuilder getBooleanBuilder() {
			BooleanBuilder builder = new BooleanBuilder();
			
			if (StringUtils.hasText(this.userId)) {
				builder.and(qUser.userId.like("%"+this.userId+"%"));
			}
			
			if (StringUtils.hasText(this.name)) {
				builder.and(qUser.name.like("%"+this.name+"%"));
			}
			
			return builder;
		}
	}
	
	@Data
	public static class UserSave {
		
		LocalDateTime createdDt;	
		
		String createdBy;
		
		LocalDateTime modifiedDt;
		
		String modifiedBy;
		
		@NotBlank(message="아이디를 입력해주세요")
		@Size(min=1, max=20, message="1자 이상 20자 이하의 아이디만 사용 가능합니다")
		@Pattern(regexp="^[A-Za-z0-9+]*$",message="영문,숫자로 이루어진 아이디만 사용 가능합니다")
		@Id(message="이미 가입한 아이디입니다")
		String userId;
			
		String name;
			
		String password;	
				
		Boolean accountNonExpired;
			
		Boolean accountNonLocked;
			
		Boolean credentialsNonExpired;
			
		Boolean enabled;
						
		List<String> authorityList = new ArrayList<String>();

		List<String> menuGroupList = new ArrayList<String>(); 
		
		public UserSave() {}
		
		public UserSave(User user) {
			this.createdDt = user.getCreatedDt();
			this.createdBy = user.getCreatedBy();
			this.modifiedDt = user.getModifiedDt();
			this.modifiedBy = user.getModifiedBy();
			this.userId = user.getUsername();
			this.name = user.getName();
			this.password = user.getPassword();
			this.accountNonExpired = user.isAccountNonExpired();
			this.accountNonLocked = user.isAccountNonLocked();
			this.credentialsNonExpired = user.isCredentialsNonExpired();
			this.enabled = user.isEnabled();
			this.authorityList = user.getAuthorityList()
									.stream()
									.map(auth -> auth.getAuthority())
									.collect(Collectors.toList());
			this.menuGroupList = user.getMenuGroupList()
									.stream()
									.map(menuGroup -> menuGroup.getMenuGroupCode())
									.collect(Collectors.toList());
		}
		

	}

}
