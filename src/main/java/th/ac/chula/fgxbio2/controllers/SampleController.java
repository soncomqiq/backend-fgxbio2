package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;
import th.ac.chula.fgxbio2.services.SampleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/samples")
public class SampleController {
	@Autowired
	private SampleService sampleService;

	@PostMapping("/persons")
	public List<?> getPersonsByLocusAllele(@RequestBody List<LocusAllele> lAlist) {
		return sampleService.getPersonsByLocusAllele(lAlist);
	}
}
