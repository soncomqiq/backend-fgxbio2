package th.ac.chula.fgxbio2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import th.ac.chula.fgxbio2.models.tables.Person;
import th.ac.chula.fgxbio2.payload.request.PersonCustom;
import th.ac.chula.fgxbio2.payload.response.PersonsPages;
import th.ac.chula.fgxbio2.services.PersonService;

@RestController
@RequestMapping("/api/persons-custom")
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@GetMapping("/")
	public ResponseEntity<?> getPersonsWithPagination(@RequestParam Integer page, @RequestParam Integer size){
		PersonsPages personRes = personService.getAllPersonByPageAndSize(page, size);
		return ResponseEntity.status(HttpStatus.OK).body(personRes);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getPersonById(@PathVariable Integer id){
		Person person = personService.getPersonById(id);
		return ResponseEntity.status(HttpStatus.OK).body(person);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody PersonCustom body){
		String message = personService.updatePerson(id, body);
		
		if(message != null) {
			return ResponseEntity.ok().build();			
		}else {
			return ResponseEntity.badRequest().build();
		}
	}
}
