package th.ac.chula.fgxbio2.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import th.ac.chula.fgxbio2.datastucture.SequenceDetail;
import th.ac.chula.fgxbio2.models.tables.EGender;
import th.ac.chula.fgxbio2.models.tables.Forenseq;
import th.ac.chula.fgxbio2.models.tables.ForenseqSequence;
import th.ac.chula.fgxbio2.models.tables.Person;
import th.ac.chula.fgxbio2.models.tables.Sample;
import th.ac.chula.fgxbio2.repository.tables.PersonRepository;

@Service
public class FileService {
	@Autowired
	private PersonRepository personRepository;

	@Transactional
	public String readExcelForenseqData(MultipartFile file) throws IOException {
		Workbook workbook = WorkbookFactory.create(file.getInputStream());
		int numberOfSheets = workbook.getNumberOfSheets();

		Person personInfo = new Person();
		personInfo.setAge(0);
		Sample sample = new Sample();
		personInfo.add(sample);

		for (int i = 0; i < numberOfSheets; i++) {
			Sheet sheet = workbook.getSheetAt(i);
			String sheetName = sheet.getSheetName();

			Map<String, SequenceDetail> locusAllele = new LinkedHashMap<>();

			if (sheetName.equals("Autosomal STRs")) {
				extractInfo(personInfo, sample, sheet);
				extractForenseqData(personInfo, sample, sheet, locusAllele, 13, 40, "Autosome");
			} else if (sheetName.equals("Y STRs")) {
				extractForenseqData(personInfo, sample, sheet, locusAllele, 13, 36, "Y");
			} else if (sheetName.equals("X STRs")) {
				extractForenseqData(personInfo, sample, sheet, locusAllele, 13, 19, "X");
			} else if (sheetName.equals("iSNPs")) {
				extractForenseqData(personInfo, sample, sheet, locusAllele, 12, 105, "iSNP");
			}

			personRepository.save(personInfo);
		}

		return "Successfully uploaded";
	}

	public Workbook openCompatibleWorkbook(String fileName, InputStream fis) throws IOException {
		return WorkbookFactory.create(fis);
	}

	private void extractForenseqData(Person personInfo, Sample sample, Sheet sheet,
			Map<String, SequenceDetail> locusAllele, int startSummaryLine, int endSummaryLine, String chromosomeTag) {

		int line = 1;
		for (Row row : sheet) {
			List<String> data = getCells(row);
			if (line >= startSummaryLine && line <= endSummaryLine) {
				locusAllele.put(data.get(0), new SequenceDetail(data.get(1), data.get(2)));
			} else if (line >= endSummaryLine + 3) {
				if (data.get(0).equals("n/a")) {
					break;
				}

				if (data.get(2).equals("Yes")) {
					ForenseqSequence tempFS = new ForenseqSequence(data.get(1), (int) Double.parseDouble(data.get(3)),
							data.size() < 5 ? "n/a" : data.get(4), sample);
					locusAllele.get(data.get(0)).getFsList().add(tempFS);
				}
			}

			if (!data.get(0).equals("n/a") && !data.get(0).equals("")) {
				line++;
			}
		}

		for (Entry<String, SequenceDetail> entry : locusAllele.entrySet()) {
			Forenseq forenseq = new Forenseq(entry.getKey(), entry.getValue().getGenotype(),
					entry.getValue().getQcIndicator(), chromosomeTag);
			sample.add(forenseq);
			for (ForenseqSequence curFS : entry.getValue().getFsList()) {
				forenseq.add(curFS);
			}
		}
	}

	private void extractInfo(Person personInfo, Sample sample, Sheet sheet) {
		int line = 1;
		for (Row row : sheet) {
			List<String> data = getCells(row);

			if (line >= 1 && line <= 7) {
				if (data.get(0).equals("Created")) {
					int sampleYear = Integer.parseInt(data.get(1).split(" ")[2]);
					sample.setSampleYear(sampleYear);
				} else if (data.get(0).equals("Sample")) {
					String sampleId = data.get(1);
					sample.setSampleId(sampleId);
				} else if (data.get(0).equals("Gender")) {
					EGender gender = data.get(1) == "XY" ? EGender.MALE : EGender.FEMALE;
					personInfo.setGender(gender);
				}
			} else {
				break;
			}

			if (!data.get(0).equals("n/a")) {
				line++;
			}
		}
	}

	private List<String> getCells(Row row) {
		List<String> data = new ArrayList<>();
		for (Cell cell : row) {
			switch (cell.getCellType()) {
			case STRING:
				data.add(cell.getStringCellValue());
				break;
			case NUMERIC:
				data.add(String.valueOf(cell.getNumericCellValue()));
				break;
			case BOOLEAN:
				data.add(String.valueOf(cell.getBooleanCellValue()));
				break;
			default:
				data.add("n/a");
			}
		}
		return data;
	}

	public String readExcelPersonData(MultipartFile file) {
		return "success";
	}

	public String readTextFileCEData(MultipartFile file) {
		return "success";
	}
}
