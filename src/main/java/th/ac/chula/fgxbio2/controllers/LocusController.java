package th.ac.chula.fgxbio2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.payload.response.KitLocusResponse;
import th.ac.chula.fgxbio2.services.LocusService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/loci")
public class LocusController {
	@Autowired
	private LocusService locusService;
	
	@GetMapping("/all")
	public ResponseEntity<KitLocusResponse> getAllLocus(){
		return ResponseEntity.status(HttpStatus.OK).body(locusService.getAllLocus());
	}
}
