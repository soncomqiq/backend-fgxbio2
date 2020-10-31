package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.models.tables.Province;
import th.ac.chula.fgxbio2.services.ProvinceService;

@RestController
@RequestMapping("/api/provinces")
public class ProvinceController {
	@Autowired
	private ProvinceService provinceService;
	
	@GetMapping("/")
	public ResponseEntity<?> getProvincesByRegionId(@RequestParam(name = "region_id") Integer regionId){
		List<Province> provincesList = provinceService.getProvincesByRegionId(regionId);
		return ResponseEntity.status(HttpStatus.OK).body(provincesList);
	}
}
