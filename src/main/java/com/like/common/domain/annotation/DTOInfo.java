package com.like.common.domain.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DTOInfo {
	
	/**
	 * 매핑될 Class
	 * @return
	 */
	Class<?> classInstance();
	
	/**
	 * 매핑될 필드명
	 * @return
	 */
	String fieldName();
	
	
}
