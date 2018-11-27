package com.userfront.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.userfront.domain.Hero;

@Repository
public interface HeroDao extends CrudRepository<Hero, Long> {
	
	List<Hero> findByNameIgnoreCaseContaining(String name);
	
    List<Hero> findAll();
    
}
