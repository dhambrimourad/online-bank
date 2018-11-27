package com.userfront.service;

import java.util.List;

import com.userfront.domain.Hero;

public interface HeroService {
	Hero findById(Long id);
	List<Hero> findHerosWithPartOfName(String heroName);
    List<Hero> findHeroList();
    Hero save(Hero hero);
    void deleteHero(Long id);
}
