package com.like.board.web.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.like.board.domain.model.Article;
import com.like.common.dto.annotation.DtoField;

import lombok.Data;

@Data
public class ArticleSaveDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6844786437530688768L;
	
	LocalDateTime sysDt;	
	
	String sysUser;
		
	LocalDateTime updDt;
		
	String updUser;
    	
    Long pkArticle;	
	
	Long ppkArticle;		
		
	String title;
    	
    String contents;
    	
    String pwd;
    	
    String hitCount;
        	
    LocalDate fromDate;
    	
    LocalDate toDate;
    	
    Integer seq;
    	
    Integer depth;
    	
    Long fkBoard;
            
    @JsonIgnore
    MultipartFile file;
        
}