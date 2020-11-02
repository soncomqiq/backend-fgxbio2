package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.models.tables.Race;
import th.ac.chula.fgxbio2.services.RaceService;

@RestController
@RequestMapping("/api/races")
public class RaceController {
	@Autowired
	private RaceService raceService;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllRaces(){
		List<Race> raceList = raceService.getAllRaces();
		return ResponseEntity.status(HttpStatus.OK).body(raceList);
	}
}
