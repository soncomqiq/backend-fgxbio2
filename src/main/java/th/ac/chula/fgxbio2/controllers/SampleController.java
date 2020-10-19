package th.ac.chula.fgxbio2.controllers;

import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.payload.request.LocusAllele;
import th.ac.chula.fgxbio2.payload.response.MatchedSampleResponse;
import th.ac.chula.fgxbio2.services.SampleService;

@RestController
@RequestMapping("/api/samples")
public class SampleController {
	@Autowired
	private SampleService sampleService;

	@PostMapping("/person")
	public ResponseEntity<MatchedSampleResponse> getPersonsByLocusAllele(@RequestBody List<LocusAllele> lAlist) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> rolesList = auth.getAuthorities();
		
		if (rolesList.stream().anyMatch(e -> e.getAuthority().equals("ROLE_ADMIN"))
				|| rolesList.stream().anyMatch(e -> e.getAuthority().equals("ROLE_LAB_USER"))) {
			return ResponseEntity.status(HttpStatus.OK).body(sampleService.getPersonsByLocusAllele(lAlist, true));
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(sampleService.getPersonsByLocusAllele(lAlist, false));
		}
	}

	@GetMapping("/allele")
	public ResponseEntity<List<Float>> getAlleleByLocus(@RequestParam String locus) {
		return ResponseEntity.status(HttpStatus.OK).body(sampleService.getAlleleByLocus(locus));
	}
}
