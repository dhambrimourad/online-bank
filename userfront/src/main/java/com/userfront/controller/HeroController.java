package com.userfront.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.userfront.domain.Hero;
import com.userfront.service.HeroService;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(value="/heroes")
public class HeroController {
		
	@Autowired
    private HeroService heroService;
	
	@GetMapping(produces = "application/json")
	public List<Hero> getHerosList() {
		List<Hero> heroList = heroService.findHeroList();
		
		return heroList;
    }
	
	@GetMapping(path = {"/{id}"}, produces = "application/json")
	public Hero getHero(@PathVariable("id") String id) {
		Hero hero = heroService.findById(new Long(id));
		
		return hero;
    }
	
	@GetMapping(path = {"/name/{name}"}, produces = "application/json")
    public List<Hero> getHeroByName(@PathVariable("name") String heroName) {
		List<Hero> heroList = heroService.findHerosWithPartOfName(heroName);
		
		return heroList;
    }
	
	@PutMapping(produces = "application/json")
    public Hero updateHero(@RequestBody Hero hero) {
		return heroService.save(hero);
    }
	
	@PostMapping(produces = "application/json")
    public Hero createHero(@RequestBody Hero hero) {
		return heroService.save(hero);
    }
	
	@DeleteMapping(path ={"/{id}"})
    public void delete(@PathVariable("id") Long id) {
        heroService.deleteHero(id);
    }
	
}
