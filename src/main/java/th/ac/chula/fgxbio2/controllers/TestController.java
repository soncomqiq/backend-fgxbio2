package th.ac.chula.fgxbio2.controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import th.ac.chula.fgxbio2.payload.response.MessageResponse;
import th.ac.chula.fgxbio2.services.ExcelUploadService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	@Autowired
	private ExcelUploadService excelUploadService;

	@GetMapping("/all")
	public String allAccess() {
		return "Public Content.";
	}

	@GetMapping("/user")
//	@PreAuthorize("hasRole('USER') or hasRole('LAB_USER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
//	@PreAuthorize("hasRole('LAB_USER')")
	public String moderatorAccess() {
		return "Moderator Board.";
	}

	@GetMapping("/admin")
//	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}

	@PostMapping("/excel")
	public ResponseEntity<?> uploadExcel() {
		ResponseEntity<?> response;

		try {
			String message = excelUploadService.readExcelData("uploads/032F Sample Details Report 232.xlsx");

			response = ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Something went wrong"));
		} catch (IOException e) {
			e.printStackTrace();
			response = ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
					.body(new MessageResponse("Only .xlsx or .xls file extension is supported"));
		}

		return response;
	}
}