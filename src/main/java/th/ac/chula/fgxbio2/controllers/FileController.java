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
import th.ac.chula.fgxbio2.services.FileService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/files")
public class FileController {
	@Autowired
	private FileService fileservice;

	@PostMapping("/uploadFile")
	public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
		String message = fileservice.readExcelData(file);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));

	}

	@PostMapping("/uploadMultipleFiles")
	public ResponseEntity<?> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		List<String> errorFiles = new ArrayList<>();
		Arrays.asList(files).stream().map(file -> {
			try {
				return uploadFile(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				errorFiles.add(file.getOriginalFilename());
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		System.out.println(errorFiles);
		return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse("Upload sucessfully."));
	}
}
