package pe.aysconsultores.bootstart.controller;

import java.util.ArrayList;
import java.util.List;

import pe.aysconsultores.bootstart.model.Team;
import pe.aysconsultores.bootstart.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {


	@Autowired
	TeamRepository teamRepository;

	@RequestMapping("/teams")
	public List<Team> getTeams() {
		return (List<Team>) teamRepository.findAll();
	}

	@RequestMapping("/teams/{id}")
	public  Team getTeam(@PathVariable Long id){
		return teamRepository.findById(id).get();
	}
}
