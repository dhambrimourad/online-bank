package com.userfront.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userfront.dao.HeroDao;
import com.userfront.domain.Hero;

@Service
public class HeroServiceImpl implements HeroService {

	@Autowired
    private HeroDao heroDao;
	
	public List<Hero> findHeroList() {
        return heroDao.findAll();
    }
	
	public List<Hero> findHerosWithPartOfName(String heroName) {
        return heroDao.findByNameIgnoreCaseContaining(heroName);
    }
	
	public Hero findById(Long id) {
        return heroDao.findById(id).get();
    }
	
	public Hero save(Hero hero) {
        return heroDao.save(hero);
    }
	
	public void deleteHero(Long id) {
        heroDao.deleteById(id);
    }
	
}
