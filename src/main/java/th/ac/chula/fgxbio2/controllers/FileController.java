package th.ac.chula.fgxbio2.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import th.ac.chula.fgxbio2.payload.response.MessageResponse;
import th.ac.chula.fgxbio2.payload.response.UploadReponse;
import th.ac.chula.fgxbio2.services.FileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
	@Autowired
	private FileService fileservice;

	@PostMapping("/forenseq")
	public ResponseEntity<?> uploadForensesqFile(@RequestParam("file") MultipartFile file) throws IOException {
		String message = fileservice.readExcelForenseqData(file);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	}

	@PostMapping("/person")
	public ResponseEntity<?> uploadPersonFile(@RequestParam("file") MultipartFile file) {
		String message = fileservice.readExcelPersonData(file);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	}

	@PostMapping("/cedata")
	public ResponseEntity<?> uploadCEFile(@RequestParam("file") MultipartFile file) {
		String message = fileservice.readTextFileCEData(file);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
	}

	@PostMapping("/excel")
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		List<String> errorFiles = new ArrayList<>();

		Arrays.asList(files).stream().map(file -> {
			try {
				return uploadForensesqFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorFiles.add(file.getOriginalFilename());
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());

		if (errorFiles.size() == 0) {
			return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Upload sucessfully."));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new UploadReponse(errorFiles));
	}
}
