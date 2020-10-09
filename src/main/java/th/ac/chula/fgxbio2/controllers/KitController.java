package th.ac.chula.fgxbio2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.payload.response.KitLocusListResponse;
import th.ac.chula.fgxbio2.services.KitService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/kits")
public class KitController {
	@Autowired
	private KitService kitService;

	@GetMapping("/all")
	public ResponseEntity<KitLocusListResponse> getAllKits(){
		return ResponseEntity.status(HttpStatus.OK).body(kitService.getAllKits());
	}
}
