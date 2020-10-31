package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.models.tables.Region;
import th.ac.chula.fgxbio2.services.RegionService;

@RestController
@RequestMapping("/api/regions")
public class RegionController {
	@Autowired
	private RegionService regionService;
	
	@GetMapping("/")
	public ResponseEntity<?> getAllRegionsByCountryId(@RequestParam(name = "country_id") Integer countryId){
		List<Region> regionsList = regionService.getRegionsByCountryId(countryId);
		return ResponseEntity.status(HttpStatus.OK).body(regionsList);
	}
}
