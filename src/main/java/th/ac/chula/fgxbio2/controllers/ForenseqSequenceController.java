package th.ac.chula.fgxbio2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.services.ForenseqSequenceService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/forenseq-sequences/")
public class ForenseqSequenceController {
	@Autowired
	private ForenseqSequenceService forenseqSequenceService;

	@GetMapping("/graph")
	public ResponseEntity<?> getGraphInfoByChroAndLocus(@RequestParam String chromosome, @RequestParam String locus) {
		return ResponseEntity.status(HttpStatus.OK).body(forenseqSequenceService.getGraphInfoByChroAndLocus(chromosome, locus));
	}
	
	@GetMapping("/map")
	public ResponseEntity<?> getMapInfoByLocus(@RequestParam String locus) {
		return ResponseEntity.status(HttpStatus.OK).body(forenseqSequenceService.getMapInfoByLocus(locus));
	}
}
