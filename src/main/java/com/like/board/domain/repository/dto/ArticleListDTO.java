package com.like.board.domain.repository.dto;

import java.io.Serializable;
import java.util.Date;

import com.querydsl.core.annotations.QueryProjection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleListDTO implements Serializable {
	
	private static final long serialVersionUID = 6343198961752805944L;

	/**
	 * 생성일자
	 */
	Date sysDt;	
	
	/**
	 * 생성유저		
	 */
	String sysUser;
	
	/**
	 * 생성유저명
	 */
	String sysUsernm;
	
	/**
	 * 수정일자			
	 */
	Date updDt;
	
	/**
	 * 수정유저			
	 */
	String updUser;	
	
	/**
	 * 게시글 키
	 */
	String pkArticle;	
	
	/**
	 * 게시글 상위키
	 */
	String ppkArticle;		
	
	/**
	 * 제목
	 */
	String title;
    
    /**
     * 내용
     */
    String contents;
     
    /**
     * 비밀번호
     */
    String pwd;
    
    /**
     * 조회 수
     */
    String hitCnt;    
        
    /**
     * 시작일자
     */
    String fromDt;
    
    /**
     * 종료일자
     */
    String toDt;
        
    /**
     * 출력순서
     */
    String seq;
    
    /**
     * 조회 여부
     */
    String checkYn;
            
    /**
     * 파일 정보
     * uuid | path | name | size
     */
    String fileInfo;
    
	/**
	 * 게시판 외래키
	 */
	String fkBoard;
	
	@QueryProjection
	public ArticleListDTO(Date sysDt, String sysUser, String sysUsernm, Date updDt, String updUser, String pkArticle,
			String ppkArticle, String title, String contents, String pwd, String hitCnt, String fromDt, String toDt,
			String seq, String checkYn, String fileInfo, String fkBoard) {
		super();
		this.sysDt = sysDt;
		this.sysUser = sysUser;
		this.sysUsernm = sysUsernm;
		this.updDt = updDt;
		this.updUser = updUser;
		this.pkArticle = pkArticle;
		this.ppkArticle = ppkArticle;
		this.title = title;
		this.contents = contents;
		this.pwd = pwd;
		this.hitCnt = hitCnt;
		this.fromDt = fromDt;
		this.toDt = toDt;
		this.seq = seq;
		this.checkYn = checkYn;		
		this.fileInfo = fileInfo;
		this.fkBoard = fkBoard;
	}
		               
}
