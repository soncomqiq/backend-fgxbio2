package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.services.LocusService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/loci")
public class LocusController {
	@Autowired
	private LocusService locusService;
	
	@GetMapping("/all")
	public List<?> getAllLocus(){
		return locusService.getAllLocus();
	}
}
