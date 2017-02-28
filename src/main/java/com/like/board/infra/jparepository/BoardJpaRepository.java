package com.like.board.infra.jparepository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.like.board.domain.repository.BoardRepository;
import com.like.board.infra.jparepository.dto.BoardHierarchyDTO;
import com.like.board.infra.jparepository.dto.QBoardTreeDTO;
import com.like.board.infra.jparepository.springdata.JpaArticle;
import com.like.board.infra.jparepository.springdata.JpaArticleCheck;
import com.like.board.infra.jparepository.springdata.JpaBoard;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.like.board.domain.model.*;

@Repository
public class BoardJpaRepository /*extends QueryDslRepositorySupport*/ implements BoardRepository {
	
	/*public BoardJpaRepository() {
		super(Board.class);
	}*/
				
	@Autowired
	private JPAQueryFactory  queryFactory;
	
	@Autowired
	private JpaBoard jpaBoard;
	
	@Autowired
	private JpaArticle jpaArticle;
	
	@Autowired
	private JpaArticleCheck jpaArticleCheck;
			
	private final QBoard qBoard = QBoard.board;
	private final QArticle qArticle = QArticle.article;
	private final QArticleCheck qArticleCheck = QArticleCheck.articleCheck;	

	public ArticleCheck findFkarticleAndSysuser(Long fkarticle, String userId) {
									
		return queryFactory.selectFrom(qArticleCheck)
					.where(qArticleCheck.sysUser.eq(userId)
					.and(qArticleCheck.article.pkArticle.eq(fkarticle)))
					.fetchOne();				
	}
	
	@Override
	public Board getBoard(Long id) {
		return jpaBoard.findOne(id);
	}

	@Override
	public List<Board> getBoardList() {		
		return jpaBoard.findAll(); 		
	}
	
	public List<Board> getBoardList(String likeBoardName) {		
									
		//query = (JPAQuery) super.getQuerydsl().applyPagination(pageable, query);
		
		return queryFactory.selectFrom(qBoard)
					.where(qBoard.boardNm.like(likeBoardName))
					.fetch();				
	}
	
	public List getBoardByTree() {
				
		QBoard parent = new QBoard("parent");								
		
		Expression<String> leaf = new CaseBuilder()
										.when(parent.pkBoard.isNotNull()).then("True")
										.otherwise("False");										
		
		return queryFactory.select(qBoard.pkBoard, qBoard.boardNm,leaf,leaf,leaf,qBoard.ppkBoard)
				.from(qBoard)
				.leftJoin(parent)				
					//.on(qBoard.pkBoard.eq(Long.parseLong(parent.ppkBoard.toString())))
					.on(qBoard.pkBoard.eq(parent.pkBoard))
				.where(qBoard.ppkBoard.eq("")).fetch();					
	}

	@Override
	public void saveBoard(Board board) {
		if ( !board.hasParentBoard() ) {
			board.setParentRoot();
		}
		jpaBoard.save(board);
	}
		

	@Override
	public void deleteBoard(Long pkBoard) {
		jpaBoard.delete(pkBoard);
	}
	
	@Override
	public Article getArticle(Long id) {
		return jpaArticle.findOne(id);
	}
	
	@Override
	public List<Article> getArticleList(Long fkBoard) { 
					
		return queryFactory.selectFrom(qArticle)
							.where(qArticle.board.pkBoard.eq(fkBoard))
							.fetch();				
	}

	@Override
	public Long saveArticle(Article article, Long fkBoard) {		
		
		Board board = jpaBoard.findOne(fkBoard);
		
		if ( !article.hasParentArticle() ){
			article.setParentRoot();
		}
		
		if (article.getSeq() == null ) {							
			article.setSeq(getArticleNextSeq(fkBoard));
		} else if (article.getSeq() == 0 ) {
			article.setSeq(1L);
		}
						
		article.setBoard(board);
						
		Article savedArticle = jpaArticle.saveAndFlush(article);
							
		return savedArticle.getPkArticle();
	}
	
	public void deleteArticle(Article article) {		
		
		jpaArticle.delete(article);
	}
	
	private void deleteArticleCheck(Long fkArticle) {
		queryFactory
			.delete(qArticleCheck)
			.where(qArticleCheck.article.pkArticle.eq(fkArticle))
			.execute();					
	}

	@Override
	public ArticleCheck getArticleCheck(Long fkarticle, String userId) {				
					
		return queryFactory
				.selectFrom(qArticleCheck)
				.where(qArticleCheck.sysUser.eq(userId)
				  .and(qArticleCheck.article.pkArticle.eq(fkarticle)))
				.fetchOne();			
	}
	
	@Override
	public Long getArticleNextSeq(Long pkboard) {
							
		Long rtn = queryFactory
						.select(qArticle.seq.max())
			  			.from(qArticle)
			  			.where(qArticle.board.pkBoard.eq(pkboard))				  
			  			.fetchOne();
		if ( rtn == null) {
			rtn = 0L;
		}
		
		return rtn + 1;		
	}

	@Override
	public int updateArticleHitCnt(Long pkAriticle, String userId) {				
				
		Article article = jpaArticle.findOne(pkAriticle);		
				
		article.updateHitCnt();
		
		jpaArticle.save(article);
				
		ArticleCheck check = queryFactory
									.selectFrom(qArticleCheck)
									.where(qArticleCheck.article.pkArticle.eq(pkAriticle)
									  .and(qArticleCheck.sysUser.eq(userId)))
									.fetchOne();
		
		if ( check == null) {
			check = new ArticleCheck();				
			check.setArticle(article);			
		} else {
			check.updateHitCnt();
		}
		
		jpaArticleCheck.save(check);
			 		
		return article.getHitCnt();		
	}
/*
	@Override
	public List<BookmarkVO> getBookmarkList(String userId) {		
		return boardDAO.getBoardBookmarkList(userId);
	}
	@Override
	public Long saveBookmark(Bookmark bookmark, Long fkBoard) {
		Board board = jpaBoard.findOne(fkBoard);
		
		bookmark.setNextSeq();
		bookmark.setBoard(board);
				
		return bookmark.getPkBookmark();
	}
	*/
	
}