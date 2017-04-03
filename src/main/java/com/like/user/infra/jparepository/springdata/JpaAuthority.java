package com.like.user.infra.jparepository.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.like.user.domain.model.Authority;

@Repository
public interface JpaAuthority extends JpaRepository<Authority, String> {
	
}
